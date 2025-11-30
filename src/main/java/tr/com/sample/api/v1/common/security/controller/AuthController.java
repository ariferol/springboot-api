package tr.com.sample.api.v1.common.security.controller;

/**
 * @author arif.erol
 */

import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import tr.com.common.core.base.IBaseRestController;
import tr.com.common.core.base.ResponseDTO;
import tr.com.common.security.model.CommonUserDetails;
import tr.com.common.security.model.dto.request.LoginRequest;
import tr.com.common.security.model.dto.response.UserCredentialResp;
import tr.com.sample.api.v1.common.security.service.CommonUserDetailsService;
import tr.com.common.core.util.ConverterUtil;
import tr.com.common.core.util.JwtUtilBean;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("${api.base-path}/v1/auth")
public class AuthController implements IBaseRestController {

    private final AuthenticationManager authenticationManager;

//    private final UserRepository userRepository;
//
//    private final RoleRepository roleRepository;

    private final PasswordEncoder encoder;

    private final JwtUtilBean jwtUtilBean;

    private CommonUserDetailsService commonUserDetailsService;

    public AuthController(AuthenticationManager authenticationManager
//            , UserRepository userRepository, RoleRepository roleRepository
            , PasswordEncoder encoder, JwtUtilBean jwtUtilBean) {
        this.authenticationManager = authenticationManager;
//        this.userRepository = userRepository;
//        this.roleRepository = roleRepository;
        this.encoder = encoder;
        this.jwtUtilBean = jwtUtilBean;
    }

//    @PostMapping("registerUser")
//    public ResponseEntity<?> registerUser(@Valid @RequestBody UserRegisterReq userRegisterReq) {
//        if (userRepository.existsByUsername(userRegisterReq.getUsername())) {
//            return ResponseEntity.badRequest().body("Hata: Bu kullanici zaten kayitli!");
//        }
//
//        if (userRepository.existsByEmail(userRegisterReq.getEmail())) {
//            return ResponseEntity.badRequest().body("Hata: Bu e-posta zaten kayitli!");
//        }
//        Date birthday = null;
//        try {
//            birthday = new SimpleDateFormat("dd.MM.yyyy").parse(userRegisterReq.getBirthday());
//        } catch (ParseException e) {
//            System.out.println("Dogum tarihi parse edilirken hata olustu");
//            e.printStackTrace();
//        }
//        User user = new User(userRegisterReq.getUsername(), userRegisterReq.getEmail()
//                , encoder.encode(userRegisterReq.getPassword())
//                , userRegisterReq.getFirstName(), userRegisterReq.getLastName(), birthday, userRegisterReq.getGender()
//        );
//
//        Set<String> strRoles = userRegisterReq.getRole();
//        Set<Role> roles = new HashSet<>();
//
//        if (strRoles == null) {
//            Role userRole = roleRepository.findByName(RoleEnum.ROLE_USER).orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//            roles.add(userRole);
//        } else {
//            String roleBulunamadiMsg = "Hata: Role bulunamadi.";
//            strRoles.forEach(role -> {
//                switch (RoleEnum.valueOf(role)) {
//                    case ROLE_ADMIN:
//                        Role adminRole = roleRepository.findByName(RoleEnum.ROLE_ADMIN).orElseThrow(() -> new RuntimeException(roleBulunamadiMsg));
//                        roles.add(adminRole);
//
//                        break;
//                    case ROLE_MODERATOR:
//                        Role modRole = roleRepository.findByName(RoleEnum.ROLE_MODERATOR).orElseThrow(() -> new RuntimeException(roleBulunamadiMsg));
//                        roles.add(modRole);
//
//                        break;
//                    default:
//                        Role userRole = roleRepository.findByName(RoleEnum.ROLE_USER).orElseThrow(() -> new RuntimeException(roleBulunamadiMsg));
//                        roles.add(userRole);
//                }
//            });
//        }
//
//        user.setRoles(roles);
//        userRepository.save(user);
//
//        return ResponseEntity.ok(
//                ResponseDTO.Builder.newInstance()
//                        .data("Kullanici basariyla eklendi")
//                        .description("Kullanici basariyla eklendi description")
//                        .build());
//    }

//    @GetMapping("/users/{userName}")
//    public ResponseDTO<?> getUser(@PathVariable("userName") String userName) {
//        /* Onemli : Keycloak tarafindan, kullanici adi na gore kullanici bilgisini getiren servistir, silmeyin!.(11.03.2024-Arif EROL)*/
////        User user = userRepository.findByUsername(userName)
////                .orElse(userRepository.findByEmail(userName));
//
//        UserDTO userDto = null;//new UserDTO
////                (user.getId(), user.getUsername(), user.getEmail()
////                        , user.getFirstName(), user.getLastName()
////                        , ConverterUtil.convertDateToStr(user.getBirthday())
////                        , ConverterUtil.convertGender(user.getGender())
////                        , ConverterUtil.mapRoleSetToList(user.getRoles()));
//
//        return ResponseDTO.Builder.newInstance()
//                .data(userDto)
//                .description(userDto != null ? "Kayit bulundu" : "Kayit bulunamadi")
//                .build();
//    }
//
//    @PostMapping("/verify-password")
//    public ResponseEntity<Boolean> verifyUserAndPassword(@Valid @RequestBody LoginRequest loginRequest) {
//        /* Onemli : Keycloak tarafindan, kullanici adi ve sifresini dogrulayan servistir, silmeyin!.(11.03.2024-Arif EROL)*/
//        Authentication authentication = authenticationManager
//            .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password()));
//        Boolean isAutheticated = authentication.isAuthenticated();
//        return ResponseEntity.ok(isAutheticated);
//    }

/* ${api.base-path}/${api.version}/auth/token */
    @PostMapping("/token")
    public ResponseEntity<?> token(@Valid @RequestBody LoginRequest loginRequest) {

    Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password()));

    SecurityContextHolder.getContext().setAuthentication(authentication);

    CommonUserDetails userDetails = (CommonUserDetails) authentication.getPrincipal();

    ResponseCookie jwtCookie = jwtUtilBean.generateJwtCookie(userDetails);

    UserCredentialResp userCredentialResp = new UserCredentialResp(
        userDetails.getId(),
        userDetails.getUsername(),
        userDetails.getEmail(),
        userDetails.getFirstName(),
        userDetails.getLastName(),
        userDetails.getGender(),
        userDetails.getGroups(),
        ConverterUtil.mapRoleAuthToList(userDetails.getAuthorities()),
        jwtCookie.getValue()
    );

    return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString()).body(
        ResponseDTO.Builder.newInstance()
            .data(userCredentialResp)
            .description("Login islemi basarili")
            .build());
    }

//    @PostMapping("/logout")
//    public ResponseEntity<?> logout() {
//        ResponseCookie cookie = jwtUtilBean.getCleanJwtCookie();
//        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
//                .body(ResponseDTO.Builder.newInstance()
//                        .data("Kullanici oturumu basariyla sonlandirildi")
//                        .description("Kullanici oturumu basariyla sonlandirildi description")
//                        .build());
//    }
//
//    @GetMapping
////    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
//    public ResponseDTO<?> validateToken(@RequestParam("token") String token) {
//        boolean result = jwtUtilBean.validateJwtToken(token);
//        String resultMsg = result ? "Token gecerli" : "Token dogrulanamadi";
//        return ResponseDTO.Builder.newInstance()
//                .data("resultMsg")
//                .description(resultMsg + " description")
////                .statusCode(HttpStatus.FORBIDDEN.value())
//                .build();
//    }

//    @GetMapping("/users")
//    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
////    @PreAuthorize("hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
//    public ResponseDTO<?> getUserList() {
//
//        List<User> userList = userRepository.findAll();
//
//        return ResponseDTO.Builder.newInstance()
//                .data(userList)
//                .description((userList != null ? userList.size() : 0) + " adet kayit bulundu")
//                .build();
//    }

//    @PostMapping("/deleteuser/{userId}")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
//    public ResponseEntity<?> deleteuser(@PathVariable("userId") Long userId) {
//
//        userRepository.deleteById(userId);
//
//        return ResponseEntity.ok(
//                ResponseDTO.Builder.newInstance()
//                        .data("Kullanıci basariyle silindi")
//                        .description("Silme islemi basarili")
//                        .build());
//    }
}
