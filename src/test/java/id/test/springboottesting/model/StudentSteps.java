package id.test.springboottesting.model;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class StudentSteps {
	private Student user = new Student();
	 
    @Given("^that the student (.*) is given a task to clear (.*) certification exam$")
    public void studentPassCertification(String name, String certication) throws Throwable {
        user.setName(name);
        user.setCertification(certication);
    }
 
    @When("^(.*) got (\\d+) marks in exam$")
    public void gotPassMarks(String name, int marks) throws Throwable {
        user.setName(name);
        user.setMarks(marks);
    }
 
    @Then("^(.*) passed (.*) certification$")
    public void certifiedYes(String name, String certification) throws Throwable {
        assertThat(name, is(user.getName()));
        assertThat(user.getCertification(), equalTo("Java"));
        assertThat(user.getResult(), is(true));
    }
    
	/*
	 * @Given("^that the student (.*) is given a task to clear (.*) certification exam$"
	 * ) public void studentFailCertification(String name, String certication)
	 * throws Throwable { user.setName(name); user.setCertification(certication); }
	 * 
	 * @When("^(.*) got (\\d+) marks in exam$") public void gotFailMarks(String
	 * name, int marks) throws Throwable { user.setName(name); user.setMarks(marks);
	 * }
	 */
    @Then("^(.*) failed (.*) certification$")
    public void certifiedNo(String name, String certification) throws Throwable {
        assertThat(name, is(user.getName()));
        assertThat(user.getCertification(), equalTo("Java"));
        assertThat(user.getResult(), is(false));
    }
    
    

}
