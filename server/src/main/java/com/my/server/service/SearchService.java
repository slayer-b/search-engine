package com.my.server.service;

import com.my.server.model.Text;
import com.my.server.model.Word;
import com.my.server.repository.SearchRepository;
import com.my.server.search.TextParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchService {

    @Autowired
    private SearchRepository repository;
    @Autowired
    private TextParser textParser;

    public String search(String words) {
        Text parsed = textParser.parse(words);
        List<String> byWordIdIn = repository.findByWordIn(
                parsed.getWords().stream().map(Word::getWord).collect(Collectors.toList()));
        //search for best one
        return byWordIdIn.isEmpty()? "" : byWordIdIn.get(0);
    }

    public void addText(String text) {
        Text entity = textParser.parse(text);
        repository.save(entity);
    }
}
