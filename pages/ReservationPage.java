package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;

public class ReservationPage {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(name = "firstname")
    private WebElement firstName;

    @FindBy(name = "lastname")
    private WebElement lastName;

    @FindBy(name = "email")
    private WebElement emailField;

    @FindBy(name = "email_confirm")
    private WebElement confirmEmailField;

    @FindBy(name = "phone")
    private WebElement phoneField;

    @FindBy(css = "button[type='submit']")
    private WebElement continueButton;

    public ReservationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void fillGuestDetails() {
        wait.until(ExpectedConditions.visibilityOf(firstName)).sendKeys("John");
        lastName.sendKeys("Doe");
        emailField.sendKeys("test@example.com");
        confirmEmailField.sendKeys("test@example.com");
        phoneField.sendKeys("+359888000111");
        continueButton.click();
    }
}
