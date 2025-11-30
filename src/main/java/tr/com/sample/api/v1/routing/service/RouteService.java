

package tr.com.sample.api.v1.routing.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Qualifier;
import tr.com.sample.api.v1.routing.enums.RouteType;
import tr.com.sample.api.v1.routing.dto.RouteRequest;
import tr.com.sample.api.v1.routing.dto.RouteResponse;
import tr.com.sample.api.v1.routing.strategy.IRouteStrategy;

/**
 * RouteService, strateji pattern'in kullanıldığı yerdir.
 * Kullanıcıdan gelen rota tipi isteğine göre ilgili strateji ile hesaplama yapılır.
 * Yeni bir strateji eklemek için sadece yeni bir sınıf yazmak ve burada inject etmek yeterlidir.
 * @author arif.erol
 */

@Service
public class RouteService {
    IRouteStrategy shortest,fastest,least;

    public RouteService(@Qualifier("shortest")IRouteStrategy s,
                        @Qualifier("fastest")IRouteStrategy f,
                        @Qualifier("leasttraffic")IRouteStrategy l){
        shortest=s;fastest=f;least=l;
    }

    /**
     * Strateji pattern ile ilgili algoritma seçimi burada yapılır.
     */
    public RouteResponse calculate(RouteRequest r, RouteType t){
        return switch(t){
            case SHORTEST -> shortest.calculate(r);
            case FASTEST -> fastest.calculate(r);
            case LEAST_TRAFFIC -> least.calculate(r);
        };
    }
}
