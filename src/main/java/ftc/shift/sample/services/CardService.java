package ftc.shift.sample.services;

import ftc.shift.sample.repositories.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class CardService {

  private final CardRepository cardRepository;

  @Autowired
  public CardService(CardRepository cardRepository) {
    this.cardRepository = cardRepository;
  }


  public Collection<Card> provideCards() {

    return cardRepository.getAllCards();
  }
}
