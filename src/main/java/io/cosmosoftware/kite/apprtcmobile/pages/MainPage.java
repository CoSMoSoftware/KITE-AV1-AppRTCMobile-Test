package io.cosmosoftware.kite.apprtcmobile.pages;

import io.cosmosoftware.kite.apprtcmobile.pages.elements.MainPageElements;
import io.cosmosoftware.kite.exception.KiteInteractionException;
import io.cosmosoftware.kite.interfaces.Runner;
import io.cosmosoftware.kite.apprtcmobile.pages.elements.PageElements;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.remote.RemoteWebDriver;

import javax.json.JsonArray;
import javax.json.JsonObject;

public class MainPage extends Page {

    private final MainPageElements mainPageElements = new MainPageElements();
    private JsonObject coordinates;

    public MainPage(Runner runner, JsonObject coordinates) {
        super(runner);
        this.os = ((RemoteWebDriver) runner.getWebDriver()).getCapabilities().getPlatform().toString();
        this.elements = this.mainPageElements.populateElement(this.os);
        this.coordinates = coordinates;
    }

    public void setRoom(String address) throws KiteInteractionException {
        sendKeys(getElement(PageElements.MAIN_PAGE_ROOMID_INPUT), address);
    }

    public void joinRoom() throws KiteInteractionException {
        click(getElement(PageElements.MAIN_PAGE_CONNECT_BTN));
    }

    public boolean quitApplication() throws KiteInteractionException {
        try {
        click(getElement(PageElements.MAIN_PAGE_CLOSE_BTN));
        } catch  (Exception e) {
            // Quitting should throw an error which is expected
            return true;
        }
        return false;
    }

    public Rectangle getLogRectangle() {
        JsonArray array = this.coordinates.getJsonArray("log");
        return new Rectangle(array.getInt(0),array.getInt(1),array.getInt(2),array.getInt(3));
    }

    public Rectangle getRemoteVideoRectangle() {
        JsonArray array = this.coordinates.getJsonArray("video");
        return new Rectangle(array.getInt(0),array.getInt(1),array.getInt(2),array.getInt(3));
    }
}
