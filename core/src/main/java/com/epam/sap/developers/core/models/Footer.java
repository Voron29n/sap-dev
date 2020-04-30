package com.epam.sap.developers.core.models;

import com.epam.sap.developers.core.entities.SimpleLink;
import com.epam.sap.developers.core.entities.footer.FooterLinksWithTopic;
import com.epam.sap.developers.core.models.bean.FooterTopicBean;
import com.epam.sap.developers.core.models.bean.SimpleLinkBean;
import com.epam.sap.developers.core.models.bean.SocialMediaBean;

import java.util.List;
import java.util.Map;

public interface Footer {

    /*old variant*/
    Map<String, String> getFooterSocialMedia();

    Map<Integer, List<SimpleLink>> getFooterCopyright();

    List<FooterLinksWithTopic> getFooterLinksWithTopic();

    /*new variant*/
    List<SimpleLinkBean> getFooterCopyrightList();

    Map<Integer, List<SimpleLink>> getFooterCopyrightMap();

    Map<Integer, List<SimpleLinkBean>> getFooterCopyrightMapVersion2();

    List<FooterTopicBean> getFooterTopicBeanList();

    List<SocialMediaBean> getSocialMediaBeanList();

    String getFooterLinksStyle();

    boolean isEmpty();

}
