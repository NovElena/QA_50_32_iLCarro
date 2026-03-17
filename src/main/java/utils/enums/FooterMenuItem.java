package utils.enums;

public enum FooterMenuItem {
    ICON_FACEBOOK("//a[@href='https://www.facebook.com/']"),
    ICON_TELEGRAM("//a[@href='https://telegram.org/']"),
    ICON_VK("//a[@href='https://vk.com/']"),
    ICON_INSTAGRAM("//a[@href='https://www.instagram.com/']"),
    ICON_SLACK("//a[@href='https://slack.com/']");

    private String locator;

    FooterMenuItem(String locator) {
        this.locator = locator;
    }

    public String getLocator() {
        return locator;
    }
}
