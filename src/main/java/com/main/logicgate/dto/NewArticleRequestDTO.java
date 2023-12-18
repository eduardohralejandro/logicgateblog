package com.main.logicgate.dto;

import com.main.logicgate.common.enums.ProgrammingLanguage;
import com.main.logicgate.common.enums.TechTag;
import com.main.logicgate.common.enums.UserRole;
import com.main.logicgate.model.UserModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NewArticleRequestDTO {
    private String title;
    private String body;
    private String photo;
    private ProgrammingLanguage programmingLanguage;
    private TechTag techTag;
    private UserModel author;
}
