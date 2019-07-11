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
        // Test values
        User test_user = new User("Дионисий", "https://sun4-12.userapi.com/c850724/v850724998/8f179/vmSHXqPLQCY.jpg", 100);
        userDB.addUser(test_user);
        User another_test_user = new User("Агафон", "https://sun4-12.userapi.com/c837638/v837638684/33f18/fxjVOY6D3yk.jpg", 150);
        userDB.addUser(another_test_user);
        Card test_card = new Card(1, "Диониссий", "Просьба", "Займите полтос", "до зарплаты плез", true, 10, "Новосибирск", "+79130092292");
        cardDB.addCard(test_card, test_user);
        Card another_test_card = new Card(2, "Микола", "Помощь", "Достану ответы на ЕГЭ (Гарвард)", "Более 99 лет на рынке США", false, 20, "Лас-Вегас", "02");
        cardDB.addCard(another_test_card, another_test_user);
    }
}
