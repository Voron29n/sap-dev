package com.epam.sap.developers.core.models;

import javax.jcr.RepositoryException;
import java.util.List;

public interface YouTube {

    String getYoutubeVideoSrc();

    String getTitle();

    String getDescription();

    List<String> getItemList() throws RepositoryException;

    boolean isOnlyVideo();

    boolean isEmpty();
}
