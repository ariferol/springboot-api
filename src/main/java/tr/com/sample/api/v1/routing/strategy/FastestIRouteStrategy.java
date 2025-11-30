package tr.com.sample.api.v1.routing.strategy;

import org.springframework.stereotype.Component;
import tr.com.sample.api.v1.routing.dto.RouteRequest;
import tr.com.sample.api.v1.routing.dto.RouteResponse;

/**
 * EN HIZLI ROTA STRATEJİSİ
 * ------------------------
 * Strateji pattern ile, bu sınıf en hızlı rotayı hesaplar.
 * RouteStrategy arayüzünü uygular.
 * @author arif.erol
 */
@Component("fastest")
public class FastestIRouteStrategy implements IRouteStrategy {


    /**
     * Haversine formülü ile iki koordinat arasındaki mesafeyi kilometre cinsinden hesaplar.
     * @param a Başlangıç enlemi
     * @param b Başlangıç boylamı
     * @param c Bitiş enlemi
     * @param d Bitiş boylamı
     * @return İki nokta arası mesafe (km)
     */
    private double hav(double a,double b,double c,double d){
        final int R=6371; // Dünya'nın yarıçapı (km)
        double dLat=Math.toRadians(c-a), dLon=Math.toRadians(d-b);
        double x=Math.sin(dLat/2)*Math.sin(dLat/2)+Math.cos(Math.toRadians(a))*Math.cos(Math.toRadians(c))*Math.sin(dLon/2)*Math.sin(dLon/2);
        return R*2*Math.atan2(Math.sqrt(x),Math.sqrt(1-x));
    }

    /**
     * En hızlı rota algoritması: Sadece iki nokta arasındaki mesafe hesaplanır.
     * Ortalama hız 60 km/s varsayılır, süre buna göre hesaplanır.
     * @param r Rota isteği (başlangıç ve bitiş koordinatları)
     * @return Rota sonucu (mesafe ve süre)
     */
    @Override
    public RouteResponse calculate(RouteRequest r){
        double dist = hav(r.startLat(), r.startLon(), r.endLat(), r.endLon())*1.0;
        double time = dist/60*60; // Ortalama hız: 60 km/s
        return new RouteResponse("Fastest route", dist, time);
    }
}
