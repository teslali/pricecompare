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
	
	@Given("User searches for product at Migros")
	public void user_searches_for_product_at_migros() throws IOException, InterruptedException {
		userEpicPage.MigrosSearch();

		
	}
	
	@When("User searches product at Kaufland")
	public void user_searches_product_at_kaufland() throws IOException, InterruptedException {
		userEpicPage.KauflandSearch();
	}
	
	@When ("User tries search product at Walmart")
	public void user_tries_search_product_at_walmart() throws IOException, InterruptedException {
		userEpicPage.WalmartSearch();
	}
	
	@When ("User searches product at Amazon US")
	public void user_searches_product_at_amazon_us() throws IOException {
		userEpicPage.AmazonSearch();
	}
	
	@Then("User gathers all prices and compare them")
	public void user_gathers_all_prices_and_compare_them() throws IOException, InterruptedException {
		userEpicPage.ComparePrices();
	}
	

}


