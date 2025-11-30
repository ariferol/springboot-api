package tr.com.sample.api.v1.routing.strategy;

import org.springframework.stereotype.Component;
import tr.com.sample.api.v1.routing.dto.RouteRequest;
import tr.com.sample.api.v1.routing.dto.RouteResponse;

/**
 * EN AZ TRAFİKLİ ROTA STRATEJİSİ
 * ------------------------------
 * Strateji pattern ile, bu sınıf en az trafik olan rotayı hesaplar.
 * RouteStrategy arayüzünü uygular.
 * @author arif.erol
 */
@Component("leasttraffic")
public class LeastTrafficIRouteStrategy implements IRouteStrategy {


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
     * En az trafik algoritması: Genelde trafik az ise yol fazladır.
     * Yani mesafe %10 artırılır (trafik etkisi), ortalama hız 50 km/s varsayılır.
     * Süre buna göre hesaplanır.
     * @param r Rota isteği (başlangıç ve bitiş koordinatları)
     * @return Rota sonucu (mesafe ve süre)
     */
    @Override
    public RouteResponse calculate(RouteRequest r){
        double dist = hav(r.startLat(), r.startLon(), r.endLat(), r.endLon())*1.1; // Trafik etkisiyle mesafe %10 artırılır
        double time = dist/50*60; // Ortalama hız: 50 km/s
        return new RouteResponse("Traffic aware", dist, time);
    }
}
