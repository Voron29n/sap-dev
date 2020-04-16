package com.epam.sap.developers.core.models.bean;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class EventDropdownItemBean {

    @ValueMapValue
    @Default(values = "")
    private String iconNumber;
    @ValueMapValue
    private String dropdownTitle;

    public String getIconNumber() {
        return iconNumber;
    }

    public String getDropdownTitle() {
        return dropdownTitle;
    }
}
