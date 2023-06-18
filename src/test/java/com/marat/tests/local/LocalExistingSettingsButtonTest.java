package com.marat.tests.local;

import com.codeborne.selenide.Condition;
import com.marat.tests.GenTestBase;
import io.appium.java_client.MobileBy;
import io.qameta.allure.AllureId;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

@Feature("Settings")
@Tag("selenide_local")
public class LocalExistingSettingsButtonTest extends GenTestBase {

    @DisplayName("Settings button")
    @AllureId("22994")
    @Owner(value = "Marat")
    @Test
    void existingSettingsButton() {
        back();
        step("Open settings menu", () -> {
            $(MobileBy.id("org.wikipedia.alpha:id/view_announcement_action_positive")).click();
        });
        step("Existing settings button", () -> {
            $(MobileBy.id("org.wikipedia.alpha:id/feed_content_type_title")).shouldHave(Condition.text("Featured article"));
        });
    }
}