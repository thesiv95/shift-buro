package ftc.shift.sample.api;


import ftc.shift.sample.services.CardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * Все аннотации, начинающиеся с @Api нужны только для построения <a href="http://localhost:8081/swagger-ui.html#/">swagger-документации</a>
 * Их удаление ничего не сломает.
 */
@RestController
@Api(description = "Запросы для работы с карточками желаний")
public class CardsController {

  private static final String CARDS_PATH = "/cards";

  @Autowired
  private CardService service;

  @GetMapping(CARDS_PATH)
  @ApiOperation(value = "Получение всех карточек")
  public ResponseEntity<Collection<Card>> listCards(
          @ApiParam(value = "Идентификатор пользователя")
          @RequestHeader("userId") String userId) {
    Collection<Card> cards = service.provideCards(userId);
    return ResponseEntity.ok(cards);
  }
}