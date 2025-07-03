package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;

public class PaymentPage {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(xpath = "//*[contains(text(),'Payment') or contains(text(),'Card number') or @name='cc_number']")
    private WebElement paymentElement;

    public PaymentPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public boolean isPaymentVisible() {
        return wait.until(ExpectedConditions.visibilityOf(paymentElement)).isDisplayed();
    }

    public void takeScreenshot(String filePath) throws Exception {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File screenshot = ts.getScreenshotAs(OutputType.FILE);
        Files.copy(screenshot.toPath(), new File(filePath).toPath());
    }
}
