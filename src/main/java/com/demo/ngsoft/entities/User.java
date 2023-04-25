package com.demo.ngsoft.entities;

import com.demo.ngsoft.requestObjects.CreateUserRequest;
import com.demo.ngsoft.utils.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "user")
public final class User implements UserDetails {

public User(CreateUserRequest createUserRequest){
    this.active = createUserRequest.active();
    this.email =createUserRequest.email();
    this.isAdmin = createUserRequest.isAdmin();
    this.name = createUserRequest.name();
    this.password = createUserRequest.password();

}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String email;

    @Column
    private boolean isAdmin;

    @Column
    private boolean active;

    @Column
    private String password;

    @Transient
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(Role.chooseRole(this.isAdmin).name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isActive();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isActive();
    }


}
