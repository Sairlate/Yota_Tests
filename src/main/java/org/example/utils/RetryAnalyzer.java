package org.example.utils;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {

    @Override
    public boolean retry(ITestResult result) {
        if (!result.isSuccess()) {
            return true;
        }
        return false;
    }
}
