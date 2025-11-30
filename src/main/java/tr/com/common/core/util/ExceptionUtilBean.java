package tr.com.common.core.util;

import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author arif.erol
 */
@Component
public class ExceptionUtilBean {

    private static final Logger log = LoggerFactory.getLogger(ExceptionUtilBean.class);

//    private static WebClient webClient;
//    private static final String EXCEPTION_SERVICE_BASE_URL = "http://localhost:8090";
//
//    @Autowired
//    public ExceptionUtilBean(WebClient.Builder builder) {
//        webClient = builder
//                .baseUrl(EXCEPTION_SERVICE_BASE_URL)
//                .build();
//    }
//
//    private static Mono<ResponseEntity> consumeRestService(WebClient webClient, String pathParam) {
//        /*Consume edilecek servis reactive ise;*/
//        Flux<Object> recipientListStream2 = webClient.get().uri("/addlog")
//                .retrieve()
//                .bodyToFlux(Object.class);
//        /*reactive olarak gelen data mono ya ceviriliyor;*/
//        Mono<List<Object>> collectedRecipesStream = recipientListStream2.collectList();
//        List<Object> recipientList2 = collectedRecipesStream.block();
//
//        return recipientList;
//        return recipientList2;
//
//        /*Consume edilecek servis reactive degil ise;*/
//        LoginRequest sampleExceptionLogModel = new LoginRequest("sampleUser", "samplePassword");
//        Mono<ResponseEntity> responseEntityMono = webClient.post()
//                .uri(pathParam)
//                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
//                .body(Mono.just(sampleExceptionLogModel), LoginRequest.class)
//                .retrieve()
//                .bodyToMono(ResponseEntity.class);
//        return responseEntityMono;
//    }

    public static int addExceptionLog(RuntimeException exception) {
        log.error(exception.getMessage(), exception);
        int exceptionId = 0;
        /*Exception log daha sonra atilacak*/
        //Mono<ResponseEntity> result = consumeRestService(webClient, "/addlog");
        return exceptionId;
    }
}
