package ftc.shift.sample.repositories;

import ftc.shift.sample.models.User;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class UserExtractor implements ResultSetExtractor<List<User>> {
    @Override
    public List<User> extractData(ResultSet rs) throws SQLException {
        List<User> users = new ArrayList<>();

        while (rs.next()) {

            User user = new User();

            user.setId(rs.getInt("id"));
            user.setName(rs.getString("name"));
            user.setPhone(rs.getString("phone"));
            user.setBalance(rs.getInt("balance"));
            user.setAge(rs.getInt("age"));
            user.setCity(rs.getString("city"));
            user.setPic_url(rs.getString("pic_url"));
            user.setStatus(rs.getString("status"));
            user.setDescription(rs.getString("description"));
            users.add(user);
        }

        return users;
    }
}