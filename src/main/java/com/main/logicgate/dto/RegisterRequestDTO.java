package com.main.logicgate.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.main.logicgate.common.enums.UserRole;
import com.main.logicgate.model.ArticleModel;
import lombok.*;

import java.util.Date;
import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RegisterRequestDTO {
   private String name;
   private String lastName;
   private String email;
   private String password;
   private String photo;
   private UserRole userRole;
   private Date dateCreated;
   private Date dateUpdated;
   private List<ArticleModel> articles;
}
