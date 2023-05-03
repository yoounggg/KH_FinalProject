package org.zerock.myapp.controller;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@RequestMapping("/crawl") // base uri
@Controller
public class CrawlController {
	
	private Document doc = null;
	
	Connection conn = Jsoup.connect("https://www.kamis.or.kr/customer/main/main.do");
	
	@GetMapping("/")
	public String crawlpage(){
		try {
			Document doc = conn.get();
			
			Elements getElements = doc.getElementsByTag(".retailArea");
			
			for(Element e : getElements) {
				log.info(e);
				log.info(e.text());
			}
			
			return "/crawlpage";
		} catch(Exception e) {
			throw new RuntimeException(e.toString());
		}
	}

}
