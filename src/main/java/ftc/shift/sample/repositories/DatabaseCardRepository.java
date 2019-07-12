package ftc.shift.sample.repositories;

import com.sun.org.apache.xpath.internal.operations.Bool;
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
import javax.validation.constraints.Null;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
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
    public void initialize() {
        // Подразумевается, что H2 работает в in-memory режиме и таблицы необходимо создавать при каждом старте приложения
        // SQL запросы для создания таблиц       ;
        //crate table if not exist ADS
        String createCardsTableSql = "create table ADS (" +
                "ID INT PRIMARY KEY AUTO_INCREMENT," +
                "TYPE VARCHAR(255)," +
                "TASK VARCHAR(255)," +
                "OWNER_NAME VARCHAR(255)," +
                "PHONE VARCHAR(50)," +
                "CITY VARCHAR(50)," +
                "DESCRIPTION VARCHAR(255)," +
                "PRICE INT," +
                "STATUS BOOLEAN," +
                "OWNER_ID INT," +
                "FOREIGN KEY (OWNER_ID) REFERENCES USERS(ID)" +
                ");";
        jdbcTemplate.update(createCardsTableSql, new MapSqlParameterSource());
    }


    // Загрузить все карточки
    public List<Card> getAllCards() {
        String sql = "select * from ADS";

        MapSqlParameterSource params = new MapSqlParameterSource();

        List<Card> all = jdbcTemplate.query(sql, params, cardExtractor);
        return all;
    }
    public void updateStatus(Integer userid, Integer cardid) {
        if (userid == getCard(cardid).getOwnerId())
            throw new BadRequestException("Самому себе нельзя перечислить баллы");
        Boolean newStatus = !(getCard(cardid).getStatus());
        String updateCardStatusSql = "UPDATE ADS SET STATUS = "
                + newStatus + " WHERE ID = " + cardid;
        MapSqlParameterSource params = new MapSqlParameterSource();
        jdbcTemplate.update(updateCardStatusSql, params);
    }



    private Boolean checkCard(Card card) {
        if (card.getStatus() == null || card.getType() == null ||
            card.getTask() == null || card.getCity() == null || card.getDescription() == null ||
            card.getPhone() == null || card.getOwnerId() == null || card.getOwnerName() == null ||
            card.getPrice() < 50 || !checkPhone(card.getPhone()))
            return false;
        else return true;
    }

    private Boolean checkPhone(String string) {

        if (Character.toString(string.charAt(0)).equals("+") && Character.toString(string.charAt(1)).equals("7") && string.length() == 12)
            return true;
        else throw new BadRequestException("Телефон задан некорректно. Формат +7ХХХХХХХХХХ");
    }

    private Boolean userBalanceIsValid (Card card, User user) {
        if (card.getType().equals("Предложение"))
            return true;
        String sql = "SELECT * FROM ADS WHERE ADS.OWNER_ID=:id";
        Integer ownerId = card.getOwnerId();
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", ownerId);
        List<Card> cards = jdbcTemplate.query(sql, params, cardExtractor);
        Integer totalCount = card.getPrice();
        for (int i = 0; i < cards.size(); i++) {
            if (cards.get(i).getType().equals("Просьба"))
                if(cards.get(i).getStatus() == true)
                totalCount += cards.get(i).getPrice();
        }
        if (totalCount > user.getBalance())
            throw new BadRequestException("Не хватает баллов");
        else return true;
    }

    public void addCard(Card card, User user) {
        if (!checkCard(card))
            throw new BadRequestException("Такого объявления не существует");
        if (card.getPrice() < 50)
            throw new BadRequestException("Цена не может быть меньше 50 баллов");
        if (userBalanceIsValid(card, user)) {
            String insertUserSql = "INSERT INTO ADS (TYPE, TASK, OWNER_NAME, PHONE, CITY, DESCRIPTION, PRICE, STATUS, OWNER_ID)" +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            KeyHolder holder = new GeneratedKeyHolder();
            jdbcTemplate.getJdbcTemplate().update(new PreparedStatementCreator() {
                @Override
                public PreparedStatement createPreparedStatement(Connection connection)
                        throws SQLException {
                    PreparedStatement ps = connection.prepareStatement(insertUserSql.toString(),
                            Statement.RETURN_GENERATED_KEYS);
                    ps.setString(1, card.getType());
                    ps.setString(2, card.getTask());
                    ps.setString(3, card.getOwnerName());
                    ps.setString(4, card.getPhone());
                    ps.setString(5, card.getCity());
                    ps.setString(6, card.getDescription());
                    ps.setInt(7, card.getPrice());
                    ps.setBoolean(8, card.getStatus());
                    ps.setInt(9, user.getId());
                    return ps;
                }
            }, holder);
            Integer newCardId = holder.getKey().intValue();
            card.setId(newCardId);
        }
    }

    public Card getCard(Integer cardId) {
        String sql = "SELECT * FROM ADS WHERE ADS.ID=:id";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", cardId);
        List<Card> cards = jdbcTemplate.query(sql, params, cardExtractor);
        if (cards.isEmpty()) {
            throw new NotFoundException("Такого объявления не существует");
        }
        return cards.get(0);
    }
    //
    //
    public void deleteCard(Integer userId, Integer cardId) {
        if (getCard(cardId) == null)
            throw new NotFoundException("Такого объявления не существует");
        if(userId != getCard(cardId).getOwnerId())
            throw new BadRequestException("Нельзя удалять чужие объявления!");
        else {
            String sql = "DELETE FROM ADS WHERE ID=:cardId";
            MapSqlParameterSource params = new MapSqlParameterSource()
                    .addValue("cardId", cardId);
            jdbcTemplate.update(sql, params);
        }
    }

    public List<Card> getTypedCards(String type) {
        String sql = "SELECT * FROM ADS WHERE ADS.TYPE=:type";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("type", type);
        List<Card> cards = jdbcTemplate.query(sql, params, cardExtractor);
        if (cards.isEmpty()) { return null; }
        return cards;
    }
}
