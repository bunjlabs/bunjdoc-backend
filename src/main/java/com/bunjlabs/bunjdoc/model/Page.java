package com.bunjlabs.bunjdoc.model;

import java.sql.Timestamp;
import java.util.List;

public class Page {
    private Timestamp createdAt;

    private String name;

    private PageVersion editing;
    private List<PageVersion> versions;
}
