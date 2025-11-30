package tr.com.sample.api.v1.routing.strategy;

import tr.com.sample.api.v1.routing.dto.RouteRequest;
import tr.com.sample.api.v1.routing.dto.RouteResponse;

/**
 * STRATEJİ DESIGN PATTERN NEDİR?
 * ------------------------------
 * Strateji pattern, runtime da , bir işlemin farklı algoritmalarla (stratejilerle) yapılmasını sağlar.
 * Harita uygulamasında "en kısa", "en hızlı", "en az trafik" gibi farklı rota hesaplama algoritmalarını
 * tek bir arayüz üzerinden yönetmek için kullanılır.
 *
 * Bu interface, tüm rota stratejilerinin ortak metodunu tanımlar.
 * @author arif.erol
 */
public interface IRouteStrategy {
    /**
     * Rota hesaplama algoritması
     */
    RouteResponse calculate(RouteRequest req);
}
