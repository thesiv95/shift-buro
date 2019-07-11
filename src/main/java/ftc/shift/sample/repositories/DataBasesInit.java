package ftc.shift.sample.repositories;

import ftc.shift.sample.models.Card;
import ftc.shift.sample.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import ftc.shift.sample.repositories.DatabaseUserRepository;
import ftc.shift.sample.repositories.DatabaseCardRepository;
import javax.annotation.PostConstruct;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

@Service
@ConditionalOnProperty(name = "use.database", havingValue = "true")
public class DataBasesInit {

    @Autowired
    UserRepository userDB;

    @Autowired
    CardRepository cardDB;

    @PostConstruct
    public void initialize () {

        boolean dbExists = false;
        SimpleDriverDataSource simpleDriverDataSource = new SimpleDriverDataSource();
        simpleDriverDataSource.setDriverClass(org.h2.Driver.class);
        simpleDriverDataSource.setUsername("sa");
        simpleDriverDataSource.setPassword("");
        Connection conn = null;
        try{
            conn = DriverManager.getConnection("jdbc:h2:~/shiftburodb;IFEXISTS=TRUE","sa","");
            dbExists =true;
        }
        catch(Exception e){
            dbExists = false;
        }
        simpleDriverDataSource.setUrl("jdbc:h2:~/shiftburodb;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE");
        if (dbExists == false) {
            userDB.initialize();
            cardDB.initialize();
        }
        //userDB.deleteUser(3);
        User user = new User("123", null, 51);
        userDB.addUser(user);
    }
}

