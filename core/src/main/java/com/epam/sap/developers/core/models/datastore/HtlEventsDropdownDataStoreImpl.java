package com.epam.sap.developers.core.models.datastore;

import com.adobe.granite.ui.components.ds.DataSource;
import com.adobe.granite.ui.components.ds.SimpleDataSource;
import com.adobe.granite.ui.components.ds.ValueMapResource;
import com.epam.sap.developers.core.models.HtlEventsDropdownDataStore;
import com.epam.sap.developers.core.models.bean.EventDropdownBean;
import org.apache.commons.collections.iterators.TransformIterator;
import org.apache.jackrabbit.oak.commons.PathUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceMetadata;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.api.wrappers.ValueMapDecorator;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Model(adaptables = SlingHttpServletRequest.class,
        adapters = HtlEventsDropdownDataStore.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class HtlEventsDropdownDataStoreImpl implements HtlEventsDropdownDataStore {

    @Inject
    private SlingHttpServletRequest request;

    @SlingObject
    private ResourceResolver resolver;

    @SlingObject
    private Resource currentResource;

    @Override
    @PostConstruct
    public void init() throws Exception {
        Resource dropdownResource = resolver.getResource(PATH_TO_DROPDOWN);
        EventDropdownBean dropdownBean = dropdownResource.adaptTo(EventDropdownBean.class);

        String dropdownFieldName = PathUtils.getName(currentResource.getPath());

        final Map<String, String> dropdownMap = getDropdownMap(dropdownFieldName, dropdownBean);

        DataSource ds = new SimpleDataSource(new TransformIterator(dropdownMap.keySet().iterator(), o -> {

            String dropdownText = (String) o;
            ValueMap vm = new ValueMapDecorator(new HashMap<>());
            vm.put("value", dropdownMap.get(dropdownText));
            vm.put("text", dropdownText);

            return new ValueMapResource(resolver, new ResourceMetadata(), "nt:unstructured", vm);
        }
        ));

        request.setAttribute(DataSource.class.getName(), ds);

    }

    private Map<String, String> getDropdownMap(String dropdownFieldName, EventDropdownBean dropdownBean) {
        if (dropdownFieldName.equals(EVENT_TOPIC_DROPDOWN)) {
            return convertEventMapToDropdownMap(dropdownBean.getEventTopics());
        }
        if (dropdownFieldName.equals(EVENT_TYPE_DROPDOWN)) {
            return convertEventMapToDropdownMap(dropdownBean.getEventTypes());
        } else {
            throw new IllegalArgumentException();
        }
    }

    private Map<String, String> convertEventMapToDropdownMap(Map<String, String> eventTypes) {
        return eventTypes.keySet()
                .stream()
                .collect(Collectors.toMap(
                        e -> e.trim(),
                        e -> createDropdownValue(e, eventTypes.get(e))
                ));
    }

    private String createDropdownValue(String topicTitle, String iconNumber) {
        return topicTitle.toLowerCase()
                .trim()
                .replaceAll("\\s+", "-")
                .concat("-" + iconNumber);
    }
}
