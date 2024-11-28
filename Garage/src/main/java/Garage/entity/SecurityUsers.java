package Garage.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "users")
public class SecurityUsers {
    @Id
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "enabled")
    private int enabled;


    public SecurityUsers() {
    }

    public SecurityUsers(String username, String password, int enabled) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }



//    public Authorities getAuthorities() {
//        return authorities;
//    }
//
//    public void setAuthorities(Authorities authorities) {
//        this.authorities = authorities;
//    }

    @Override
    public String toString() {
        return "SecurityUsers{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", enabled=" + enabled +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SecurityUsers that = (SecurityUsers) o;
        return enabled == that.enabled && Objects.equals(username, that.username) && Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, enabled);
    }
}
