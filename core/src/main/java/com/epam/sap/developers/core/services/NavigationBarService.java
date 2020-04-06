package com.epam.sap.developers.core.services;

import com.day.cq.wcm.api.Page;
import com.epam.sap.developers.core.entities.SimpleLink;

import java.util.List;

public interface NavigationBarService {

    List<SimpleLink> getSecondLevelPagesLinksWithCssClass(Page mainPage, Page pageWithComponent);
}
