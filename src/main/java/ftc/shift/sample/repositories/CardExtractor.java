package ftc.shift.sample.repositories;

import ftc.shift.sample.models.Card;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CardExtractor implements ResultSetExtractor<List<Card>> {
    @Override
    public List<Card> extractData(ResultSet rs) throws SQLException {
        List<Card> cards = new ArrayList<>();

        while (rs.next()) {

            Card card = new Card();
            card.setId(rs.getInt("id"));
            card.setUserId(rs.getInt("userId"));
            card.setTask(rs.getString("task"));
            card.setIsActive(rs.getBoolean("is_active"));

            cards.add(card);
        }

        return cards;
    }
}
