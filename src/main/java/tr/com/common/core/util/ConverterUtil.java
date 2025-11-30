package tr.com.common.core.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;
//import tr.com.common.security.model.Role;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author arif.erol
 */
public final class ConverterUtil {
    private ConverterUtil() {
    }

    public static String convertDateToStr(Date date) {
        return date != null ? new SimpleDateFormat("dd.MM.yyyy").format(date) : StringUtils.EMPTY;
    }

//    public static List<String> mapRoleSetToList(Set<Role> roles) {
//        return roles != null && roles.size() > 0
//                ? roles.stream().map(role -> role.getName()).toList() : new ArrayList<>();
//    }

    public static List<String> mapRoleAuthToList(Collection<? extends GrantedAuthority> authorities) {
        return authorities != null && authorities.size() > 0
                ? authorities.stream().map(role -> role.getAuthority()).toList()
                : new ArrayList<>();
    }

    public static String convertGender(String gender) {
        String result = "Cinsiyet Bos";
        if (StringUtils.isNotEmpty(gender)) {
            result = gender.equals("E") ? "Erkek" : "Kadın";
        }
        return result;
    }

    public static Date convertStrToDate(String dateStr) {
        try {
            return StringUtils.isNotEmpty(dateStr) ? new SimpleDateFormat("dd.MM.yyyy").parse(dateStr) : null;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
