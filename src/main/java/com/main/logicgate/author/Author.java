package com.main.logicgate.author;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.main.logicgate.common.enums.AuthorRole;

import java.util.Date;
import java.util.List;

import com.main.logicgate.article.Article;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;


@Data
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "author")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long authorId;

    private String name;
    private String lastName;
    private String email;
    private String password;
    private String photo;

    @Enumerated(EnumType.STRING)
    private AuthorRole authorRole;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateUpdated;

    @OneToMany(mappedBy = "author")
    private List<Article> articles;
}

