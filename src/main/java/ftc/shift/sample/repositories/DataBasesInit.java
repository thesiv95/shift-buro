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

        //return simpleDriverDataSource;
        if (dbExists == false) {
            userDB.initialize();
            cardDB.initialize();
        }

        //cardDB.addCard(new Card(1,	1,	"Могу сходить за хлебом", false));
        //cardDB.addCard(new Card(2,	2,	"Прошу посидеть с ребенком", false));
        //cardDB.addCard(new Card(3,	3,	"Могу погулять с собакой", false));

        //User a = new ftc.shift.sample.models.User("Georgy", "+79139432282", 50, 12, "s", "a", "a", "a");
        //User b = new ftc.shift.sample.models.User("Georgy", "+79139432282", 50, 12, "s", "a", "a", "a");
        //User c = new ftc.shift.sample.models.User("Georgy", "+79139432282", 50, 12, "s", "a", "a", "a");
        //userDB.addUser(a);
        //userDB.addUser(b);
        //userDB.deleteUser(2);

        //User d = userDB.getUser(a.getId());
        //userDB.addUser(new ftc.shift.sample.models.User(2, "Marina", "+79130002282", 50, 12, "s", "a", "a", "a"));
        //userDB.addUser(new User(3, "Ivan", "+79139221788",50, 12, "s", "a", "a", "a"));
        //cardDB.addCard(new Card(1,	1,	"Могу сходить за хлебом", false));

        userDB.changeBalance(50, 43,44);
    }


}
