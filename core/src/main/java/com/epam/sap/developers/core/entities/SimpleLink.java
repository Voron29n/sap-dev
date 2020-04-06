package com.epam.sap.developers.core.entities;

import com.day.cq.wcm.api.Page;

import java.util.Objects;

public class SimpleLink {

    private String linkTitle;

    private String linkURL;

    private String cssClass;

    public SimpleLink(String linkTitle, String linkURL) {
        this.linkTitle = linkTitle;
        this.linkURL = linkURL;
    }

    public SimpleLink(String linkTitle, String linkURL, String cssClass) {
        this.linkTitle = linkTitle;
        this.linkURL = linkURL;
        this.cssClass = cssClass;
    }

    public String getLinkTitle() {
        return linkTitle;
    }

    public String getLinkURL() {
        return linkURL;
    }

    public String getCssClass() {
        return cssClass;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SimpleLink that = (SimpleLink) o;
        return Objects.equals(getLinkTitle(), that.getLinkTitle()) &&
                Objects.equals(getLinkURL(), that.getLinkURL()) &&
                Objects.equals(getCssClass(), that.getCssClass());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLinkTitle(), getLinkURL(), getCssClass());
    }
}
