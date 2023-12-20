package org.example.model.customer.request;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

@Data
@With
@AllArgsConstructor
@NoArgsConstructor
public class PostCustomerByIdRequest {
    @JacksonXmlProperty(localName = "Header")
    public Header header;
    @JacksonXmlProperty(localName = "Body")
    public RequestBody requestBody;
}
