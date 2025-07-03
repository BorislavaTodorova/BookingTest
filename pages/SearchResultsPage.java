package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;
import java.util.List;

public class SearchResultsPage {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(css = "div[data-filters-group='pri'] input")
    private List<WebElement> priceFilters;

    @FindBy(css = "div[data-filters-group='ht_id'] input")
    private List<WebElement> hotelFilters;

    @FindBy(css = "div[data-testid='property-card']")
    private List<WebElement> propertyCards;

    public SearchResultsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void applyFilters() {
        // Set max price to 100 (usually the first filter option)
        wait.until(ExpectedConditions.elementToBeClickable(priceFilters.get(0))).click();

        // Filter only hotels (usually the first or second option)
        wait.until(ExpectedConditions.elementToBeClickable(hotelFilters.get(0))).click();
    }

    public void selectThirdHotel() {
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.cssSelector("div[data-testid='property-card']"), 2));
        propertyCards.get(2).click();
    }
}
