package org.example.model.customer.response;

import lombok.*;
import org.example.model.customer.request.AdditionalParameters;

@Data
@With
@AllArgsConstructor
@NoArgsConstructor
public class Return {
    public String customerId;
    public String name;
    public String status;
    public Long phone;
    public AdditionalParameters additionalParameters;
    public String pd;
}
