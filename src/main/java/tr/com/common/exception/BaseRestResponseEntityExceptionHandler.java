package tr.com.common.exception;

import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * Ortak (base) handler: MethodArgumentNotValidException için.
 * @author arif.erol
 */
public abstract class BaseRestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    protected Map<String, String> extractFieldErrors(MethodArgumentNotValidException ex) {
        return ex.getBindingResult().getFieldErrors().stream()
                .collect(Collectors.toMap(
                        fieldError -> fieldError.getField(),
                        fieldError -> fieldError.getDefaultMessage(),
                        (msg1, msg2) -> msg1));
    }
    // ...other shared methods if needed...
}
