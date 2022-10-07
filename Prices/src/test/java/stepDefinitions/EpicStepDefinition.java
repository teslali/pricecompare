package stepDefinitions;

import java.io.IOException;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.UserEpicPage;
import utils.TestContextSetup;

public class EpicStepDefinition {
	UserEpicPage userEpicPage;
	TestContextSetup testContextSetup;

	public EpicStepDefinition(TestContextSetup testContextSetup) {
		this.testContextSetup = testContextSetup;
		this.userEpicPage = testContextSetup.pageObjectManager.getUserEpicPage();

	}
	
	@Given("I want to write a step with precondition")
	public void i_want_to_write_a_step_with_precondition () throws IOException, InterruptedException {
		userEpicPage.MigrosSearch();
		userEpicPage.KauflandSearch();
		userEpicPage.WalmartSearch();
		userEpicPage.AmazonSearch();
		userEpicPage.ComparePrices();
	}

	

}


