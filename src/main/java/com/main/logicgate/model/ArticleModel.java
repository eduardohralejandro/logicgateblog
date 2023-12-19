package com.main.logicgate.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.main.logicgate.common.enums.ProgrammingLanguage;
import com.main.logicgate.common.enums.TechTag;

import java.util.Date;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ArticleModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long articleId;

    @NotBlank(message = "Article must have a title")
    private String title;

    @NotBlank(message = "Body can not be empty")
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
    @JsonIgnore
    private UserModel author;
}
