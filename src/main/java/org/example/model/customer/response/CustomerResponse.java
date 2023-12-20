package org.example.model.customer.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@With
@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponse {
    @JsonProperty("return")
    private Return customerReturn;
}
