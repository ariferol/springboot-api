

/**
 * VALIDATION'I DEVELOPER'A KOD YAZDIRMADAN ÇÖZMEK
 * ------------------------------------------------
 * Spring Validation ile custom annotation (@TCKimlikNo) ve validator (TCKimlikNoValidator) tanımlanır.
 * Böylece, entity veya DTO'da sadece alanın üstüne @TCKimlikNo yazmak yeterli olur, ek kod yazmaya gerek kalmaz.
 *
 * ÖRNEK KULLANIM:
 * public class UserRegisterReq {
 *     @TCKimlikNo
 *     private String tcKimlikNo;
 * }
 *
 * Böylece, controller'a gelen request otomatik olarak validasyondan geçer.
 */
package tr.com.common.core.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

/**
 * @author arif.erol
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TCKimlikNoValidator.class)
public @interface TCKimlikNo {
    String message() default "T.C Kimlik Numarası Doğrulanamadı!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
