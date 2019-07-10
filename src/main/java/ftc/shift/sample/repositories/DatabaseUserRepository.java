package ftc.shift.sample.repositories;

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
import java.util.List;

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

    // Добавить пользователя
    public String addUser(User user){
        if (user == null)
            return "Некорректный пользователь";
        else {
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
            return "Успешно";
        }

    }

    public String changeBalance(Integer price, Integer recipientId, Integer donorId) {
        User recip = getUser(recipientId);
        User donor = getUser(donorId);
        if (price < 50)
            return "Нукорректная цена";
        if (donor.getBalance() < price)
            return "ты бомж, у тебя нет денег";
        if (recipientId == donorId)
            return "сам у себя покупаешь дурачок чи що";
        else {
            Integer recBal = recip.getBalance() + price;
            Integer donBal = donor.getBalance() - price;

            // добавляем
            String addBallsSql = "UPDATE USERS SET BALANCE = " + recBal + " WHERE ID = " + recipientId;

            // Снимаем баллы у донора
            String removeBallsSql = "UPDATE USERS SET BALANCE = " + donBal + " WHERE ID = " + donorId;

            MapSqlParameterSource params2 = new MapSqlParameterSource()
                    .addValue("price", price)
                    .addValue("recipientId", recipientId)
                    .addValue("donorId", donorId);

            jdbcTemplate.update(addBallsSql, params2);
            jdbcTemplate.update(removeBallsSql, params2);
            return "Успешно";
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

    public String deleteUser(Integer userId) {
        if (getUser(userId) == null)
            return "Пользователь не найден";
        String sql = "DELETE FROM USERS WHERE USERS.ID=:userId";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("userId", userId);
         jdbcTemplate.update(sql, params);
         return "успешно";
    }

    public String updateUser(User user) {
        if(user == null)
            return "пользователь не задан";
        if(user.getId() == null)
            return "пользователь не существует";
        else {
            //String sql = "UPDATE USERS SET NAME:=name, PIC_URL:=picUrl, BALANCE:=balance WHERE ID:=userId";
            String sql = "UPDATE USERS SET NAME = " + user.getName() + ", PIC_URL = " + user.getPicUrl() + ", BALANCE = " +
                    user.getBalance() + "WHERE ID = " + user.getId();
            MapSqlParameterSource params = new MapSqlParameterSource();
            jdbcTemplate.update(sql, params);
            return "успешно";
        }
    }
}