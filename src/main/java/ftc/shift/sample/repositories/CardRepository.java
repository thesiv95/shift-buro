package ftc.shift.sample.repositories;

import ftc.shift.sample.models.Card;
import ftc.shift.sample.models.User;

import java.util.List;

/**
 * Интерфейс для получения данных по карточкам с желаниями
 */
public interface CardRepository {
    List<Card> getAllCards();
    Card getCard(Integer cardId);
    void updateStatus(Integer userId, Integer cardId);
    void addCard(Card card, User user);
    void deleteCard(Integer userId, Integer cardId);
    void initialize();
    List<Card> getTypedCards(String type);
}
