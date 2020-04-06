package com.epam.sap.developers.core.entities.footer;

import com.epam.sap.developers.core.entities.SimpleLink;

import java.util.List;

public class FooterLinksWithTopic {

    private String linkTopic;

    private List<SimpleLink> simpleLinks;

    public FooterLinksWithTopic(String linkTopic, List<SimpleLink> simpleLinks) {
        this.linkTopic = linkTopic;
        this.simpleLinks = simpleLinks;
    }

    public String getLinkTopic() {
        return linkTopic;
    }

    public List<SimpleLink> getSimpleLinks() {
        return simpleLinks;
    }
}
