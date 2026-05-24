import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePageQaScooter {

    private WebDriver webDriver;

    // Кнопка Заказать верхняя
    private By orderTopButton = By.xpath(".//div[2]/button[1][contains(@class, 'Button_Button__ra12g') and text()='Заказать']");

    // Кнопка Заказать нижняя
    private By orderBottomButton = By.xpath(".//div[5]/button[contains(@class, 'Button_Button__ra12g') and text()='Заказать']");

    // Кнопка Статус заказа
    private By orderStatusButton = By.xpath(".//button[2][contains(@class, 'Header_Link__1TAG7') and text()='Статус заказа']");

    // Кнопка закрытия куков
    private By cookiesButton = By.xpath(".//div[2]/button[contains(@class, 'App_CookieButton__3cvqF')]");

    // Разделы Вопросы о важном
    private By[] questionLocators = {
            By.xpath(".//div[1]/div[@id='accordion__heading-0']"),
            By.xpath(".//div[1]/div[@id='accordion__heading-1']"),
            By.xpath(".//div[1]/div[@id='accordion__heading-2']"),
            By.xpath(".//div[1]/div[@id='accordion__heading-3']"),
            By.xpath(".//div[1]/div[@id='accordion__heading-4']"),
            By.xpath(".//div[1]/div[@id='accordion__heading-5']"),
            By.xpath(".//div[1]/div[@id='accordion__heading-6']"),
            By.xpath(".//div[1]/div[@id='accordion__heading-7']")
    };

    public HomePageQaScooter(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void clickOrderTopButton(){
        webDriver.findElement(orderTopButton).click();
    }

    public void clickOrderBottomButton(){
        webDriver.findElement(orderBottomButton).click();
    }

    public void clickOrderStatusButton() {
        webDriver.findElement(orderStatusButton).click();
    }

    public void cookiesButton() {
        webDriver.findElement(cookiesButton).click();
    }

    public void clickQuestion(int numberButton) {
        webDriver.findElement(questionLocators[numberButton]).click();
    }
}
