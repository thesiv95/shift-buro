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


        // Demo-data
        String[] cities = new String[]{"Москва", "Санкт-Петербург", "Новосибирск"};

        User u1 = new User("Марина", "https://sun4-17.userapi.com/c845121/v845121075/ab5ae/YBZSUcDkTLw.jpg", 100);
        userDB.addUser(u1);
        User u2 = new User("Николай", "https://sun4-16.userapi.com/c848616/v848616895/1d8f61/qVhrdccO5-c.jpg", 100);
        userDB.addUser(u2);
        User u3 = new User("Анатолий", "https://sun4-17.userapi.com/c850224/v850224067/db0a6/HUr1xk4qxSU.jpg", 100);
        userDB.addUser(u3);
        Card c1 = new Card(2, "Владислав", "Предложение", "Присмотрю за питомцами", "Присмотрю за вашими домашними животными, пока вы в отпуске", true, 50, cities[2], "+79131234567");
        cardDB.addCard(c1, u1);
        Card c2 = new Card(1, "Марина", "Просьба", "Сделать уборку в квартире", "Пожалуйста, помогите сделать уборку в квартире пожилого человека", false, 60, cities[2], "+79131234576");
        cardDB.addCard(c2, u2);
        Card c3 = new Card(3, "Елена", "Просьба", "Решить пример по физике (11 класс)", "Мой сын был бы рад помощи, очень сложный пример задачи для подготовки к ЕГЭ", true, 70, cities[0], "+79131234111");
        cardDB.addCard(c3, u3);
        Card c4 = new Card(2, "Николай", "Предложение", "Предоставлю услуги переводчика с английского", "Переведу статью, рецензию, заметку и так далее", false, 100, cities[1], "+79131114123");
        cardDB.addCard(c4, u3);


    }
}

