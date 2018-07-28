package com.chb.title.model;

public class Lexicon {
    private Integer id;

    private String content;

    private Integer lexiconNum;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getLexiconNum() {
        return lexiconNum;
    }

    public void setLexiconNum(Integer lexiconNum) {
        this.lexiconNum = lexiconNum;
    }
}