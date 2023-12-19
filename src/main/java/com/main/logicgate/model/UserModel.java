package com.main.logicgate.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.main.logicgate.common.enums.UserRole;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


@Data
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
public class UserModel implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String name;
    private String lastName;
    private String email;
    private String password;
    private String photo;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;

    @LastModifiedDate
    private Date dateUpdated;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<ArticleModel> articles;

    public List<ArticleModel> getArticles() {
        return articles;
    }

    public Long getId() {
        return userId;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(userRole.name()));
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
}

