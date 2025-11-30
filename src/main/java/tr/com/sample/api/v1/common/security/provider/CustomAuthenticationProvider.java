package tr.com.sample.api.v1.common.security.provider;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import tr.com.common.security.model.CommonUserDetails;

//import tr.com.common.security.repository.RoleRepository;


/**
 * @author arif.erol
 */
public class CustomAuthenticationProvider implements AuthenticationProvider {

//    private final RoleRepository roleRepository;

//    public CustomAuthenticationProvider(RoleRepository roleRepository) {
//        this.roleRepository = roleRepository;
//    }
public CustomAuthenticationProvider() {
}

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        //User i authenticate and authorize olarak valid ediyoruz;
        UserDetails userDetails = loadUserByYetkiKontrol(username, password);
        if (userDetails == null || StringUtils.isEmpty(userDetails.getUsername())) {
            throw new UsernameNotFoundException("Kimlik dogrulama basarisiz oldu");
        }
        Authentication authenticated = new UsernamePasswordAuthenticationToken(
                userDetails, password, userDetails.getAuthorities());
        return authenticated;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

    public UserDetails loadUserByYetkiKontrol(String tcKimlikNo, String password) throws UsernameNotFoundException {
        //TODO: Mümkünse second level cache de eklenebilir

        return CommonUserDetails.build();
    }
}
