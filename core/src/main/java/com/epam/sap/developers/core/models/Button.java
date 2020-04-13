package com.epam.sap.developers.core.models;

public interface Button {

    String getButtonText();

    String getButtonLink();

    String getButtonTitle();

    boolean isTitleForButtonNeed();

    boolean isButtonNeed();

    boolean isEmpty();
}
