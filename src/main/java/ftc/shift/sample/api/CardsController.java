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

  @GetMapping(CARDS_PATH + "/getAllCards")
  @ApiOperation(value = "Получение всех карточек")
  public ResponseEntity<List<Card>> getAllCards() {
    List<Card> cards = service.getAllCards();
    return ResponseEntity.ok(cards);
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

  //позволяет добавить новое объявление, принимает на вход экземпляр класса Card,
  //возвращет код завершения: 0 - все хорошо, объявление добавилось, 1 - объявление заданно некорректно(какое-то из полей не заполнено,
  //объявление не было добавлено), 2 - у автора объявления не достаточно денег на балнсе(объявление не добавлено);
  @PostMapping(CARDS_PATH + "/addCard")
  @ApiOperation(value = "Добавление новой карты")
  public ResponseEntity<Void> addCard(
          @ApiParam(value = "Данные для новой карты")
          @RequestBody Card card) {
    service.addCard(card);
    return ResponseEntity.ok().build();
  }

  @PostMapping(CARDS_PATH + "/updateStatus" + "/{cardId}")
  @ApiOperation(value = "обновление статуса карты")
  public ResponseEntity<Void> updateStatus(
          @ApiParam(value = "айди карты")
          @RequestHeader Integer userId,
          @ApiParam(value = "новый статус")
          @PathVariable Integer cardId) {
    service.updateStatus(userId, cardId);
    return ResponseEntity.ok().build();
  }


  @DeleteMapping(CARDS_PATH + "/deleteCard" + "/{cardId}")
  @ApiOperation(value = "удаление карты")
  public ResponseEntity<Void> deleteCard(
          @ApiParam(value = "айди карты")
          @PathVariable Integer cardId,
          @ApiParam(value = "айди пользователя")
          @RequestHeader Integer userId) {
    service.deleteCard(userId, cardId);
    return ResponseEntity.ok().build();
  }

  @GetMapping(CARDS_PATH + "/getTypedCards" + "/{type}")
  @ApiOperation(value = "Получение всех карточек")
  public ResponseEntity<List<Card>> getTypedCards(
          @ApiParam(value = "выбрать карточки определнной категории")
          @PathVariable String type) {
    List<Card> typedCards = service.getTypedCards(type);
    return ResponseEntity.ok(typedCards);
  }
}