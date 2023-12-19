package org.example.model.customer;

import lombok.*;


@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class CustomerModel {
    private String name;
    @EqualsAndHashCode.Exclude
    private Long phone;
    @EqualsAndHashCode.Exclude
    private AdditionalParameters additionalParameters;

    @Getter
    @Setter
    @With
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @EqualsAndHashCode
    @ToString
    public static class AdditionalParameters {
        private String string;
    }
}
