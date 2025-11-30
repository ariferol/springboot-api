package tr.com.sample.api.v1.common.security.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tr.com.common.security.model.CommonUserDetails;

/**
 * @author arif.erol
 */
//@Component
@Service
public class
CommonUserDetailsService implements UserDetailsService {

    public CommonUserDetailsService() {
    }

    @Override
    public UserDetails loadUserByUsername(String tcKimlikNo) throws UsernameNotFoundException {
          return CommonUserDetails.build();
    }

}
