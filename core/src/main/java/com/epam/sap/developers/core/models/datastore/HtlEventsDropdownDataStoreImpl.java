package com.epam.sap.developers.core.models.datastore;

import com.adobe.granite.ui.components.ds.DataSource;
import com.adobe.granite.ui.components.ds.SimpleDataSource;
import com.adobe.granite.ui.components.ds.ValueMapResource;
import com.day.cq.wcm.api.Page;
import com.epam.sap.developers.core.models.HtlEventsDropdownDataStore;
import com.epam.sap.developers.core.models.bean.EventDropdownBean;
import com.epam.sap.developers.core.services.EventsService;
import com.epam.sap.developers.core.services.impl.EventsServiceImpl;
import com.epam.sap.developers.core.utils.ServiceUtils;
import org.apache.commons.collections.iterators.TransformIterator;
import org.apache.jackrabbit.oak.commons.PathUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.*;
import org.apache.sling.api.wrappers.ValueMapDecorator;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static com.epam.sap.developers.core.services.impl.EventsServiceImpl.*;

@Model(adaptables = SlingHttpServletRequest.class,
        adapters = HtlEventsDropdownDataStore.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class HtlEventsDropdownDataStoreImpl implements HtlEventsDropdownDataStore {

    private static final String EVENT_TOPIC_DROPDOWN = "eventTopic";
    private static final String EVENT_TYPE_DROPDOWN = "eventType";

    //private static final String DROPDOWN_MULTIFIELD_NODE = ServiceUtils.getCrxPath("dropdownMultifield");

    @SlingObject
    private SlingHttpServletRequest request;

    @SlingObject
    private ResourceResolver resolver;

    @SlingObject
    private Resource currentResource;

    @Inject
    private EventsService eventsService;

    @PostConstruct
    @Override
    public void init() throws LoginException {

        String pathToComponent = request.getRequestPathInfo().getSuffix();

        String pathToDropdown = PathUtils.getAncestorPath(pathToComponent, 2).concat(DROPDOWN_MULTIFIELD_NODE);

        EventDropdownBean dropdownBean = eventsService.getEventDropdownBean(pathToDropdown);

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
            throw new IllegalArgumentException("No matches with select option");
        }
    }

    private Map<String, String> convertEventMapToDropdownMap(Map<String, String> eventTypes) {
        return eventTypes.keySet()
                .stream()
                .collect(Collectors.toMap(
                        String::trim,
                        e -> e
                ));
    }
}
