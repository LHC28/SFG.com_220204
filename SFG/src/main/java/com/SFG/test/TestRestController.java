package com.SFG.test;

import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestRestController {

	@RequestMapping("/test/test")
	public void test() {
        final String inflearnUrl = "https://sports.news.naver.com/wbaseball/record/index";
        Connection conn = Jsoup.connect(inflearnUrl);

        try {
            Document document = conn.get();
            Element imageUrlElements = document.getElementById("westDivisionTeamRecordList_table");
//            Elements imageUrlElements = document.getElementsByTag("tr");
//            System.out.println(imageUrlElements);
            
            Elements dataElements = document.select("#westDivisionTeamRecordList_table .inner");
            
            for(int i=0; i<dataElements.size(); i++) {
            	final String data = dataElements.get(i).text();
            	System.out.println(data);
            }
            
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
}
