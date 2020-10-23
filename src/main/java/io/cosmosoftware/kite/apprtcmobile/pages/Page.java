package io.cosmosoftware.kite.apprtcmobile.pages;

import io.cosmosoftware.kite.exception.KiteInteractionException;
import io.cosmosoftware.kite.interfaces.Runner;
import io.cosmosoftware.kite.pages.BasePage;
import java.util.HashMap;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public abstract class Page extends BasePage {

    protected String os;
    protected HashMap<String, String> elements;

    protected Page(Runner runner) {
        super(runner);
    }

    protected WebElement getElement(String elementName) throws KiteInteractionException {
        try {
            return this.webDriver.findElement(By.xpath(this.elements.get(elementName)));
        } catch (Exception e) {
            logger.debug(e.getStackTrace());
            throw new KiteInteractionException("Could not find element: " + elementName);
        }
    }
}
