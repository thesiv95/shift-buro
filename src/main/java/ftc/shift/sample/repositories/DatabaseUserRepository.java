package ftc.shift.sample.repositories;

import ftc.shift.sample.exception.BadRequestException;
import ftc.shift.sample.exception.NotFoundException;
import ftc.shift.sample.models.Card;
import ftc.shift.sample.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Реализиция, хранящая все данные в БД
 */
@Repository
@ConditionalOnProperty(name = "use.database", havingValue = "true")
public class DatabaseUserRepository implements UserRepository {

    DatabaseUserRepository () {
        //
    }

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    private UserExtractor userExtractor;

    @Autowired
    private CardExtractor cardExtractor;

    @Autowired
    private CardRepository cardRepository;

    public void initialize() {
        // Подразумевается, что H2 работает в in-memory режиме и таблицы необходимо создавать при каждом старте приложения
        // SQL запросы для создания таблиц
        String createUsersTableSql = "create table USERS (" +
                "ID       INT PRIMARY KEY AUTO_INCREMENT," +
                "NAME     VARCHAR(255)," +
                "PIC_URL  VARCHAR(255)," +
                "BALANCE  INT);";
        jdbcTemplate.update(createUsersTableSql, new MapSqlParameterSource());
    }



    public List<User> getAllUsers() {
        String sql = "select * from USERS";

        MapSqlParameterSource params = new MapSqlParameterSource();

        List<User> all = jdbcTemplate.query(sql, params, userExtractor);
        return all;
    }


    private void checkUser (User user) {
        if (user == null)
            throw new BadRequestException("пользователь не существует");
        if (user.getPicUrl() == "")
            throw new BadRequestException("нет ссылки на аву");
        if (user.getPicUrl() == null)
            throw new BadRequestException("Нужна ава");
        if (user.getBalance() < 50)
            throw new BadRequestException("баланс задан некорректно");
        if (user.getName() == "")
            throw new BadRequestException("нет имя");
        if (user.getName() == null)
            throw new BadRequestException("Нужна имя");
    }

    // Добавить пользователя
    public void addUser(User user){
        checkUser(user);
        String insertUserSql = "INSERT INTO USERS (NAME, PIC_URL, BALANCE) " +
                "values (?, ?, ?)";
        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.getJdbcTemplate().update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection)
                    throws SQLException {
                PreparedStatement ps = connection.prepareStatement(insertUserSql.toString(),
                        Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, user.getName());
                ps.setString(2, user.getPicUrl());
                ps.setInt(3, user.getBalance());
                return ps;
                }
            }, holder);
            Integer newPersonId = holder.getKey().intValue();
            user.setId(newPersonId);
    }

    public void changeBalance(Integer cardId, Integer userId) {
        if (cardRepository.getCard(cardId).getStatus() == false)
            throw new BadRequestException("Объявление неактивно");
        if (userId == cardRepository.getCard(cardId).getOwnerId())
            throw new BadRequestException("Сам у себя покупаешь");
        User donor = getUser(cardRepository.getCard(cardId).getOwnerId());
        User recip = getUser(userId);
        Integer price = cardRepository.getCard(cardId).getPrice();
        if (donor.getBalance() < price)
            throw new BadRequestException("у автора объявления не хватает денег");
        else {
            cardRepository.updateStatus(userId, cardId);
            Integer recBal = recip.getBalance() + price;
            Integer donBal = donor.getBalance() - price;

            // добавляем
            String addBallsSql = "UPDATE USERS SET BALANCE = " + recBal + " WHERE ID = " + userId;

            // Снимаем баллы у донора
            String removeBallsSql = "UPDATE USERS SET BALANCE = " + donBal + " WHERE ID = " + cardRepository.getCard(cardId).getOwnerId();

            MapSqlParameterSource params2 = new MapSqlParameterSource()
                    .addValue("price", price)
                    .addValue("recipientId", userId)
                    .addValue("donorId", cardRepository.getCard(cardId).getOwnerId());

            jdbcTemplate.update(addBallsSql, params2);
            jdbcTemplate.update(removeBallsSql, params2);
        }
    }

    public User getUser(Integer id) {

        String sql = "SELECT * FROM USERS WHERE ID=:id";

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", id);

        List<User> users = jdbcTemplate.query(sql, params, userExtractor);

        if (users.isEmpty()) { return null; }

        return users.get(0);
       }

    public void deleteUser(Integer userId) {
        if (getUser(userId) == null)
            throw new NotFoundException("Некорректная карта");

        String AdsSql = "SELECT * FROM ADS WHERE OWNER_ID=" + userId;
        MapSqlParameterSource AdsParams = new MapSqlParameterSource();
        List<Card> Ads = jdbcTemplate.query(AdsSql, AdsParams, cardExtractor);
        for (int i = 0; i <Ads.size(); i++) {
            cardRepository.deleteCard(userId, Ads.get(i).getId());
        }
        String sql = "DELETE FROM USERS WHERE USERS.ID=:userId";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("userId", userId);
         jdbcTemplate.update(sql, params);
    }

    public void updateUser(User user) {
        if(user == null)
            throw new BadRequestException("пользователь задан некорректно");
        if(user.getId() == null)
            throw new NotFoundException("Некорректная карта");
        else {
            String sql = "UPDATE USERS SET NAME = " + user.getName() + ", PIC_URL = " + user.getPicUrl() + ", BALANCE = " +
                    user.getBalance() + "WHERE ID = " + user.getId();
            MapSqlParameterSource params = new MapSqlParameterSource();
            jdbcTemplate.update(sql, params);
        }
    }
}