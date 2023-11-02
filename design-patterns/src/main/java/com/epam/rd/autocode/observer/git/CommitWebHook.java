package com.epam.rd.autocode.observer.git;

import java.util.ArrayList;
import java.util.List;

public class CommitWebHook implements WebHook {
    String branch;
    List<Event> events = new ArrayList<>();

    public CommitWebHook(String branch) {
        this.branch = branch;
    }

    @Override
    public String branch() {
        return this.branch;
    }

    @Override
    public Event.Type type() {
        return Event.Type.COMMIT;
    }

    @Override
    public List<Event> caughtEvents() {
        return events;
    }

    @Override
    public void onEvent(Event event) {
        events.add(event);
    }
}
