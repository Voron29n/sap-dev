package com.epam.sap.developers.core.models;

import com.epam.sap.developers.core.entities.SimpleLink;
import com.epam.sap.developers.core.entities.footer.FooterLinksWithTopic;

import java.util.List;
import java.util.Map;

public interface Footer {

    Map<String, String> getFooterSocialMedia();

    Map<Integer, List<SimpleLink>> getFooterCopyright();

    List<FooterLinksWithTopic> getFooterLinksWithTopic();
}
