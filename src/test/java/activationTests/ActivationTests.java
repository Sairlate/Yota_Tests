package activationTests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.restassured.response.Response;
import org.example.model.customer.CustomerModel;
import org.example.model.phone.PhonesModel;
import org.example.service.BaseTest;
import org.testng.annotations.Test;
import org.example.utils.StringBuilder;


public class ActivationTests extends BaseTest {

    @Test
    public void activateCustomer() throws JsonProcessingException, InterruptedException {
        login("admin", "password");
        Response getNumber = ACTIVATION_STEPS.getEmptyNumbers(token, 200);

        PhonesModel phonesModel = new ObjectMapper().readValue(getNumber.getBody().asString(), PhonesModel.class);
        Long phone = phonesModel.getPhones().get(0).getPhone();
        CustomerModel customerModel = new CustomerModel().withName(StringBuilder.genLatin(7)).withPhone(phone).withAdditionalParameters(new CustomerModel.AdditionalParameters().withString("string"));
        Response createCustomer = ACTIVATION_STEPS.createNewCustomer(token, 200, customerModel);

        Thread.sleep(120000);

        ObjectNode node = new ObjectMapper().readValue(createCustomer.getBody().asString(), ObjectNode.class);
        String id = node.get("id").asText();
        Response getCustomer = ACTIVATION_STEPS.getCustomerByID(token, 200, id);

    }
}
