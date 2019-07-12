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
            throw new BadRequestException("Пользователь не существует");
        if (user.getPicUrl() == "")
            throw new BadRequestException("Нет ссылки на аву");
        if (user.getPicUrl() == null)
            throw new BadRequestException("Нужна ава");
        if (user.getBalance() < 50)
            throw new BadRequestException("Баланс задан некорректно - не меньше 50");
        if (user.getName() == "")
            throw new BadRequestException("Имя не может быть пустым");
        if (user.getName() == null)
            throw new BadRequestException("Нужно указать свое имя");
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
            throw new BadRequestException("Самому себе нельзя перечислить баллы");
        User cardOwner = getUser(cardRepository.getCard(cardId).getOwnerId());
        User user = getUser(userId);
        Integer price = cardRepository.getCard(cardId).getPrice();
        if (cardOwner.getBalance() < price)
            throw new BadRequestException("У автора объявления не хватает баллов");
        else {
            cardRepository.updateStatus(userId, cardId);
            Integer userBal = user.getBalance();
            Integer ownerBal = cardOwner.getBalance();
            if (cardRepository.getCard(cardId).getType().equals("Просьба")) {
                userBal += price;
                ownerBal -= price;
            }
            else if (cardRepository.getCard(cardId).getType().equals("Предложение")){
                userBal -= price;
                ownerBal += price;
            }
            String addBallsSql = "UPDATE USERS SET BALANCE = " + userBal + " WHERE ID = " + userId;
            String removeBallsSql = "UPDATE USERS SET BALANCE = " + ownerBal + " WHERE ID = " + cardRepository.getCard(cardId).getOwnerId();
            MapSqlParameterSource params2 = new MapSqlParameterSource();
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
            throw new NotFoundException("Такого пользователя не существует");
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
            throw new BadRequestException("Такого пользователя не существует");
        if(user.getId() == null)
            throw new NotFoundException("Такого объявления у этого пользователя не существует");
        else {
            String sql = "UPDATE USERS SET NAME = " + user.getName() + ", PIC_URL = " + user.getPicUrl() + ", BALANCE = " +
                    user.getBalance() + "WHERE ID = " + user.getId();
            MapSqlParameterSource params = new MapSqlParameterSource();
            jdbcTemplate.update(sql, params);
        }
    }
}