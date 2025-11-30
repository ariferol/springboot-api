
package tr.com.sample.api.v1.routing.dto;

import jakarta.validation.constraints.NotNull;
import tr.com.common.core.validation.TCKimlikNo;

/**
 * @author arif.erol
 */
public record RouteRequest(
    @NotNull Double startLat,
    @NotNull Double startLon,
    @NotNull Double endLat,
    @NotNull Double endLon,
    @TCKimlikNo String tcKimlikNo
) {}
