package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private final By signInButton = By.id("nav-link-accountList");
    private final By emailField = By.name("email");
    private final By continueButton = By.id("continue");
    private final By passwordField = By.id("ap_password");
    private final By loginButton = By.id("signInSubmit");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void login(String email, String password) {
        click(signInButton);
        enterText(emailField, email);
        click(continueButton);
        enterText(passwordField, password);
        click(loginButton);
    }
}