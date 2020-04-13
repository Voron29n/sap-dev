package com.epam.sap.developers.core.models.impl;

import com.epam.sap.developers.core.models.Button;
import com.epam.sap.developers.core.utils.SimpleLinkUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class,
        adapters = {Button.class},
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class ButtonImpl implements Button {

    @ValueMapValue
    private String buttonText;

    @ValueMapValue
    @Default(values = "#")
    private String buttonHref;

    @ValueMapValue
    private boolean isButtonNeed;

    @ValueMapValue
    private boolean isTitleForButtonNeed;

    @ValueMapValue
    private String buttonTitle;

    @Override
    public String getButtonText() {
        return buttonText;
    }

    @Override
    public String getButtonLink() {
        return SimpleLinkUtils.getLinkForButtonFromPathField(buttonHref);
    }

    @Override
    public boolean isButtonNeed() {
        return isButtonNeed;
    }

    @Override
    public boolean isTitleForButtonNeed() {
        return isTitleForButtonNeed;
    }

    @Override
    public String getButtonTitle() {
        return buttonTitle;
    }

    @Override
    public boolean isEmpty() {
        return (!isButtonNeed || (buttonText != null));
    }

}
