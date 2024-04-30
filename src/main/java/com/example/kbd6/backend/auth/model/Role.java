package com.example.kbd6.backend.auth.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
// code is taken from this video. code is predefined to work as required.
// https://www.youtube.com/watch?v=TeBt0Ike_Tk&ab_channel=UnknownKoder
// https://github.com/unknownkoder/spring-security-login-system
@Entity
@Table(name = "roles")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "role_id")
    private long id;

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    private String authority;

    public Role(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return this.authority;
    }

}
