package com.securityjwt.JwtAuthentication.Document;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Component
public class ExceptionTemplate {
    private String errorStatus;
    private String errorMessage;
    private LocalDateTime localDateTime;
}
