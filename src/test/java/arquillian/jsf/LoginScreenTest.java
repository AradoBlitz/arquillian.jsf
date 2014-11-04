package arquillian.jsf;

import static org.junit.Assert.*;

import java.io.File;
import java.net.URL;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.graphene.Graphene;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.asset.StringAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@RunWith(Arquillian.class)
public class LoginScreenTest {

	@Deployment(testable=false)
	public static WebArchive create(){
		return ShrinkWrap.create(WebArchive.class)
				.addClasses(LoginController.class)
				.addAsWebResource(new File("src/main/webapp","login.xhtml"))
				.addAsWebResource(new File("src/main/webapp","home.xhtml"))
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
				.addAsWebInfResource(new StringAsset("<faces-config version=\"2.0\"/>"), "faces-config.xml");
	}
	
	@FindBy(tagName="h2")
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
		
		browser.get(deploymentUrl.toExternalForm()+"login.jsf");
		
		assertNotNull("UserName field should be provided.", userName);
		userName.sendKeys("demo");
		
		assertNotNull("Password field should be provided.", password);
		password.sendKeys("demo");
		
		Graphene.guardHttp(loginButton).click();
		
		assertNotNull("FacesMessage field should be provided.", facesMessage);
		assertEquals("Welcom text should be present.","Welcome",facesMessage.getText().trim());
	}
}
