package io.cosmosoftware.kite.apprtcmobile.pages.elements;


import java.util.HashMap;

public class MainPageElements extends PageElements {

    // MAC================================================================
    private String roomIdInputMac = "/AXApplication/AXWindow/AXTextField";
    private String connectBtnMac = "/AXApplication/AXWindow/AXButton[0]";
    private String quitAppBtnMac = "/AXApplication/AXWindow/AXButton[1]";
    private String windowMac = "/AXApplication/AXWindow[0]";
    private String appMac = "/AXApplication";

    public MainPageElements() {

    }

    public HashMap<String, String> populateElement(String os) {
        HashMap<String, String> elements = new HashMap<>();
        if (os.equals("MAC")) {
            getMacElements(elements);
        }
        return elements;
    }

    protected void getMacElements(HashMap<String, String> elements) {
        elements.put(PageElements.MAIN_PAGE_ROOMID_INPUT, this.roomIdInputMac);
        elements.put(PageElements.MAIN_PAGE_CONNECT_BTN, this.connectBtnMac);
        elements.put(PageElements.MAIN_PAGE_CLOSE_BTN, this.quitAppBtnMac);
        elements.put(PageElements.MAIN_PAGE_WINDOW, this.windowMac);
        elements.put(PageElements.MAIN_PAGE_APP, this.appMac);

    }

}
