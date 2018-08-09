package com.my.server.model;

import javax.persistence.*;

@Entity(name = "Word")
public class Word {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;
    @Column(name = "word")
    private String word;

    public Word(String word) {
        this.word = word;
    }

    public Word() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }
}
