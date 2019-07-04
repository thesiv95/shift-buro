package ftc.shift.sample.repositories;

import java.util.Collection;

/**
 * Интерфейс для получения данных по книгам
 */
public interface CardRepository {
  Collection<Card> getAllCards();
}
