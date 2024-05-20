package tn.louay.recruitme.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import tn.louay.recruitme.enums.ApplicationStatus;

@Data
@AllArgsConstructor
@Builder
public class GetApplications {
    private Integer id;
    private String name;
    private ApplicationStatus status;
    private Date createdAt;
}
