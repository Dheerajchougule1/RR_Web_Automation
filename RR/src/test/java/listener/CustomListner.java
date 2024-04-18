package listener;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import utility_RR.Utility_RR.CustomReporterRed;

public class CustomListner implements ITestListener  {
	
	@Override
    public void onTestFailure(ITestResult result) {
		CustomReporterRed.log("Test Case Failed: " + result.getName(),"red");
        CustomReporterRed.log("Error Message: " + result.getThrowable().getMessage(),"red");
    }

    @Override
    public void onTestStart(ITestResult result) {
//        Reporter.log("Starting Test Case: " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
//        Reporter.log("Test Case Passed: " + result.getName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        Reporter.log("Test Case Skipped: " + result.getName());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
//        Reporter.log("Test Case Failed but within success percentage: " + result.getName());
    }

    @Override
    public void onStart(ITestContext context) {
//        Reporter.log("Test Suite Started: " + context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
//        Reporter.log("Test Suite Finished: " + context.getName());
    }	

	
	
}
