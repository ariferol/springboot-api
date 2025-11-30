package tr.com.sample.api.v1.routing.controller;

import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import tr.com.sample.api.v1.routing.service.RouteService;
import tr.com.sample.api.v1.routing.enums.RouteType;
import tr.com.sample.api.v1.routing.dto.RouteRequest;
import tr.com.sample.api.v1.routing.dto.RouteResponse;
import tr.com.common.core.base.ResponseDTO;

/**
 * RouteController, strateji pattern'in dışarıya açıldığı yerdir.
 * Kullanıcıdan rota tipi ve rota isteği alınır, ilgili strateji ile hesaplama yapılır.
 *
 * ÖRNEK KULLANIM:
 * POST /api/v1/route/SHORTEST
 * Body: { "startLat": ..., "startLon": ..., "endLat": ..., "endLon": ... }
 * @author arif.erol
 */

@RestController
@RequestMapping("${api.base-path}/v1/route")
public class RouteController {

    private final RouteService service;

    public RouteController(RouteService service) {
        this.service = service;
    }

    @PostMapping("/{type}")
    public ResponseDTO<RouteResponse> calc(@PathVariable RouteType type, @Valid @RequestBody RouteRequest r){
        RouteResponse response = service.calculate(r, type);
        return ResponseDTO.Builder.<RouteResponse>newInstance()
                .data(response)
                .description("Rota başarıyla hesaplandı")
                .build();
    }

    @GetMapping("/exception-demo")
    public ResponseDTO<String> throwExceptionExample() {
        // Burada bilinçli olarak bir hata fırlatılır. AOP ile otomatik loglanacaktır.
        if (true) {
            throw new IllegalArgumentException("Örnek: Rota exception loglama demonstrasyonu!");
        }
        return ResponseDTO.Builder.<String>newInstance()
                .data("Bu satır asla çalışmaz.")
                .description("Exception fırlatıldı.")
                .build();
    }
}
