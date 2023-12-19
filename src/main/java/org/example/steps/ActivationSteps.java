package org.example.steps;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.example.model.customer.CustomerModel;

import static io.restassured.RestAssured.given;
import static org.example.specification.Specification.requestSpecification;
import static org.example.specification.Specification.responseSpecification;

public class ActivationSteps {
    @Step("Получить список свободных номеров")
    public Response getEmptyNumbers(String token, Integer statusCode){
        return given()
                .spec(requestSpecification())
                .header("authToken", token)
                .get("/simcards/getEmptyPhone")
                .then()
                .spec(responseSpecification(statusCode))
                .extract()
                .response();
    }

    @Step("Создать нового кастомера")
    public Response createNewCustomer(String token, Integer statusCode, CustomerModel customerModel){
        return given()
                .spec(requestSpecification())
                .header("authToken", token)
                .body(customerModel)
                .post("/customer/postCustomer")
                .then()
                .spec(responseSpecification(statusCode))
                .extract()
                .response();
    }

    @Step("Получить кастомера по ID")
    public Response getCustomerByID(String token, Integer statusCode, String customer_id) {
        return given()
                .spec(requestSpecification())
                .header("authToken", token)
                .queryParam("?customer_id", customer_id)
                .get("/customer/getCustomerById")
                .then()
                .spec(responseSpecification(statusCode))
                .extract()
                .response();
    }
}
