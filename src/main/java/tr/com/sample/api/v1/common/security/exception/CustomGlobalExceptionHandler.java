package tr.com.sample.api.v1.common.security.exception;

import io.jsonwebtoken.JwtException;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import tr.com.common.core.base.ResponseDTO;
import tr.com.common.core.util.ExceptionUtilBean;
import tr.com.common.exception.BaseGlobalExceptionHandler;

/**
 * @author arif.erol
 */
@ControllerAdvice
@Order(1) // Bu handler'ın önceliğini artırır
public class CustomGlobalExceptionHandler extends BaseGlobalExceptionHandler {
    @Override
    @ExceptionHandler({JwtException.class})
    public ResponseEntity<Object> handleJwtException(JwtException exception) {
        ExceptionUtilBean.addExceptionLog(exception);
        return new ResponseEntity<>(ResponseDTO.Builder.newInstance()
                .data("[Custom] JWT token dogrulanamadi.")
                .description("[Custom] JWT token bilgisi dogrulanamadi, lutfen login olduktan sonra tekrar deneyiniz.")
                .statusCode(HttpStatus.UNAUTHORIZED.value())
                .build(), HttpStatus.UNAUTHORIZED);
    }

    @Override
    @ExceptionHandler({AccessDeniedException.class})
    public ResponseEntity<Object> handleAccessDeniedException(AccessDeniedException exception) {
        ExceptionUtilBean.addExceptionLog(exception);
        return new ResponseEntity<>(ResponseDTO.Builder.newInstance()
                .data("[Custom] Islem yapmak icin yetkiniz bulunmamaktadir.")
                .description("[Custom] Islem yapmak icin yetkiniz bulunmamaktadir, lutfen sistem yoneticisi ne basvurunuz")
                .statusCode(HttpStatus.UNAUTHORIZED.value())
                .build(), HttpStatus.UNAUTHORIZED);
    }

    @Override
    @ExceptionHandler({BadCredentialsException.class})
    public ResponseEntity<Object> handleBadCredentialException(BadCredentialsException exception) {
        ExceptionUtilBean.addExceptionLog(exception);
        return new ResponseEntity<>(ResponseDTO.Builder.newInstance()
                .data("[Custom] Kullanici bilgisi bulunamadi.")
                .description("[Custom] Kullanici bilgisi bulunamadi, lutfen sistem yoneticisi ne basvurunuz")
                .statusCode(HttpStatus.NO_CONTENT.value())
                .build(), HttpStatus.OK);
    }

    @Override
    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<Object> handleRuntimeException(RuntimeException exception) {
        ExceptionUtilBean.addExceptionLog(exception);
        return new ResponseEntity<>(ResponseDTO.Builder.newInstance()
                .data("[Custom] Hata: " + exception.getMessage())
                .description("[Custom] Calisma zamani hatasi, lutfen sistem yoneticisi ne basvurunuz")
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
