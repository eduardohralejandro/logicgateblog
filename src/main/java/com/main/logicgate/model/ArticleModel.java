package com.main.logicgate.model;

import com.main.logicgate.common.enums.ProgrammingLanguage;
import com.main.logicgate.common.enums.TechTag;

import java.util.Date;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Entity
public class ArticleModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long articleId;

    private String title;
    private String body;
    @Enumerated(EnumType.STRING)
    private TechTag techTag;
    private String photo;

    @Enumerated(EnumType.STRING)
    private ProgrammingLanguage programmingLanguage;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateUpdated;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserModel author;

    public ArticleModel(Long articleId, String title, String body, TechTag techTag, String photo, ProgrammingLanguage programmingLanguage, Date dateCreated, Date dateUpdated, UserModel author) {
        this.articleId = articleId;
        this.title = title;
        this.body = body;
        this.techTag = techTag;
        this.photo = photo;
        this.programmingLanguage = programmingLanguage;
        this.dateCreated = dateCreated;
        this.dateUpdated = dateUpdated;
        this.author = author;
    }

    public Long getArticleId() {
        return articleId;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public TechTag getTechTag() {
        return techTag;
    }

    public String getPhoto() {
        return photo;
    }

    public ProgrammingLanguage getProgrammingLanguage() {
        return programmingLanguage;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public Date getDateUpdated() {
        return dateUpdated;
    }

    public UserModel getAuthor() {
        return author;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setTechTag(TechTag techTag) {
        this.techTag = techTag;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setProgrammingLanguage(ProgrammingLanguage programmingLanguage) {
        this.programmingLanguage = programmingLanguage;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public void setDateUpdated(Date dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public void setAuthor(UserModel author) {
        this.author = author;
    }
}
