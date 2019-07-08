package ftc.shift.sample.repositories;

import ftc.shift.sample.models.Card;

import java.util.List;

/**
 * Интерфейс для получения данных по карточкам с желаниями
 */
public interface CardRepository {
    List<Card> getAllCards();
    //void changeBalance(int price, int recipientId, int donorId);
    Card getCard(Integer id);
    void updateStatus(Integer id, Boolean status);
    Boolean addCard(Card card);
    void initialize();

}
