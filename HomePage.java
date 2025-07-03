package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;

public class HomePage {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(id = "ss")
    private WebElement destinationInput;

    @FindBy(css = ".xp__dates-inner")
    private WebElement dateField;

    @FindBy(css = "td[data-date='2025-12-22']")
    private WebElement checkInDate;

    @FindBy(css = "td[data-date='2025-12-30']")
    private WebElement checkOutDate;

    @FindBy(id = "xp__guests__toggle")
    private WebElement guestsToggle;

    @FindBy(css = "button[aria-label='Decrease number of Adults']")
    private WebElement decreaseAdults;

    @FindBy(css = "button[aria-label='Increase number of Adults']")
    private WebElement increaseAdults;

    @FindBy(css = ".sb-searchbox__button")
    private WebElement searchButton;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void setDestination(String destination) {
        wait.until(ExpectedConditions.visibilityOf(destinationInput)).clear();
        destinationInput.sendKeys(destination);
    }

    public void selectDates() {
        dateField.click();
        wait.until(ExpectedConditions.elementToBeClickable(checkInDate)).click();
        wait.until(ExpectedConditions.elementToBeClickable(checkOutDate)).click();
    }

    public void setGuests(int adults) {
        guestsToggle.click();
        // Reset adults to 1 before increasing to 2
        decreaseAdults.click();
        increaseAdults.click();
    }

    public void submitSearch() {
        searchButton.click();
    }
}
