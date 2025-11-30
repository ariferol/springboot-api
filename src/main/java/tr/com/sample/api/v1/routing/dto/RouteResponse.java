
package tr.com.sample.api.v1.routing.dto;

/**
 * @author arif.erol
 */
public record RouteResponse(
    String description,
    double distanceKm,
    double estimatedMinutes
) {}
