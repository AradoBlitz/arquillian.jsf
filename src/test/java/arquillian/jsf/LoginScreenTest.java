package arquillian.jsf;

import static org.junit.Assert.*;

import java.net.URL;

import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.graphene.Graphene;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@RunWith(Arquillian.class)
public class LoginScreenTest {

	@FindBy(tagName="li")
	private WebElement facesMessage;
	
	@FindBy(id="login")
	private WebElement loginButton;
	
	@FindBy
	private WebElement userName;

	@FindBy
	private WebElement password;

	@Drone
	private WebDriver browser;

	@ArquillianResource
	private URL deploymentUrl; 

	@Test
	public void loginSuccess() throws Exception {
		//Describe what is "object page" pattern
		//Browser nullpointer - add Arquillian tests runner.Add arquillian dependency.
		//deploy package.
		browser.get(deploymentUrl.toExternalForm()+"login.jsf");
		userName.sendKeys("demo");
		password.sendKeys("demo");
		Graphene.guardHttp(loginButton).click();
		assertEquals("Welcom text should be present.","Welcome",facesMessage.getText().trim());
	}
}
