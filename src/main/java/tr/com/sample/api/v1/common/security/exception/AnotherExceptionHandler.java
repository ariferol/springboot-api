package tr.com.sample.api.v1.common.security.exception;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author arif.erol
 */
@ControllerAdvice
@Order(2) // Ortak ve custom handlerlardan sonra çalışır
public class AnotherExceptionHandler {
    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException exception) {
        // Burada farklı bir loglama veya response dönebilirsiniz
        return new ResponseEntity<>(
                "[AnotherExceptionHandler] Illegal argument: " + exception.getMessage(),
                HttpStatus.BAD_REQUEST);
    }

    // İsterseniz başka exception handler metotları da ekleyebilirsiniz
}
