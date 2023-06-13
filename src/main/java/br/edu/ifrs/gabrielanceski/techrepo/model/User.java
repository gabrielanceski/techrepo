package br.edu.ifrs.gabrielanceski.techrepo.model;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "users")
@Data
@NoArgsConstructor
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "nickname", nullable = false, length = 32, unique = true)
    private String nickname;
    @Column(name = "email", nullable = false, length = 255, unique = true)
    private String email;
    @Column(name = "password", nullable = false, length = 255)
    private String password;
    @Column(name = "firstname", nullable = true, length = 32)
    private String firstName;
    @Column(name = "lastname", nullable = true, length = 32)
    private String lastName;

    @ManyToMany
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;

    private boolean isAccountNonExpired;
    private boolean isAccountNonLocked;
    private boolean isCredentialsNonExpired;
    private boolean isEnabled;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .toList();
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

    @Override
    public String getUsername() {
        return email;
    }

    public static UserBuilder builder() {
        return new UserBuilder();
    }

    public static class UserBuilder {
        private long id;
        private String nickname;
        private String email;
        private String password;
        private String firstName;
        private String lastName;
        private List<Role> roles;
        private boolean isAccountNonExpired;
        private boolean isAccountNonLocked;
        private boolean isCredentialsNonExpired;
        private boolean isEnabled;

        private UserBuilder() {}

        public UserBuilder id(long id) {
            this.id = id;
            return this;
        }

        public UserBuilder nickname(String nickname) {
            this.nickname = nickname;
            return this;
        }

        public UserBuilder email(String email) {
            this.email = email;
            return this;
        }

        public UserBuilder password(String password) {
            this.password = password;
            return this;
        }

        public UserBuilder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public UserBuilder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public UserBuilder roles(Role... roles) {
            this.roles = Arrays.asList(roles);
            return this;
        }

        public UserBuilder accountNonExpired(boolean accountNonExpired) {
            isAccountNonExpired = accountNonExpired;
            return this;
        }

        public UserBuilder accountNonLocked(boolean accountNonLocked) {
            isAccountNonLocked = accountNonLocked;
            return this;
        }

        public UserBuilder credentialsNonExpired(boolean credentialsNonExpired) {
            isCredentialsNonExpired = credentialsNonExpired;
            return this;
        }

        public UserBuilder enabled(boolean enabled) {
            isEnabled = enabled;
            return this;
        }

        public User build() {
            User user = new User();
            user.setId(id);
            user.setNickname(nickname);
            user.setEmail(email);
            user.setPassword(password);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setRoles(roles);
            user.setAccountNonExpired(isAccountNonExpired);
            user.setAccountNonLocked(isAccountNonLocked);
            user.setCredentialsNonExpired(isCredentialsNonExpired);
            user.setEnabled(isEnabled);
            return user;
        }
    }
}