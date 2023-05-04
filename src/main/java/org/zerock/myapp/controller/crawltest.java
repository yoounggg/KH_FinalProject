//package org.zerock.myapp.controller;
//
//import java.io.IOException;
//import java.util.List;
//
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import org.jsoup.nodes.Element;
//import org.jsoup.select.Elements;
//import org.zerock.myapp.domain.CrawlDTO;
//
//import lombok.extern.log4j.Log4j2;
//
//@Log4j2
//public class crawltest { // 크롤링 테스트 클래스
//	public static void main(String[] args) throws IOException {
		

				
//				String url = "https://www.kamis.or.kr/customer/price/agricultureRetail/catalogue.do";
//				Document doc = Jsoup.connect(url).get();
				
				//오늘 시세만 가져와서 가격 매일 업데이트 되도록 해볼가
//				Elements contents = doc.select("table.wtable3 tbody tr");
//				log.info("boot: "+ contents);
				
//				for(Element content : contents) {
//					Elements tdContents = content.select("td");
//					log.info("getRetailDatas(): " + contents);
		
//					CrawlDTO dto = new CrawlDTO();
//					
//					dto.setRetailTable(tdContents);
//					log.info(dto.toString());
//					dto.setProductName(content.select("td.cell_tit1").text()); // 이름 
//					dto.setProductType(content.select("td.cell_tit2").text()); // 타입
//					dto.setTodayPrice(Integer.parseInt(tdContents.get(3).text())); // 오늘
//					dto.setLastweekPrice(Integer.parseInt(tdContents.get(5).text())); // 지난주 
//					
//					log.info(dto.toString());
//				}
				
				
				
//				--------------------------------------------------------------
//				Elements tables = doc.select("table.wtable3 tr");
//				Elements retails = doc.select("table.wtable3 tr td");
				
//				for(int j = 0; j< tables.size(); j++) {
//					
//					Element table = tables.get(j);
////					log.info(table);
//					
//					
//					for (Element row : table.select("tr")) {
//					    Elements tds = row.select("td:nth-child(4), td:nth-child(5)");
//					    for (Element td : tds) {
//					        log.info(td);
//					    }
//					}
//					
////					for (int i = 3; i < 5 ; i++) {
////						Element retail = table.get(i);
////						
////						log.info(retails);
////					} // inner for
//				} // outer for
	

		
//	} // main

//} // end class

//package org.zerock.myapp.controller;
//
//import java.io.IOException;
//
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import org.jsoup.select.Elements;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import lombok.NoArgsConstructor;
//import lombok.extern.log4j.Log4j2;
//
//@Log4j2
//@NoArgsConstructor
//
//@RequestMapping("/price") // base uri
//@Controller
//public class CrawlRetailController { // 크롤링하기!
//	
//	
//	@GetMapping("/retail")
//	public String crawlretailpage(Model model) throws IOException{ // 소매가격
//		
//		String url = "https://www.kamis.or.kr/customer/price/agricultureRetail/catalogue.do";
//		Document doc = Jsoup.connect(url).get();
//		Elements contents = doc.getElementsByAttributeValue("id", "itemTable_1"); // 식량
//		log.info(contents);
//		
//		model.addAttribute("retailInfo", contents);
//		
//		return "crawlretail";
//		try {	
//			String url = "https://www.kamis.or.kr/customer/price/agricultureRetail/catalogue.do";
//			Document doc = Jsoup.connect(url).get();
//			List<CrawlDTO> list = new ArrayList<CrawlDTO>();
//			
//			//오늘 시세만 가져와서 가격 매일 업데이트 되도록 해볼가
//			Elements tables = doc.select("table.wtable3 tr");
//			Elements retails = doc.select("table.wtable3 tr td");
//			
//			for(int j = 0; j< tables.size(); j++) {
//				
//				Element table = tables.get(j);
////				log.info(table);
//							
//				for (Element row : table.select("tr")) {
//				    Elements tds = row.select("td:nth-child(4), td:nth-child(5)");
//				    for (Element td : tds) {
////				        log.info(td);
//				        String realtd = td.text();
//				        log.info(realtd);
//				        
//				        CrawlDTO dto = new CrawlDTO();
//				        dto.setTodayPrice(realtd);
//				        list.add(dto);
//				        log.info(list);
//				        return list;
//				    } // inner for
//				} // inner for
//			} // for
//				
////			return "crawlpage";
//				
//		} catch(Exception e) {
//			throw new RuntimeException(e.toString());
//		} // try-catch	
//		return null;
				
//				---------------------------------------------------------------------				
//			model.addAttribute("retail", retail.html());
				
//			for(Element e : retail) {
//				log.info(e); // 태그 포함
//				log.info(e.text()); // 태그 미포함 value만 가져오기
//			} // for
			
//			model.addAttribute("retailinfo", );		
			
//			---------------------------------------------------------------------
			//소매
//			Document doc = conn.get();
//			Elements retail1 = doc.getElementsByAttributeValue("id", "tab_tbl0101"); // 식량
//			Elements retail2 = doc.getElementsByAttributeValue("id", "tab_tbl0201"); // 채소
//			Elements retail3 = doc.getElementsByAttributeValue("id", "tab_tbl0301"); // 특용
//			Elements retail4 = doc.getElementsByAttributeValue("id", "tab_tbl0401"); // 과일
//			model.addAttribute("retailInfo1", retail1.html());
//			model.addAttribute("retailInfo2", retail2.html());
//			model.addAttribute("retailInfo3", retail3.html());
//			model.addAttribute("retailInfo4", retail4.html());
			
//			---------------------------------------------------------------------

			//방법2
//			String url = "https://www.kamis.or.kr/customer/main/main.do";
//			Document doc = Jsoup.connect(url).get();
//			Elements retail = doc.getElementsByAttributeValue("class",  "tbl_comn fl_r w_half tblArea retailArea");
//			model.addAttribute("retailInfo", retail.html());
		

//	} // crawlpage

//}

