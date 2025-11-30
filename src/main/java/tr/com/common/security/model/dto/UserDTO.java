package tr.com.common.security.model.dto;

import tr.com.common.core.base.IBaseDTO;

import java.util.List;

/**
 * @author arif.erol
 */
public record UserDTO(
    Long id,
    String username,
    String email,
    String firstName,
    String lastName,
    String birthday,
    String gender,
    List<String> roles,
    List<String> groups
) implements IBaseDTO {}