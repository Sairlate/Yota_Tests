package org.example.model.phone;

import lombok.*;

import java.util.List;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class PhonesModel {
    private List<PhoneModel> phones;
}
