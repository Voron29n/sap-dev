//package com.epam.sap.developers.core.models.impl;
//
//import com.epam.sap.developers.core.entities.SimpleLink;
//import com.epam.sap.developers.core.models.Intro;
//import com.epam.sap.developers.core.services.YouTubeService;
//import com.epam.sap.developers.core.utils.ModelUtils;
//import com.epam.sap.developers.core.utils.SimpleLinkUtils;
//import org.apache.sling.api.SlingHttpServletRequest;
//import org.apache.sling.api.resource.Resource;
//import org.apache.sling.models.annotations.Default;
//import org.apache.sling.models.annotations.DefaultInjectionStrategy;
//import org.apache.sling.models.annotations.Model;
//import org.apache.sling.models.annotations.injectorspecific.SlingObject;
//import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
//
//import javax.annotation.PostConstruct;
//import javax.inject.Inject;
//import java.util.List;
//
//@Model(adaptables = SlingHttpServletRequest.class,
//        adapters = {Intro.class},
//        resourceType = {IntroMinImpl.RESOURCE_TYPE},
//        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
//public class IntroMinImpl implements Intro {
//
//    protected static final String RESOURCE_TYPE = "developers/components/custom/intro-min";
//
//    @Inject
//    private YouTubeService youTubeService;
//
//    @SlingObject
//    private Resource currentResource;
//
//    @ValueMapValue
//    private String title;
//
//    @ValueMapValue
//    private String description;
//
//    @ValueMapValue
//    private String buttonText;
//
//    @ValueMapValue
//    @Default(values = "#")
//    private String buttonHref;
//
//    @ValueMapValue
//    private boolean isButtonNeed;
//
//    @ValueMapValue
//    private String fileName;
//
//    @ValueMapValue
//    private String fileReference;
//
//    @PostConstruct
//    public void init(){
//        System.out.println(fileName);
//    }
//
//    @Override
//    public String getIntroTitle() {
//        return title;
//    }
//
//    @Override
//    public String getIntroDescription() {
//        return description;
//    }
//
//    @Override
//    public String getImagePath() {
//        return ModelUtils.getImageSrc(fileName, fileReference, currentResource);
//    }
//
//    @Override
//    public String getMaxHeight() {
//        return null;
//    }
//
//    @Override
//    public SimpleLink getButtonData() {
//        return SimpleLinkUtils.getSimpleLinkForButtonFromPathField(buttonText, buttonHref);
//    }
//
//    @Override
//    public List<String> getItemList() {
//        return youTubeService.getItemsOfMultiefieldProperty(currentResource);
//    }
//
//    @Override
//    public boolean isEmpty() {
//        return title == null && description == null && getImagePath() == null && (!isButtonNeed || (isButtonNeed && buttonText == null));
//    }
//
//    @Override
//    public boolean isButtonNeed() {
//        return isButtonNeed;
//    }
//}
