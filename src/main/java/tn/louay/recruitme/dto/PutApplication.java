package tn.louay.recruitme.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import tn.louay.recruitme.enums.ApplicationAction;

@Data
@AllArgsConstructor
@Builder
public class PutApplication {
    ApplicationAction action;
}
