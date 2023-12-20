package org.example.model.passport;

import lombok.*;

@Data
@With
@AllArgsConstructor
@NoArgsConstructor
public class PassportModel {
    private String passportNumber;
    private String passportSeries;

}
