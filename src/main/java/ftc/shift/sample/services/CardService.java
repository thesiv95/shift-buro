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

  public Card addCard(Card card) { return this.cardRepository.addCard(card); }
}

