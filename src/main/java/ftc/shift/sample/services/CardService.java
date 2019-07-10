package ftc.shift.sample.services;

import ftc.shift.sample.models.Card;
import ftc.shift.sample.models.User;
import ftc.shift.sample.repositories.CardRepository;
import ftc.shift.sample.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;



@Service
public class CardService {

  private final CardRepository cardRepository;

  private final UserRepository userRepository;

  @Autowired
  public CardService(CardRepository cardRepository, UserRepository userRepository) {
    this.cardRepository = cardRepository;
    this.userRepository = userRepository;
  }

  public List<Card> getAllCards(){
    return this.cardRepository.getAllCards();
  }

  public Card getCard(Integer id) { return this.cardRepository.getCard(id); }

  public void addCard(Card card) {
    User user = userRepository.getUser(card.getOwnerId());
    this.cardRepository.addCard(card, user);
  }

  public void updateStatus(Integer userId, Integer cardId) { this.cardRepository.updateStatus(userId, cardId); }

  public void deleteCard(Integer userId, Integer cardId) { this.cardRepository.deleteCard(userId, cardId); }

  public List<Card> getTypedCards (String type) {return this.cardRepository.getTypedCards(type); }
}


