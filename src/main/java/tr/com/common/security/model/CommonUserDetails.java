package tr.com.common.security.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * @author arif.erol
 */
public class CommonUserDetails implements UserDetails {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String username;
    private String email;
    @JsonIgnore
    private String password;
    private String firstName;
    private String lastName;
    private String dateOfStart;
    private String gender;
    private List<String> groups;

    private boolean gizliSozOk;

    private Collection<? extends GrantedAuthority> authorities;

    public CommonUserDetails(Long id, String username, String email, String password
            , String firstName, String lastName, String dateOfStart, String gender, List<String> groups
            , Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfStart = dateOfStart;
        this.gender = gender;
        this.groups = groups;
        this.authorities = authorities;
    }

    public CommonUserDetails(Long id, String tcKimlikNo, String mail, String isim, String soyisim, String dateOfStart
            ,List<String> groups, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = tcKimlikNo;
        this.email = mail;
        this.firstName = isim;
        this.lastName = soyisim;
        this.dateOfStart = dateOfStart;
        this.authorities = authorities;

    }

    public static CommonUserDetails build() {
        if (true) {
            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("Admin"));
            return new CommonUserDetails(1l,"sa","test@test.com",null,"sa name","sa lastname",null,null,new ArrayList<String>()
                    ,authorities
                    );
        } else return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommonUserDetails user = (CommonUserDetails) o;
        return Objects.equals(id, user.id);
    }

    public boolean isGizliSozOk() {
        return gizliSozOk;
    }

    public void setGizliSozOk(boolean gizliSozOk) {
        this.gizliSozOk = gizliSozOk;
    }

    /*override olmayan, yani haricen eklenen alanlarin getter lari:*/
    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getGender() {
        return gender;
    }

    public List<String> getGroups() {
        return groups;
    }

}
