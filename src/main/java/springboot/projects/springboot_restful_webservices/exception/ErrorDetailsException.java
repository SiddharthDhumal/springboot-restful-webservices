package springboot.projects.springboot_restful_webservices.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDetailsException {

    private LocalDateTime timestamp;
    private String message;
    private String path;
    private String errorCode;
}