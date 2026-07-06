package org.lievasoft.instructor.entity;

import java.util.List;

public class SentenceContext {

    private String context;
    private List<String> examples;

    public SentenceContext(String context, List<String> examples) {
        this.context = context;
        this.examples = examples;
    }

    public String getContext() {
        return context;
    }

    public List<String> getExamples() {
        return examples;
    }
}
