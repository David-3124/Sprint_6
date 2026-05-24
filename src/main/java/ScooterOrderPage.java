import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ScooterOrderPage {

    private WebDriver webDriver;

    // Заказ: первая форма "Для кого самокат"
    private By nameField = By.xpath(".//input[contains(@placeholder, 'Имя')]");
    private By surnameField = By.xpath(".//input[contains(@placeholder, 'Фамилия')]");
    private By addressField = By.xpath(".//input[contains(@placeholder, 'Адрес')]");
    private By metroField = By.xpath(".//input[contains(@placeholder, '* Станция метро')]");
    private By phoneField = By.xpath(".//input[contains(@placeholder, 'Телефон: на него позвонит')]");
    private By nextButton = By.xpath(".//button[contains(@class, 'Button_Button__ra12g') and text()='Далее']");

    // Заказ: первая форма "Про аренду"
    private By whenToBringButton = By.xpath(".//input[contains(@placeholder, 'Когда привезти самокат')]");
    private By rentalPeriodButton = By.xpath(".//div[contains(@class, 'Dropdown-control')]");
    private By colorBlackButton = By.xpath(".//input[contains(@id, 'black')]");
    private By colorGreyButton = By.xpath(".//input[contains(@id, 'grey')]");
    private By commentButton = By.xpath(".//input[contains(@placeholder, 'Комментарий для курьера')]");
    private By orderButton = By.xpath(".//button[2][contains(@class, 'Button_Button__ra12g') and text()='Заказать']");

    //Форма "Хотите оформить заказ?"
    private By yesButton = By.xpath(".//button[contains(@class, 'Button_Button__ra12g') and text()='Да']");
    private By noButton = By.xpath(".//button[contains(@class, 'Button_Button__ra12g') and text()='Нет']");
    private By numberOrder = By.xpath(".//div[1]/div[contains(@class, 'Order_Text__2broi')]");


    public ScooterOrderPage(WebDriver driver) {
        this.webDriver = driver;
    }

    public void selectMetro(String metroName) {
        webDriver.findElement(metroField).click();
        webDriver.findElement(By.xpath(".//div[contains(@class, 'Order_Text__2broi') and text()='" + metroName + "']")).click();
    }

    public void selectDate(int date){
        webDriver.findElement(whenToBringButton).click();
        webDriver.findElement(By.xpath(".//div[contains(@class, 'react-datepicker__day') and text()='" + date + "']")).click();
    }

    public void numberOfDays(int days){
        webDriver.findElement(rentalPeriodButton).click();
        webDriver.findElement(By.xpath(".//div[" + days + "][contains(@class, 'Dropdown-option')]")).click();
    }

    public void selectColors(String color){
        if (color.equals("black")) webDriver.findElement(colorBlackButton).click();
        else if (color.equals("grey")) webDriver.findElement(colorGreyButton).click();
    }

    public void commentCourier(String comment){
        webDriver.findElement(commentButton).sendKeys(comment);
    }

    public boolean placingOrder(boolean confirm){
        if (confirm) {
            webDriver.findElement(yesButton).click();
            List<WebElement> orderNumberElements = new WebDriverWait(webDriver, Duration.ofSeconds(1))
                    .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                            By.xpath(".//div[contains(@class, 'Order_Text__2broi') and contains(text(), 'Номер заказа:')]")
                    ));
            return !orderNumberElements.isEmpty() && orderNumberElements.get(0).isDisplayed();
        } else {
            return false;
        }
    }

    public String setNumberOrder(){
        By orderNumberLabel = numberOrder;
        return webDriver.findElement(orderNumberLabel).getText();
    }

    public void formForWhom(String name, String surname, String address, String metroName, String phone){
        webDriver.findElement(nameField).sendKeys(name);
        webDriver.findElement(surnameField).sendKeys(surname);
        webDriver.findElement(addressField).sendKeys(address);
        selectMetro(metroName);
        webDriver.findElement(phoneField).sendKeys(phone);
        webDriver.findElement(nextButton).click();
    }

    public void formOfRent(int date, int days, String color, String comment, boolean confirm){
        selectDate(date);
        numberOfDays(days);
        selectColors(color);
        commentCourier(comment);
        webDriver.findElement(orderButton).click();
        placingOrder(confirm);
        String text = setNumberOrder();
        System.out.println(text);
    }
}

