package org.zerock.myapp.controller;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@RequestMapping("/price") // base uri
@Controller
public class CrawlWholeController { // 크롤링하기!
	
	
	@GetMapping("/wholesale")
	public String crawlretailpage(Model model) throws IOException{ // 농산물 도매 시세 가져오기!
		
		String url = "https://www.kamis.or.kr/customer/price/wholesale/catalogue.do";
		
		Document doc = Jsoup.connect(url).get();
		
		Elements contents = doc.getElementsByAttributeValue("id", "itemTable_1");
		log.info(contents);
		
		model.addAttribute("wholeSaleInfo", contents);
		
		return "crawl/crawlwholesale";
		
	} // crawlwholesale

} // end class
