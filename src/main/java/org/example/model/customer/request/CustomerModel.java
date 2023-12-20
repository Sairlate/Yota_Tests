package org.example.model.customer.request;

import lombok.*;


@Data
@With
@AllArgsConstructor
@NoArgsConstructor
public class CustomerModel {
    private String name;
    private Long phone;
    private AdditionalParameters additionalParameters;


}
