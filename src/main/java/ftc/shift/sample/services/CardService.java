package ftc.shift.sample.services;

import ftc.shift.sample.models.Card;
import ftc.shift.sample.repositories.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;



@Service
public class CardService {

  private final CardRepository cardRepository;

  @Autowired
  public CardService(CardRepository cardRepository) {
    this.cardRepository = cardRepository;
  }

  public List<Card> getAllCards(){
    return this.cardRepository.getAllCards();
  }

  public Card getCard(Integer id) { return this.cardRepository.getCard(id); }

  public String addCard(Card card) { return this.cardRepository.addCard(card); }

  public String updateStatus(Integer cardId, Boolean status) { return this.cardRepository.updateStatus(cardId, status); }

  public String deleteCard(Integer cardId) { return this.cardRepository.deleteCard(cardId); }

  public List<Card> getTypedCards (String type) {return this.cardRepository.getTypedCards(type); }
}


