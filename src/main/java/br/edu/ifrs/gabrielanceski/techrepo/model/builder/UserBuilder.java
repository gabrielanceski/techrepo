package br.edu.ifrs.gabrielanceski.techrepo.model.builder;

import java.util.Arrays;
import java.util.List;

import br.edu.ifrs.gabrielanceski.techrepo.model.Role;
import br.edu.ifrs.gabrielanceski.techrepo.model.User;

public class UserBuilder {
    private long id;
    private String username;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private List<Role> roles;
    private boolean isAccountNonExpired;
    private boolean isAccountNonLocked;
    private boolean isCredentialsNonExpired;
    private boolean isEnabled;

    public UserBuilder id(long id) {
        this.id = id;
        return this;
    }

    public UserBuilder username(String username) {
        this.username = username;
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
        user.setUsername(username);
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