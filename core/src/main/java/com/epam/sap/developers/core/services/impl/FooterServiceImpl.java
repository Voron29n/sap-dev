package com.epam.sap.developers.core.services.impl;

import com.epam.sap.developers.core.entities.footer.FooterLinksWithTopic;
import com.epam.sap.developers.core.entities.SimpleLink;
import com.epam.sap.developers.core.services.FooterService;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ValueMap;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.RepositoryException;
import java.util.*;

@Component(
        service = FooterService.class,
        immediate = true)
public class FooterServiceImpl implements FooterService {

    protected static final String SOCIAL_MEDIA_PROPERTY = "socialMedia";
    protected static final String COPYRIGHT_NODE = "copyrightLinks";
    protected static final String LINKS_WITH_TOPIC_NODE = "footerLinks";
    protected static final String FOOTER_LINK_TITLE_PROPERTY = "linkTitle";
    protected static final String FOOTER_LINK_URL_PROPERTY = "linkURL";
    protected static final String FOOTER_LINK_TOPIC_PROPERTY = "topicTitle";
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public List<SimpleLink> footerCopyright(Resource contentOfPageWithFooterProperty) {
        List<SimpleLink> footerCopyrightLinks = new ArrayList<>();

        Node footerCopyrightNode = contentOfPageWithFooterProperty.getChild(COPYRIGHT_NODE).adaptTo(Node.class);

        try {
            NodeIterator footerCopyrightNodes = footerCopyrightNode.getNodes();

            while (footerCopyrightNodes.hasNext()) {
                Node nodeItem = footerCopyrightNodes.nextNode();

                String simpleLinkTitle = nodeItem.getProperty(FOOTER_LINK_TITLE_PROPERTY).getString();
                String simpleLinkURL = nodeItem.getProperty(FOOTER_LINK_URL_PROPERTY).getString();

                footerCopyrightLinks.add(new SimpleLink(simpleLinkTitle, simpleLinkURL));
            }

        } catch (RepositoryException e) {
            logger.error(e.getMessage());
        }

        return footerCopyrightLinks;
    }

    @Override
    public List<FooterLinksWithTopic> footerLinksWithTopic(Resource contentOfPageWithFooterProperty) {
        List<FooterLinksWithTopic> footerLinksWithTopicList = new ArrayList<>();
        Node footerLinksNode = contentOfPageWithFooterProperty.getChild(LINKS_WITH_TOPIC_NODE).adaptTo(Node.class);

        try {
            NodeIterator footerLinksNodes = footerLinksNode.getNodes();

            while (footerLinksNodes.hasNext()) {
                Node topicNode = footerLinksNodes.nextNode();
                List<SimpleLink> linksWithTopic = new ArrayList<>();

                NodeIterator topicNodes = topicNode.getNodes();
                while (topicNodes.hasNext()) {
                    Node topicNodesItem = topicNodes.nextNode();

                    String simpleLinkTitle = topicNodesItem.getProperty(FOOTER_LINK_TITLE_PROPERTY).getString();
                    String simpleLinkURL = topicNodesItem.getProperty(FOOTER_LINK_URL_PROPERTY).getString();

                    linksWithTopic.add(new SimpleLink(simpleLinkTitle, simpleLinkURL));
                }
                String linkTopic = topicNode.getProperty(FOOTER_LINK_TOPIC_PROPERTY).getString();
                footerLinksWithTopicList.add(new FooterLinksWithTopic(linkTopic, linksWithTopic));
            }

        } catch (RepositoryException e) {
            logger.error(e.getMessage());
        }

        return footerLinksWithTopicList;
    }

    @Override
    public Map<String, String> footerSocialMedia(Resource contentOfPageWithFooterProperty) {
        Map<String, String> socialMedia = new HashMap<>();

        ValueMap propertiesOfContentAtPageWithFooter = contentOfPageWithFooterProperty.getValueMap();

        List<String> selectedSocialMedia = Arrays.asList((String[]) propertiesOfContentAtPageWithFooter.get(SOCIAL_MEDIA_PROPERTY));

        selectedSocialMedia.stream()
                .forEach(socialMediaName -> socialMedia.put(socialMediaName, (String) propertiesOfContentAtPageWithFooter.get(socialMediaName))
        );

        return socialMedia;
    }

}
