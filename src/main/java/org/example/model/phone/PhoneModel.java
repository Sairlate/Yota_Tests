package org.example.model.phone;

import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class PhoneModel {
    private Long phone;
    private String locale;
}
