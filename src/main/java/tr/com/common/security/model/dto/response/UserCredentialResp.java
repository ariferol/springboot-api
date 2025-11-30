package tr.com.common.security.model.dto.response;

import tr.com.common.core.base.IBaseDTO;
import java.util.List;

/**
 * @author arif.erol
 * UserCredentialResp sadece veri taşımak için kullanıldığı için immutable bir record olarak tanımlanmıştır.
 */
public record UserCredentialResp(
    Long id,
    String tcKimlikNo,
    String email,
    String firstName,
    String lastName,
    String dateOfStart,
    List<String> groups,
    List<String> roles,
    String token
) implements IBaseDTO {}
