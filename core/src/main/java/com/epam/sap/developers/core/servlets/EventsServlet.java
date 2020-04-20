package com.epam.sap.developers.core.servlets;

import com.epam.sap.developers.core.models.Event;
import com.epam.sap.developers.core.services.EventsService;
import com.epam.sap.developers.core.services.impl.EventsServiceImpl.EventsWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Component(service = {Servlet.class})
@SlingServletResourceTypes(
        resourceTypes = "developers/components/custom/event",
        methods = HttpConstants.METHOD_GET,
        extensions = "json",
        selectors = "data")
public class EventsServlet extends SlingSafeMethodsServlet {

    private static final Logger logger = LoggerFactory.getLogger(EventsServlet.class);

    private static final String VIEW_BY_PROP = "by";
    private static final String TITLE_PROP = "title";
    private static final String COLUMN_NUMBER_PROP = "column";

    private static final String VIEW_BY_TOPIC_VAL = "topic";
    private static final String VIEW_BY_TYPE_VAL = "type";

    @Reference
    private EventsService eventsService;

    @Override
    protected void doGet(SlingHttpServletRequest req, SlingHttpServletResponse resp) throws ServletException, IOException {
        List<Event> eventsColumn = new ArrayList<>();
        List<EventsWrapper> eventsBy = new ArrayList<>();

        String jsonResponse = "";

        String paramViewBy = req.getParameter(VIEW_BY_PROP);
        String paramTitle = req.getParameter(TITLE_PROP);
        String paramColNum = req.getParameter(COLUMN_NUMBER_PROP);


        try {
            if (paramViewBy != null && paramTitle == null) {
                eventsBy = getEventsBy(paramViewBy);
            } else if (paramViewBy != null && paramColNum != null) {
                eventsColumn = getEventsColumn(paramViewBy, paramTitle, paramColNum);
            }

            if (!eventsBy.isEmpty()) {
                jsonResponse = new ObjectMapper().writeValueAsString(eventsBy);
            } else if (!eventsColumn.isEmpty()) {
                jsonResponse = new ObjectMapper().writeValueAsString(eventsColumn);
            } else {
                jsonResponse = null;
            }
        } catch (LoginException e) {
            logger.error(e.getMessage());
        }

        if (jsonResponse == null) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } else {
            PrintWriter out = resp.getWriter();
            resp.setContentType("application/json");
            resp.setHeader("Access-Control-Allow-Origin", "*");
            resp.setCharacterEncoding("UTF-8");
            out.print(jsonResponse);
            out.flush();
        }

    }

    private List<Event> getEventsColumn(String paramViewBy, String paramTitle, String paramColNum) throws LoginException {
        if (paramViewBy.equals(VIEW_BY_TOPIC_VAL)) {
            return eventsService.getEventsColumnForTopic(paramTitle, Integer.parseInt(paramColNum));
        } else if (paramViewBy.equals(VIEW_BY_TYPE_VAL)) {
            return eventsService.getEventsColumnForType(paramTitle, Integer.parseInt(paramColNum));
        }
        return Collections.emptyList();

    }

    private List<EventsWrapper> getEventsBy(String paramViewBy) throws LoginException {
        if (paramViewBy.equals(VIEW_BY_TOPIC_VAL)) {
            return eventsService.getEventsWrapperByTopic();
        } else if (paramViewBy.equals(VIEW_BY_TYPE_VAL)) {
            return eventsService.getEventsWrapperByType();
        }
        return Collections.emptyList();
    }


}