package ftc.shift.sample.api;


import ftc.shift.sample.models.Card;
import ftc.shift.sample.services.CardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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

  @GetMapping(CARDS_PATH + "/{id}")
  @ApiOperation(value = "Получение карточки по ее айди")
  public ResponseEntity<Card> getCard(
          //@ApiParam(value = "Идентификатор пользователя")
          //@RequestHeader("userId") String userId,
          @ApiParam(value = "Идентификатор книги")
          @PathVariable Integer id) {
    Card card = service.getCard(id);
    return ResponseEntity.ok(card);
  }

  @PostMapping(CARDS_PATH)
  @ApiOperation(value = "Добавление новой карты")
  public ResponseEntity<Boolean> addCard(
          @ApiParam(value = "Данные для новой карты")
          @RequestBody Card card) {
    Boolean result = service.addCard(card);
    return ResponseEntity.ok(result);
  }


}