package ftc.shift.sample.repositories;

import org.springframework.dao.DataAccessException;
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
    public List<Card> extractData(ResultSet rs) throws SQLException, DataAccessException {
        Map<String, Card> cards = new HashMap<>();

        while (rs.next()) {


            Card card;
            card.setId(rs.getInt("id"));
            card.setName(rs.getString("name"));
            card.setPhone(rs.getString("phone"));
            card.setTask(rs.getString("task"));
            card.setIsActive(rs.getBoolean("is_active"));

            cards.put(card);

        }


        }

        return new ArrayList<>(cards.values());
    }
}
