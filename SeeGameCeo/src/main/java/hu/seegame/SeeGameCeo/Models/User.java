package hu.seegame.SeeGameCeo.Models;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "generator")
    @SequenceGenerator(name = "generator", sequenceName = "generator", allocationSize = 1, initialValue = 1000)
    private int id;
    private String username;

    private String icnev;
    private String email;
    private String password;
    private int accountid;
    private LocalDateTime created_at;
    private String permission;

    @PrePersist
    public void onCreate(){
        if (created_at == null || permission == null){
            created_at = LocalDateTime.now();
            permission = "user";
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAccountid() {
        return accountid;
    }

    public void setAccountid(int accountid) {
        this.accountid = accountid;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getIcnev() {
        return icnev;
    }

    public void setIcnev(String icnev) {
        this.icnev = icnev;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && accountid == user.accountid && Objects.equals(username, user.username) && Objects.equals(icnev, user.icnev) && Objects.equals(email, user.email) && Objects.equals(password, user.password) && Objects.equals(created_at, user.created_at) && Objects.equals(permission, user.permission);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, icnev, email, password, accountid, created_at, permission);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", icnev='" + icnev + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", accountid=" + accountid +
                ", created_at=" + created_at +
                ", permission='" + permission + '\'' +
                '}';
    }
}
