package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {

    private final By allMenuButton = By.id("nav-hamburger-menu");
    private final By allMenuExpand = By.linkText("See all");
    private final By videoGamesCategory = By.xpath("//div[text()='Video Games']");
    private final By allVideoGamesLink = By.linkText("All Video Games");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void selectVideoGamesCategory() throws InterruptedException {
        click(allMenuButton);
        scrollToElement(allMenuExpand);
        click(allMenuExpand);
        scrollToElement(videoGamesCategory);
        click(videoGamesCategory);
        Thread.sleep(2000);
        moveToElement(allVideoGamesLink);
        pressEnter(allVideoGamesLink);
    }
}