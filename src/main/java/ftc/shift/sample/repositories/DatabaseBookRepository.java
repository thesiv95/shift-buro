package ftc.shift.sample.repositories;

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

        String createBookTableSql = "create table BOOKS (" +
                "ID       INT," +
                "NAME     VARCHAR(50)," +
                "PHONE    VARCHAR(20)," +
                "TASK     VARCHAR(255)" +
                "IS_ACTIVE    BOOLEAN" +
                ");";


        jdbcTemplate.update(createBookTableSql, new MapSqlParameterSource());

        // Заполним таблицы тестовыми данными
        createBook("userA", new Card(1,	'Георгий',	'+79139432282',	'Могу сходить за хлебом', true));

        createBook("userB", new Card(2,	'Марина',	'+79130002282',	'Прошу посидеть с ребенком', true));

        createBook("userC", new Card(3,	'Иван',	'+79139221788',	'Могу погулять с собакой', true);
    }

    @Override
    public void getAllBooks() {
        String sql = "select * from INFORMATION";

        MapSqlParameterSource params = new MapSqlParameterSource();

        jdbcTemplate.query(sql, params, cardExtractor);
    }


    @Override
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
