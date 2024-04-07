package smu.squiz.spring.domain;

import javax.persistence.*;
import javax.validation.constraints.Email;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import smu.squiz.spring.domain.common.BaseEntity;
import smu.squiz.spring.domain.enums.Gender;
import smu.squiz.spring.domain.enums.RoleType;

import java.util.Collection;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Builder
@AllArgsConstructor
public class User extends BaseEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,length = 20)
    private String password;

    @Email
    @Column(nullable = false,length = 50)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;

    @Column(nullable = true)
    private Boolean is_deleted;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private RoleType role;

    //UserDetails 상속
    public void setIsDeleted(boolean deleted) {
        this.is_deleted = deleted;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
