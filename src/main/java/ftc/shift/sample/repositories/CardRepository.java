package ftc.shift.sample.repositories;

import ftc.shift.sample.models.Card;

import java.util.List;

/**
 * Интерфейс для получения данных по карточкам с желаниями
 */
public interface CardRepository {
    List<Card> getAllCards();
    Card getCard(Integer cardId);
    String updateStatus(Integer id, Boolean status);
    String addCard(Card card);
    String deleteCard(Integer cardId);
    void initialize();
    List<Card> getTypedCards(String type);
}
