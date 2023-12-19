package org.example.steps;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.example.model.user.UserModel;
import org.hamcrest.Matchers;

import static io.restassured.RestAssured.given;
import static org.example.specification.Specification.requestSpecification;
import static org.example.specification.Specification.responseSpecification;

public class LoginSteps {
    @Step("Отправка запроса на авторизацию")
    public Response sendLoginRequest (UserModel user, Integer statusCode){
        return given()
                .spec(requestSpecification())
                .body(user)
                .post("/login")
                .then()
                .spec(responseSpecification(statusCode))
                .body(Matchers.anything())
                .extract()
                .response();

    }

    @Step("Отправка запроса на авторизацию")
    public String sendLoginRequest(UserModel user){
        return sendLoginRequest(user, 200).body().as(UserModel.class).getToken();
    }
}
