package io.cosmosoftware.kite.apprtcmobile.pages.elements;

import java.util.HashMap;

public abstract class PageElements {

    // MAIN PAGE ITEMS
    public static final String MAIN_PAGE_ROOMID_INPUT = "MAIN_PAGE_ROOMID_INPUT";
    public static final String MAIN_PAGE_CONNECT_BTN = "MAIN_PAGE_CONNECT_BTN";
    public static final String MAIN_PAGE_CLOSE_BTN = "MAIN_PAGE_CLOSE_BTN";
    public static final String MAIN_PAGE_WINDOW = "MAIN_PAGE_WINDOW";
    public static final String MAIN_PAGE_APP = "MAIN_PAGE_APP";

    public HashMap<String, String> populateElement(String os) {
        HashMap<String, String> elements = new HashMap<>();
        if (os.equals("MAC")) {
            getMacElements(elements);
        }
        return elements;
    }

    abstract protected void getMacElements(HashMap<String, String> elements);
}
