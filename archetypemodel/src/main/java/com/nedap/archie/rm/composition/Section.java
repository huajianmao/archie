package com.nedap.archie.rm.composition;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pieter.bos on 04/11/15.
 */
public class Section extends ContentItem {

    private List<ContentItem> items = new ArrayList<>();

    public List<ContentItem> getItems() {
        return items;
    }

    public void setItems(List<ContentItem> items) {
        this.items = items;
    }

    public void addItem(ContentItem item) {
        this.items.add(item);
    }
}
