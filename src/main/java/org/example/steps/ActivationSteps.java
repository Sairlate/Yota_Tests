package org.example.steps;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.example.model.customer.request.CustomerModel;
import org.example.model.customer.request.StatusModel;

import java.time.format.ResolverStyle;

import static io.restassured.RestAssured.given;
import static org.example.specification.Specification.*;

public class ActivationSteps {
    @Step("Получить список свободных номеров")
    public Response getEmptyNumbers(String token, Integer statusCode){
        return given()
                .log().all()
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
                .log().all()
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
    public Response getCustomerById(String token, Integer statusCode, String customerId) {
        return given()
                .log().all()
                .spec(requestSpecification())
                .header("authToken", token)
                .queryParam("customerId", customerId)
                .get("/customer/getCustomerById")
                .then()
                .spec(responseSpecification(statusCode))
                .extract()
                .response();
    }

    @Step("Поиск кастомера по номеру")
    public Response getCustomerByPhone(Integer statusCode, String postCustomerByIdRequest){
        return given()
                .log().all()
                .spec(requestSpecification())
                .contentType(ContentType.XML)
                .body(postCustomerByIdRequest)
                .post("/customer/findByPhoneNumber")
                .then()
                .spec(responseSpecification(statusCode))
                .extract()
                .response();
    }

    @Step("Поменять статус кастомеру")
    public Response setCustomerStatus(String token, Integer statusCode,String customerId, StatusModel statusModel){
        return given()
                .log().all()
                .spec(requestSpecification())
                .header("authToken", token)
                .body(statusModel)
                .pathParam("customerId", customerId)
                .post("/customer/{customerId}/changeCustomerStatus")
                .then()
                .statusCode(statusCode)
                .extract()
                .response();
    }
}
