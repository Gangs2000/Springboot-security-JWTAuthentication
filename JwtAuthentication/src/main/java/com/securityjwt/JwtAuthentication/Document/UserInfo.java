package com.securityjwt.JwtAuthentication.Document;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document
public class UserInfo {
    @Id
    private String emailId;
    private String userName;
    private String password;
}
