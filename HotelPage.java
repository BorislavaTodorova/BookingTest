package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;
import java.util.*;

public class HotelPage {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(css = "div[data-room-name]")
    private List<WebElement> roomContainers;

    public HotelPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void selectLowestPricedRoom() {
        WebElement lowestRoom = null;
        double lowestPrice = Double.MAX_VALUE;

        for (WebElement room : roomContainers) {
            try {
                String priceText = room.getText().replaceAll("[^\\d.]", "");
                double price = Double.parseDouble(priceText);
                if (price <= lowestPrice) {
                    lowestPrice = price;
                    lowestRoom = room;
                }
            } catch (Exception e) {
                // skip invalid price format
            }
        }

        if (lowestRoom != null) {
            lowestRoom.findElement(By.xpath(".//button[contains(text(),'Reserve') or contains(text(),"I'll reserve")]")).click();
        }
    }
}