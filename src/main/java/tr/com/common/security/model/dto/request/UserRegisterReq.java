package tr.com.common.security.model.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import tr.com.common.core.base.IBaseDTO;
import tr.com.common.core.validation.TCKimlikNo;

import java.util.Set;

/**
 * @author arif.erol
 * Tüm validation annotation'ları record parametrelerinde de çalışır.
 */
public record UserRegisterReq(
    @NotBlank @Size(min = 3, max = 20) String username,
    @NotBlank @Size(max = 50) @Email String email,
    Set<String> role,
    @NotBlank @Size(min = 6, max = 40) String password,
    @NotBlank @Size(min = 3, max = 50) String firstName,
    @NotBlank @Size(min = 3, max = 50) String lastName,
    @NotBlank @Size(max = 11) @TCKimlikNo String tcKimlikNo,
    @NotBlank @Size(max = 10) String birthday,
    @NotBlank @Size(max = 1) String gender
) implements IBaseDTO {}
