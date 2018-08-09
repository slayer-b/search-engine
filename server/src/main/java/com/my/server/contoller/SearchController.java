package com.my.server.contoller;

import com.my.server.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class SearchController {

	@Autowired
	private SearchService service;

	@PostMapping("/search")
	public String findByWords(@RequestParam("words") String words) {
		return service.search(words);
	}

	@PostMapping("/addText")
	public void addText(@RequestBody String text) {
	    service.addText(text);
	}

}
