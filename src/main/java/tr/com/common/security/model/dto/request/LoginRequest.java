package tr.com.common.security.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import tr.com.common.core.base.IBaseDTO;
import tr.com.common.core.validation.TCKimlikNo;

/**
 * @author arif.erol
 * Validation annotation'ları record parametrelerinde de çalışır.
 */
public record LoginRequest(
    @NotBlank
    @TCKimlikNo
    String username,
    @NotBlank String password
) implements IBaseDTO {
    private static final long serialVersionUID = 1L;
}
