package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    protected static WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public void click(By locator) {
        waitUntilClickable(locator).click();
    }

    public void toggleCheckbox(By locator) {
        WebElement checkbox = driver.findElement(locator);
        if (!checkbox.isSelected()) {
            checkbox.click();
        }
    }
    
    private WebElement waitUntilClickable(By locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.elementToBeClickable(locator));
    }
    
    public void moveToElement(By locator) {
        WebElement element = driver.findElement(locator);
        new org.openqa.selenium.interactions.Actions(driver).moveToElement(element).perform();
    }


    public void pressEnter(By locator) {
        driver.findElement(locator).sendKeys(org.openqa.selenium.Keys.ENTER);
    }

    public void scrollToElement(By locator) {
        WebElement element = driver.findElement(locator);
        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void enterText(By locator, String text) {
        driver.findElement(locator).sendKeys(text);
    }

    public String getText(By locator) {
        return driver.findElement(locator).getText();
    }

    public void selectDropdown(By locator, String visibleText) {
        Select dropdown = new Select(driver.findElement(locator));
        dropdown.selectByVisibleText(visibleText);
    }
}