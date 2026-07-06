package org.lievasoft.instructor.entity;

import java.util.List;

public class Locution {

    private String sentence;
    private List<String> generalDefinitions;
    private List<SentenceContext> sentenceContexts;

    public Locution(String sentence, List<String> generalDefinitions, List<SentenceContext> sentenceContexts) {
        this.sentence = sentence;
        this.generalDefinitions = generalDefinitions;
        this.sentenceContexts = sentenceContexts;
    }

    public String getSentence() {
        return sentence;
    }

    public List<String> getGeneralDefinitions() {
        return generalDefinitions;
    }

    public List<SentenceContext> getSentenceContexts() {
        return sentenceContexts;
    }
}
