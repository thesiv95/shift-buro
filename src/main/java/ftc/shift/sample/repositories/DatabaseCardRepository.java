package ftc.shift.sample.repositories;

import ftc.shift.sample.models.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Реализиция, хранящая все данные в БД
 */
@Repository
@ConditionalOnProperty(name = "use.database", havingValue = "true")
public class DatabaseCardRepository implements CardRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    private CardExtractor cardExtractor;

    @PostConstruct
    public void initialize() {
        // Подразумевается, что H2 работает в in-memory режиме и таблицы необходимо создавать при каждом старте приложения
        // SQL запросы для создания таблиц
       ;

        String createCardsTableSql = "create table INFORMATION (" +
                "ID       INT," +
                "USER_ID  INT," +
                "TASK     VARCHAR(255)," +
                "IS_ACTIVE    BOOLEAN," +
                "FOREIGN KEY (USER_ID) REFERENCES USERS(ID)" +
                ");";

        String createUsersTableSql = "create table USERS (" +
                "ID       INT PRIMARY KEY," +
                "NAME     VARCHAR(50);" +
                "PHONE    VARCHAR(20)," +
                "AGE      VARCHAR(20),"+
                "CITY     VARCHAR(20)," +
                "PIC_URL  VARCHAR(255)," +
                "STATUS   VARCHAR(50);" +
                "DESCRIPTION VARCHAR(255);" +
                "BALANCE  INT";


        jdbcTemplate.update(createUsersTableSql, new MapSqlParameterSource());
        jdbcTemplate.update(createCardsTableSql, new MapSqlParameterSource());

        // Заполним таблицы тестовыми данными
        new Card(1,	1,	"Могу сходить за хлебом", false);

        new Card(2,	2,	"Прошу посидеть с ребенком", false);

        new Card(3,	3,	"Могу погулять с собакой", false);

        // НЕ ИМПОРТИРУЕТСЯ ПРАВИЛЬНО КЛАСС!!!!!!111!!!!
        new ftc.shift.sample.models.User(1, "Georgy", "+79139432282", 50, 12, "s", "a", "a", "a");
        new ftc.shift.sample.models.User(2, "Marina", "+79130002282", 50, 12, "s", "a", "a", "a");
        new ftc.shift.sample.models.User(3, "Ivan", "+79139221788",50, 12, "s", "a", "a", "a");

    }



    // Загрузить все карточки
    public List<Card> getAllCards() {
        String sql = "select * from INFORMATION";

        MapSqlParameterSource params = new MapSqlParameterSource();

        jdbcTemplate.query(sql, params, cardExtractor);
        return null;
    }


    // Пометить заявку как Выполнено/не выполнено
    // по id пользователя
    public void updateStatus(Integer id, Boolean status) {

        String updateCardStatusSql = "UPDATE `information` SET `status`= "
                + status + "WHERE `id` = " + id;

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", id)
                .addValue("status", status);

        jdbcTemplate.update(updateCardStatusSql, params);

    }

    // Изменение баллов
    private void changeBalance(int price, int recipientId, int donorId){
        // Добавляем баллы к получателю
        String addBallsSql = "UPDATE `users` SET `balance` " +
                "= `balance` + " + price + " WHERE `id` = " + recipientId;

        // Снимаем баллы у донора
        String removeBallsSql = "UPDATE `users` SET `balance` " +
                "= `balance` - " + price + " WHERE `id` = " + donorId;

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("price", price)
                .addValue("recipientId", recipientId)
                .addValue("donorId", donorId);

        jdbcTemplate.update(addBallsSql, params);
        jdbcTemplate.update(removeBallsSql, params);

    }
}
