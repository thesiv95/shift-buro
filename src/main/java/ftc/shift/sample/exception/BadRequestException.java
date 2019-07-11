package ftc.shift.sample.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Если при обработке пользовательского запроса будет выброшено это исключение,
 * то пользователю вернётся ответ со статусом 404 (NOT_FOUND)
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
}
