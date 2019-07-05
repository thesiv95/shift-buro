package ftc.shift.sample.repositories;

import ftc.shift.sample.models.Card;

import java.util.List;

/**
 * Интерфейс для получения данных по карточкам с желаниями
 */
public interface CardRepository {
    List<Card> getAllCards();
}
