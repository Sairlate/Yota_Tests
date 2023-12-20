package org.example.model.customer.response;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;
import org.example.model.customer.request.Header;

@Data
@With
@AllArgsConstructor
@NoArgsConstructor
public class GetCustomerStatusResponse {
    @JacksonXmlProperty(localName = "Header")
    public Header header;
    @JacksonXmlProperty(localName = "Body")
    public ResponseBody responseBody;
}
