package com.epam.rd.autocode.observer.git;

import java.util.*;

public class MyRepo implements Repository {
    List<WebHook> hooks;
    Map<String, List<Commit>> commits;

    public MyRepo() {
        hooks = new ArrayList<>();
        commits = new HashMap<>();
    }

    @Override
    public void addWebHook(WebHook webHook) {
        hooks.add(webHook);
    }

    @Override
    public Commit commit(String branch, String author, String[] changes) {
        Commit newCommit = new Commit(author, changes);
        if (hooks.size() > 0) {
            notify(new Event(Event.Type.COMMIT, branch, Collections.singletonList(newCommit)));
            if (commits.containsKey(branch)) {
                ArrayList<Commit> newList = (ArrayList<Commit>) commits.get(branch);
                newList.add(newCommit);
                commits.put(branch, newList);
            } else {
                ArrayList<Commit> values = new ArrayList<>();
                values.add(newCommit);
                commits.put(branch, values);
            }
            return newCommit;
        }
        return newCommit;
    }

    @Override
    public void merge(String sourceBranch, String targetBranch) {
        if (commits.get(targetBranch) == null) {
            commits.put(targetBranch, new ArrayList<>());
        }
        if (commits.get(sourceBranch) == null) {
            commits.put(sourceBranch, new ArrayList<>());
        }
        if (commits.get(sourceBranch).equals(commits.get(targetBranch))) {
            notify(new Event(Event.Type.MERGE, targetBranch, null));
        } else {
            List<Commit> difference = new ArrayList<>(commits.get(sourceBranch));
            difference.removeAll(commits.get(targetBranch));
            difference.forEach(e -> commits.get(targetBranch).add(e));
            notify(new Event(Event.Type.MERGE, targetBranch, difference));
        }
    }

    private void notify(Event event) {
        for (WebHook e : hooks) {
            if (e.type() == event.type() && e.branch().equals(event.branch())) {
                e.onEvent(event);
            }
        }
    }
}
