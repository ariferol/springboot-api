package tr.com.sample.api.v1.common.security.filter;

import io.jsonwebtoken.JwtException;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;
import tr.com.common.core.util.JwtUtilBean;
import tr.com.sample.api.v1.common.security.service.CommonUserDetailsService;

import java.io.IOException;

/**
 * @author arif.erol
 */
public class AuthRequestFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtilBean jwtUtils;

    @Autowired
    private CommonUserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        /*Swagger ve OpenAPI endpointleri JWT doğrulamasından muaf tutuldu*/
        String reqPathUri = request.getRequestURI();
        System.out.println("reqPathUri : "+ reqPathUri);
        System.out.println("getRequestURL : "+ request.getRequestURL().toString());

        boolean isSwaggerOrOpenApi = reqPathUri.equals("/favicon.ico")
            || reqPathUri.startsWith("/sample-api/swagger-ui")
            || reqPathUri.startsWith("/sample-api/v3/api-docs")
            || reqPathUri.startsWith("/sample-api/swagger-resources")
            || reqPathUri.startsWith("/swagger-ui")
            || reqPathUri.startsWith("/v3/api-docs")
            || reqPathUri.startsWith("/swagger-resources")
            || reqPathUri.startsWith("/webjars")
            || reqPathUri.contains("/h2-console")
            || reqPathUri.contains("swagger-ui")
            || reqPathUri.contains("swagger-resources")
            || reqPathUri.contains("v3/api-docs")
            || reqPathUri.contains("/sample-api/v1/auth/registerUser")
            || reqPathUri.contains("/sample-api/v1/auth/token")
            || reqPathUri.contains("/sample-api/v1/route");
        if (StringUtils.isNotEmpty(reqPathUri) && !isSwaggerOrOpenApi) {
            String jwtTokenInHeader = jwtUtils.getJwtTokenInHeader(request);
            String jwtTokenInCookie = jwtUtils.getJwtFromCookies(request);
            /*Token validate olmadiysa hata firlatiyoruz. Bu yuzden burayi try-catch yapma!*/
            boolean isCookieTokenValidated = StringUtils.isNotEmpty(jwtTokenInCookie) && jwtUtils.validateJwtToken(jwtTokenInCookie);
            boolean isHeaderTokenValidated = StringUtils.isNotEmpty(jwtTokenInHeader) && jwtUtils.validateJwtToken(jwtTokenInHeader);
            if (isCookieTokenValidated || isHeaderTokenValidated) {
                String username = isCookieTokenValidated ? jwtUtils.getUserNameFromJwtToken(jwtTokenInCookie)
                        : jwtUtils.getUserNameFromJwtToken(jwtTokenInHeader);

                UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(userDetails,
                                null,/*Buraya diger user credentials degerleri basilabilir*/
                                userDetails.getAuthorities());

                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                /*Context e authentication bilgilerini setliyoruz*/
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else
                throw new JwtException("JWT token bilgisi dogrulanamadi!");
        }

        filterChain.doFilter(request, response);
    }
}
