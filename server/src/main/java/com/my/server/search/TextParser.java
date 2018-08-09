package com.my.server.search;

import com.my.server.model.Text;
import com.my.server.model.Word;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TextParser {
    private static final String[] DEL_WORDS = new String[]{"a", "am", "the"};

    public Text parse(String text) {
        List<String> strings = Arrays.asList(text.trim().split(" "));
        List<Word> words = strings.stream().filter(TextParser::isEqual)
                .map(Word::new).collect(Collectors.toList());

        Text rez = new Text();
        rez.setText(text);
        rez.setWords(words);
        return rez;
    }

    private static boolean isEqual(String word) {
        for (String del_word : DEL_WORDS) {
            if (word.equals(del_word)) {
                return false;
            }
        }
        return true;
    }
}
