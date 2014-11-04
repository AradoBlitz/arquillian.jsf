package arquillian.jsf;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;


@SessionScoped
public class LoginController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	static{
		System.out.println("I am " + LoginController.class);
	}
	
	public String login(){
		
		return "home.xhtml";
	}
}
