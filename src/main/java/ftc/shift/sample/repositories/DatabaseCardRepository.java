package ftc.shift.sample.repositories;

import ftc.shift.sample.models.Card;
import ftc.shift.sample.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.validation.constraints.Null;
import java.util.List;

/**
 * ВСЯ ЛОГИКА в приложении
 */
@Repository
@ConditionalOnProperty(name = "use.database", havingValue = "true")
public class DatabaseCardRepository implements CardRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    private CardExtractor cardExtractor;

    @Autowired
    private UserExtractor userExtractor;

    public void initialize() {
        // Подразумевается, что H2 работает в in-memory режиме и таблицы необходимо создавать при каждом старте приложения
        // SQL запросы для создания таблиц       ;

        String createCardsTableSql = "create table INFORMATION (" +
                "ID INT," +
                "USER_ID INT," +
                "TASK VARCHAR(255)," +
                "IS_ACTIVE BOOLEAN," +
                "FOREIGN KEY (USER_ID) REFERENCES USERS(ID)" +
                ");";

        jdbcTemplate.update(createCardsTableSql, new MapSqlParameterSource());

        // Заполним таблицы тестовыми данными
        //new Card(1,	1,	"Могу сходить за хлебом", false);

        //new Card(2,	2,	"Прошу посидеть с ребенком", false);

        //new Card(3,	3,	"Могу погулять с собакой", false);

    }



    // Загрузить все карточки
    public List<Card> getAllCards() {
        String sql = "select * from INFORMATION";

        MapSqlParameterSource params = new MapSqlParameterSource();

        List<Card> all = jdbcTemplate.query(sql, params, cardExtractor);
        return all;
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
    /*
    public void changeBalance(int price, int recipientId, int donorId) {
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
     */

    public Boolean addCard(Card card){

        String insertCardSql = "insert into INFORMATION (ID, USER_ID, TASK, IS_ACTIVE) values (:id, :userId, :task, :isActive)";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", card.getId())
                .addValue("userId", card.getUserId())
                .addValue("task", card.getTask())
                .addValue("isActive", card.getIsActive());

        return jdbcTemplate.update(insertCardSql, params) != 0;

    }

    public Card getCard(Integer id) {
        String sql = "SELECT * FROM INFORMATION WHERE INFORMATION.ID=:id";

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("userId", id);

        List<Card> cards = jdbcTemplate.query(sql, params, cardExtractor);

        if (cards.isEmpty()) { return null; }

        return cards.get(0);
    }
}
