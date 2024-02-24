package com.main.logicgate.article;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.main.logicgate.common.enums.ProgrammingLanguage;
import com.main.logicgate.common.enums.TechTag;

import java.util.Date;

import com.main.logicgate.author.Author;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long articleId;

    @NotBlank(message = "Article must have a title")
    private String title;

    @NotBlank(message = "Body of an article can not be empty")
    private String body;

    @Enumerated(EnumType.STRING)
    private TechTag techTag;
    private String photo;

    @Enumerated(EnumType.STRING)
    private ProgrammingLanguage programmingLanguage;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateUpdated;

    @ManyToOne
    @JoinColumn(name = "author_id")
    @JsonIgnore
    private Author author;
}
