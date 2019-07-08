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
        ;
        String createGenerateBookIdSequenceSql = "create sequence USER_ID_GENERATOR";


        String createUsersTableSql = "create table USERS (" +
                "ID       INT PRIMARY KEY AUTO_INCREMENT," +
                "NAME     VARCHAR(50)," +
                "PHONE    VARCHAR(20)," +
                "AGE      VARCHAR(20),"+
                "CITY     VARCHAR(20)," +
                "PIC_URL  VARCHAR(255)," +
                "STATUS   VARCHAR(50)," +
                "DESCRIPTION VARCHAR(255)," +
                "BALANCE  INT);";


        jdbcTemplate.update(createUsersTableSql, new MapSqlParameterSource());

        //new ftc.shift.sample.models.User(1, "Georgy", "+79139432282", 50, 12, "s", "a", "a", "a");
        //new ftc.shift.sample.models.User(2, "Marina", "+79130002282", 50, 12, "s", "a", "a", "a");
        //new ftc.shift.sample.models.User(3, "Ivan", "+79139221788",50, 12, "s", "a", "a", "a");

    }

    public List<User> getAllUsers() {
        String sql = "select * from USERS";

        MapSqlParameterSource params = new MapSqlParameterSource();

        List<User> all = jdbcTemplate.query(sql, params, userExtractor);
        return all;
    }

    // Добавить пользователя
    public User addUser(User user){

        String insertUserSql = "insert into USERS (NAME, PHONE, AGE, CITY, PIC_URL, STATUS, DESCRIPTION, BALANCE) " +
                "values (?, ?, ?, ?, ?, ?, ?, ?)";
        KeyHolder holder = new GeneratedKeyHolder();

        jdbcTemplate.getJdbcTemplate().update(new PreparedStatementCreator() {

            @Override
            public PreparedStatement createPreparedStatement(Connection connection)
                    throws SQLException {
                PreparedStatement ps = connection.prepareStatement(insertUserSql.toString(),
                        Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, user.getName());
                ps.setString(2, user.getPhone());
                ps.setInt(3, user.getAge());
                ps.setString(4, user.getCity());
                ps.setString(5, user.getPic_url());
                ps.setString(6, user.getStatus());
                ps.setString(7, user.getDescription());
                ps.setInt(8, user.getBalance());
                return ps;
            }
        }, holder);

        Integer newPersonId = holder.getKey().intValue();
        user.setId(newPersonId);
        return user;
    }

    public void changeBalance(Integer price, Integer recipientId, Integer donorId) {
        Integer recBal = this.getUser(recipientId).getBalance() + price;
        Integer donBal = this.getUser(donorId).getBalance() - price;

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
        String sql = "DELETE FROM USERS WHERE USERS.ID=:userId";

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("userId", userId);
        jdbcTemplate.update(sql, params);
    }
}
