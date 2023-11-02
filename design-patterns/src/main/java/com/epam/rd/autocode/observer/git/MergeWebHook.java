package com.epam.rd.autocode.observer.git;

import java.util.ArrayList;
import java.util.List;

public class MergeWebHook implements WebHook {
    String branch;
    List<Event> events = new ArrayList<>();

    public MergeWebHook(String branch) {
        this.branch = branch;
    }

    @Override
    public String branch() {
        return this.branch;
    }

    @Override
    public Event.Type type() {
        return Event.Type.MERGE;
    }

    @Override
    public List<Event> caughtEvents() {
        return events;
    }

    @Override
    public void onEvent(Event event) {
        if (event.commits() != null) {
            events.add(event);
        }
    }
}
