package utilities;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class Listeners extends ExtentManager implements ITestListener {
	@Override
	public void onTestStart(ITestResult result) {

		test = extent.createTest(result.getName());

		System.out.println("Test started: " + result.getMethod().getMethodName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		if (result.getStatus() == ITestResult.SUCCESS) {
			test.log(Status.PASS, "Pass Test case is: " + result.getName());
		}

	}

	@Override
	public void onTestFailure(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			test.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " - Test Case Failed", ExtentColor.RED));
			test.log(Status.FAIL,
					MarkupHelper.createLabel(result.getThrowable() + " - Test Case Failed", ExtentColor.RED));
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		if (result.getStatus() == ITestResult.SKIP) {
			test.log(Status.SKIP, "Skipped Test case is: " + result.getName());
		}
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedWithTimeout(result);
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onStart(context);
	}

	@Override
	public void onFinish(ITestContext context) {
		try {
			Map<String, Object> testResult = new HashMap<>();
			testResult.put("TotalTestCaseCount", context.getAllTestMethods().length);
			testResult.put("PassedTestCaseCount", context.getPassedTests().size());
			testResult.put("FailedTestCaseCount", context.getFailedTests().size());
			testResult.put("SkippedTestCaseCount", context.getSkippedTests().size());

			ObjectMapper mapper = new ObjectMapper();

			mapper.enable(SerializationFeature.INDENT_OUTPUT);
			String filePath = System.getProperty("user.dir") + "\\extent-report\\TestExecutionReport.json";
			mapper.writeValue(new File(filePath), testResult);
		} catch (IOException e) {
			throw new RuntimeException("Error occurred while writing to TestExecutionReport.json file: ", e);
		}
	}

}
