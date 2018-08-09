package com.my.server.model;

import javax.persistence.*;
import java.util.List;

@Entity(name = "Text")
public class Text {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "textId", updatable = false, nullable = false)
    private Long textId;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Word> words;
    @Column(name = "text")
    private String text;

    public Long getTextId() {
        return textId;
    }

    public void setTextId(Long textId) {
        this.textId = textId;
    }

    public List<Word> getWords() {
        return words;
    }

    public void setWords(List<Word> words) {
        this.words = words;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
