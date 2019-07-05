package ftc.shift.sample.repositories;

import ftc.shift.sample.models.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Реализиция, хранящая все данные в БД
 */
@Repository
@ConditionalOnProperty(name = "use.database", havingValue = "true")
public class DatabaseCardRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    private CardExtractor cardExtractor;

    @PostConstruct
    public void initialize() {
        // Подразумевается, что H2 работает в in-memory режиме и таблицы необходимо создавать при каждом старте приложения
        // SQL запросы для создания таблиц
       ;

        String createCardsTableSql = "create table BOOKS (" +
                "ID       INT," +
                "NAME     VARCHAR(50)," +
                "PHONE    VARCHAR(20)," +
                "TASK     VARCHAR(255)" +
                "IS_ACTIVE    BOOLEAN" +
                ");";

        String createUsersTableSql = "create table USERS (" +
                "ID       INT," +
                "NAME     VARCHAR(50);";

        jdbcTemplate.update(createCardsTableSql, new MapSqlParameterSource());
        jdbcTemplate.update(createUsersTableSql, new MapSqlParameterSource());

        // Заполним таблицы тестовыми данными
        new Card(1,	"Георгий",	"+79139432282",	"Могу сходить за хлебом", true);

        new Card(2,	"Марина",	"+79130002282",	"Прошу посидеть с ребенком", true);

        new Card(3,	"Иван",	"+79139221788",	"Могу погулять с собакой", true);

        new User(1, "Georgy");
        new User(2, "Marina");
        new User(3, "Ivan");

    }




    public void getAllCards() {
        String sql = "select * from INFORMATION";

        MapSqlParameterSource params = new MapSqlParameterSource();

        jdbcTemplate.query(sql, params, cardExtractor);
    }



    public void updateStatus(Integer id, Boolean status) {
        // 1) Обновляем информацию о книге
        String updateCardStatusSql = "update BOOKS " +
                "set STATUS =  " + status +
                "where BOOK_ID = " + id;

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", id)
                .addValue("status", status);

        jdbcTemplate.update(updateCardStatusSql, params);

    }
}
