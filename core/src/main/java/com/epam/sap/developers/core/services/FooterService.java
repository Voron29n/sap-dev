package com.epam.sap.developers.core.services;

import com.epam.sap.developers.core.entities.footer.FooterLinksWithTopic;
import com.epam.sap.developers.core.entities.SimpleLink;
import org.apache.sling.api.resource.Resource;

import java.util.List;
import java.util.Map;

public interface FooterService {

    List<SimpleLink> footerCopyright(Resource contentOfPageWithFooterProperty);

    List<FooterLinksWithTopic> footerLinksWithTopic(Resource contentOfPageWithFooterProperty);

    Map<String, String> footerSocialMedia(Resource contentOfPageWithFooterProperty);

}
