//package tr.com.common.security.repository;
//
//import org.springframework.stereotype.*;
//import tr.com.common.base.IBaseRepository;
//import tr.com.common.security.model.User;
//
//import java.util.Optional;
//
///**
// * @author arif.erol
// */
//@Repository
//public interface UserRepository extends IBaseRepository<User, Long> {
//    Optional<User> findByUsername(String username);
//
//    Boolean existsByUsername(String username);
//
//    Boolean existsByEmail(String email);
//
//    User findByEmail(String email);
//}
