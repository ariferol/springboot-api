
package tr.com.common.core.aop;

/**
 * SPRING AOP NEDİR?
 * -------------------
 * Aspect Oriented Programming (AOP), uygulamanın iş mantığından bağımsız olarak çalışması gereken çapraz kesen (cross-cutting-concern) konuları
 * (ör: loglama, validation, güvenlik, transaction yönetimi gibi) ana iş kodundan ayırmamızı sağlar. Spring AOP, bu işlemleri aspect, advice ve pointcut kavramlarıyla yönetir.
 *
 * NASIL İMPLEMENTE EDİLİR?
 * ------------------------
 * 1. pom.xml dosyasına spring-boot-starter-aop bağımlılığı eklenir.
 * 2. @Aspect anotasyonu ile bir sınıf tanımlanır.
 * 2. @Before, @After, @AfterReturning, @Around gibi anotasyonlarla hangi metot(lar)dan önce/sonra çalışacağı belirlenir.
 * 3. Pointcut ifadesiyle hangi metotların etkileneceği seçilir.
 * 4. Aspect sınıfı @Component ile Spring bean olarak tanımlanır.
 *
 * ÖRNEK: Bu sınıf, @RestController ve @Service annotation lu tüm metotlara giriş/çıkışta loglama yapar.
 * @author arif.erol
 */

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Aspect
@Component
public class LoggingAspect {
    private static final Logger log = LoggerFactory.getLogger(LoggingAspect.class);

    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * BEFORE ADVICE
     * --------------
     * Tüm @RestController ve @Service annotation lu bean'lerdeki methodlar çağrılmadan önce loglanır.
     * JoinPoint ile metot adı ve argümanlar loglanır.
     */
    @Before("@within(org.springframework.web.bind.annotation.RestController) || @within(org.springframework.stereotype.Service)")
    public void before(JoinPoint jp){
        log.info("[AOP] -> {} metoduna giriş yapıldı. Parametreler: {}", jp.getSignature().toShortString(), jp.getArgs());
    }

    /**
     * AFTER RETURNING ADVICE
     * ----------------------
     * Tüm @RestController ve @Service annotation lu bean'lerdeki methodlar başarıyla döndükten sonra loglanır.
     * Metot adı ve dönüş değeri loglanır.
     */
    @AfterReturning(pointcut = "@within(org.springframework.web.bind.annotation.RestController) || @within(org.springframework.stereotype.Service)", returning = "ret")
    public void after(JoinPoint jp, Object ret){
        String resultStr;
        try {
            resultStr = objectMapper.writeValueAsString(ret);
        } catch (Exception e) {
            resultStr = ret != null ? ret.toString() : "null";
        }
        log.info("[AOP] <- {} metodundan çıkıldı. Dönüş: {}", jp.getSignature().toShortString(), resultStr);
    }

        /**
     * AFTER THROWING ADVICE
     * ---------------------
     * Sadece @Service annotationlu bean'lerdeki methodlarda exception fırlatıldığında loglanır.
     * Metot adı, argümanlar ve exception detayları loglanır.
     */
    @AfterThrowing(pointcut = "@within(org.springframework.web.bind.annotation.RestController) || @within(org.springframework.stereotype.Service)", throwing = "ex")
    public void afterThrowing(JoinPoint jp, Throwable ex) {
        log.error("[AOP] !! {} metodunda hata oluştu. Parametreler: {} | Hata: {}", jp.getSignature().toShortString(), jp.getArgs(), ex.getMessage(), ex);
    }
}