package model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "AUTHORITIES")
public class Authorities {

    @Id
    @Column(name = "AUTHORITY")
    private String authority;


    @Column(name = "USERNAME")
    private String username;

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
