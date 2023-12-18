package com.main.logicgate.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.main.logicgate.common.enums.UserRole;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Data
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
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
    private UserRole userRole = UserRole.REGULAR;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;

    @LastModifiedDate
    private Date dateUpdated;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<ArticleModel> articles;

    public UserModel(String name, String lastName, String email, String photo, UserRole userRole) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.photo = photo;
        this.userRole = userRole;
        this.dateCreated = new Date();
        this.dateUpdated = new Date();
    }

    public UserModel() {
    }

    public List<ArticleModel> getArticles() {
        return articles;
    }

    public Long getId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword(){
        return password;
    }

    public String getPhoto() {
        return photo;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public Date getDateUpdated() {
        return dateUpdated;
    }

    public void setId(Long id) {
        this.userId = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public void setDateUpdated(Date dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public void setArticles(List<ArticleModel> articles) {
        this.articles = articles;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserModel)) return false;
        UserModel userModel = (UserModel) o;
        return Objects.equals(userId, userModel.userId) && Objects.equals(getName(), userModel.getName()) && Objects.equals(getLastName(), userModel.getLastName()) && Objects.equals(getEmail(), userModel.getEmail()) && Objects.equals(getPhoto(), userModel.getPhoto()) && getUserRole() == userModel.getUserRole() && Objects.equals(getDateCreated(), userModel.getDateCreated()) && Objects.equals(getDateUpdated(), userModel.getDateUpdated()) && Objects.equals(articles, userModel.articles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, getName(), getLastName(), getEmail(), getPhoto(), getUserRole(), getDateCreated(), getDateUpdated(), articles);
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", photo='" + photo + '\'' +
                ", userRole=" + userRole +
                ", dateCreated=" + dateCreated +
                ", dateUpdated=" + dateUpdated +
                ", articles=" + articles +
                '}';
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

