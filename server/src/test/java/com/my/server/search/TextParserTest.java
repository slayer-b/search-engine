package com.my.server.search;

import com.my.server.model.Text;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TextParserTest {

    private TextParser textParser;

    @Before
    public void before() {
        textParser = new TextParser();
    }

    @Test
    public void parseEmpty() {
        Text parsed = textParser.parse("");

        assertFalse(parsed.getWords().isEmpty());
        assertEquals("", parsed.getWords().get(0).getWord());
        assertEquals("", parsed.getText());
    }

    @Test
    public void parseDelWords() {
        Text parsed = textParser.parse("Hello the man");

        assertFalse(parsed.getWords().isEmpty());
        assertEquals("Hello", parsed.getWords().get(0).getWord());
        assertEquals("man", parsed.getWords().get(1).getWord());
        assertEquals("Hello the man", parsed.getText());
    }

    @Test
    public void parseWords() {
        Text parsed = textParser.parse("Hello man");

        assertFalse(parsed.getWords().isEmpty());
        assertEquals("Hello", parsed.getWords().get(0).getWord());
        assertEquals("man", parsed.getWords().get(1).getWord());
        assertEquals("Hello man", parsed.getText());
    }

}