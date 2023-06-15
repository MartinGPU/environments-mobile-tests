package com.marat.tests.browserstack;

import com.codeborne.selenide.Condition;
import io.appium.java_client.MobileBy;
import io.qameta.allure.AllureId;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static io.qameta.allure.Allure.step;

@Feature("Settings")
@Tag("selenide_browser_stack")
public class BrowserStackExistingSettingsButtonTest extends BrowserStackTestBase {

    @DisplayName("Settings button")
    @AllureId("22994")
    @Owner(value = "Marat")
    @Test
    void existingSettingsButton() {
        step("Open settings menu", () -> {
            $(MobileBy.id("org.wikipedia.alpha:id/menu_overflow_button")).click();
        });
        step("Existing settings button", () -> {
            $(MobileBy.id("org.wikipedia.alpha:id/explore_overflow_settings")).shouldHave(Condition.text("Settings"));
        });
    }
}
