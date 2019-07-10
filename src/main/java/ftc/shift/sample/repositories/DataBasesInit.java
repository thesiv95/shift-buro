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
            conn = DriverManager.getConnection("jdbc:h2:~/shiftburodb1;IFEXISTS=TRUE","sa","");
            dbExists =true;

        }
        catch(Exception e){
            dbExists = false;
        }
        simpleDriverDataSource.setUrl("jdbc:h2:~/shiftburodb1;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE");
        if (dbExists == false) {
            userDB.initialize();
            cardDB.initialize();
            userDB.addUser(new User("Николай", "https://pp.userapi.com/c836333/v836333001/31192/y1Cm4JfplhQ.jpg", 100 ));
            userDB.addUser(new User("Елена", "https://sun4-15.userapi.com/c846320/v846320081/160c6d/xmTozuo4qIw.jpg", 100 ));
            userDB.addUser(new User("Мария", "https://sun4-12.userapi.com/c851528/v851528630/12de40/ONt4JmmLChA.jpg", 100 ));
            //Card card = new Card(123,"123","123","123","123",true,123,"123","123");
            //cardDB.addCard(card);
        }
        //User user = userDB.getUser(1220);

        //Card card2 = new Card(3,"222","помощь","222","222",true,222,"222","222");
        //for (int i = 0; i < 5; i++) {
        cardDB.addCard(new Card(1,"Николай","просьба","Могу погулять с собакой","Опыт 5 лет",true,10,"Новосибирск","+79038971122"));
        cardDB.addCard(new Card(2,"Елена","просьба","Решу пример по математике","Услуги репетитора",false,20,"Новосибирск","+79030071122"));
        cardDB.addCard(new Card(3,"Маша","просьба","Выслушаю ваши проблемы","Опыт психолога 5 лет",true,20,"Москва","+79038971000"));
        //    cardDB.addCard(card2);
        //}
        //List<Card> typedCards = cardDB.getTypedCards("просьба");
    }
}
