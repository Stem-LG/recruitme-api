package tn.louay.recruitme.dto;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class PostApplication implements Serializable {
    private String name;
    private String email;
    private MultipartFile resume;
    private String motivation;
}
