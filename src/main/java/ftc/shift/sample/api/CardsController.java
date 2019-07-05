package ftc.shift.sample.api;


import ftc.shift.sample.models.Card;
import ftc.shift.sample.services.CardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(description = "Запросы для работы с карточками желаний")
public class CardsController {
  private static final String CARDS_PATH = "/cards";

  @Autowired
  private CardService service;

  @GetMapping(CARDS_PATH)
  @ApiOperation(value = "Получение всех карточек")
  public ResponseEntity<List<Card>> getAllCards() {
    List<Card> card = service.getAllCards();
    return ResponseEntity.ok(card);
  }


}