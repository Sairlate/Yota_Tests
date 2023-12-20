package org.example.service;

import org.example.model.user.UserModel;
import org.example.steps.Steps;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;

public class BaseTest implements Steps {
    public String token;


    public void login(String login, String password){
        token = LOGIN_STEPS.sendLoginRequest(new UserModel().withLogin(login).withPassword(password));
    }



}
