package tr.com.sample.api.v1.common.security.exception;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import tr.com.common.security.exception.BaseAuthEntryPointForJwtException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author arif.erol
 */
public class CustomAuthEntryPointForJwtException extends BaseAuthEntryPointForJwtException {
    private static final Logger logger = LoggerFactory.getLogger(CustomAuthEntryPointForJwtException.class);

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        logger.error("[Custom] Yetki hatasi : {}", authException.getMessage());

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        final Map<String, Object> body = new HashMap<>();
        body.put("status", HttpServletResponse.SC_UNAUTHORIZED);
        body.put("hata", "[Custom] Erisim izniniz yok");
        body.put("aciklama", authException.getMessage());
        body.put("path", request.getServletPath());

        // İsterseniz burada farklı bir response body veya loglama yapabilirsiniz
        super.commence(request, response, authException); // Ortak davranışı da çağırabilirsiniz
    }
}
