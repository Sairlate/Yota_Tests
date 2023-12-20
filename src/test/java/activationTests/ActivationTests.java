package activationTests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import io.restassured.response.Response;
import org.example.model.customer.request.*;
import org.example.model.customer.response.CustomerResponse;
import org.example.model.customer.response.GetCustomerStatusResponse;
import org.example.model.customer.response.Return;
import org.example.model.phone.PhonesModel;
import org.example.service.BaseTest;
import org.example.utils.RetryAnalyzer;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.example.utils.StringBuilder;


public class ActivationTests extends BaseTest {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private static final XmlMapper XML_MAPPER = new XmlMapper();


    @Test(description = "Тест на полный цикл активации пользователя", retryAnalyzer = RetryAnalyzer.class)
    public void activateCustomer() throws JsonProcessingException, InterruptedException {
        //Авторизация под одним из пользователей
        login("admin", "password");

        //Получение списка свободных номеров для дальнейшего использования
        Response number = ACTIVATION_STEPS.getEmptyNumbers(token, 200);

        //Создание нового кастомера, используя один из доступеных номеров
        AdditionalParameters additionalParameter = new AdditionalParameters().withString("string");
        String customerName = StringBuilder.genLatin(7);
        PhonesModel phonesModel = OBJECT_MAPPER.readValue(number.getBody().asString(), PhonesModel.class);
        Long phone = phonesModel.getPhones().get(0).getPhone();
        CustomerModel customerModel = new CustomerModel().withName(customerName).withPhone(phone).
                withAdditionalParameters(additionalParameter);
        Response createdCustomer = ACTIVATION_STEPS.createNewCustomer(token, 200, customerModel);

        //Ожидание в надежде на активацию
        Thread.sleep(120000);

        //Получение созданного кастомера
        ObjectNode node = OBJECT_MAPPER.readValue(createdCustomer.getBody().asString(), ObjectNode.class);
        String customerId = node.get("id").asText();
        Response existingCustomer = ACTIVATION_STEPS.getCustomerById(token, 200, customerId);
        //Ожидание, что статус был сменен
        CustomerResponse customerResponse = OBJECT_MAPPER.readValue(existingCustomer.getBody().asString(), CustomerResponse.class);
        String passport = customerResponse.getCustomerReturn().getPd();
        Assert.assertEquals(existingCustomer.getBody().as(CustomerResponse.class),
                new CustomerResponse().withCustomerReturn(new Return().withName(customerName).withCustomerId(customerId).withStatus("ACTIVE")
                        .withPhone(phone).withPd(passport).withAdditionalParameters(additionalParameter)));

        //Получение сохраненного кастомера по номеру телефона
        String xmlString = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                "<ns3:Envelope xmlns:ns2=\"soap\" xmlns:ns3=\"http://schemas.xmlsoap.org/soap/envelope\">\n" +
                "    <ns2:Header>\n" +
                "        <authToken>"+ token +"</authToken>\n" +
                "    </ns2:Header>\n" +
                "    <ns2:Body>\n" +
                "        <phoneNumber>"+ phone +"</phoneNumber>\n" +
                "    </ns2:Body>\n" +
                "</ns3:Envelope>";
        Response savedCustomer = ACTIVATION_STEPS.getCustomerByPhone(200, xmlString);
        GetCustomerStatusResponse xmlResponse = XML_MAPPER.readValue(savedCustomer.getBody().asString(), GetCustomerStatusResponse.class);
        String customerIdXml = xmlResponse.getResponseBody().getCustomerId();
        Assert.assertEquals(customerId, customerIdXml);

        //Изменение статуса
        String status = StringBuilder.genLatin(5);
        Response changeStatus = ACTIVATION_STEPS.setCustomerStatus(token, 200, customerId,
                new StatusModel().withStatus(status));
        Response changedCustomerStatus = ACTIVATION_STEPS.getCustomerById(token, 200, customerId);

        //Ожидание, что статус поменялся
        Assert.assertEquals(changedCustomerStatus.getBody().as(CustomerResponse.class),
                new CustomerResponse().withCustomerReturn(new Return().withName(customerName).withCustomerId(customerId).withStatus(status)
                        .withPhone(phone).withPd(passport).withAdditionalParameters(additionalParameter)));
    }

}
