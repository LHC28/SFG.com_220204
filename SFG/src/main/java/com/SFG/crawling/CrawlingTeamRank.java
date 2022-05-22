package com.SFG.crawling;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.SFG.crawling.model.TeamRank;

public class CrawlingTeamRank {

	public static List<TeamRank> getTeamRank() {
        final String inflearnUrl = "https://sports.news.naver.com/wbaseball/record/index";
        Connection conn = Jsoup.connect(inflearnUrl);

        // 11개씩 잘라서 저장해야 한다. 0~11 12~22 23~33
        List<TeamRank> teamRanks = new ArrayList<>();
        
        try {
            Document document = conn.get();
//            Element imageUrlElements = document.getElementById("westDivisionTeamRecordList_table");
//            Elements imageUrlElements = document.getElementsByTag("tr");
//            System.out.println(imageUrlElements);
            
            Elements dataElements = document.select("#westDivisionTeamRecordList_table .inner");
            
            for(int i=0; i<5; i++) {
            	// 값을 넣을 객체
            	TeamRank teamRank = new TeamRank();
            	teamRank.setRank(i+1);
            	for(int j=i*11; j<(i*11)+11; j++) {
            		String data = dataElements.get(j).text();
            		if(j%11==1) { // 팀명
            			teamRank.setTeamName(data);
            		}else if(j%11==2) { // 경기수
            			int games = Integer.valueOf(data);
            			teamRank.setGames(games);
            		}else if(j%11==3) { // 승
            			int wins = Integer.valueOf(data);
            			teamRank.setWins(wins);
            		}else if(j%11==4) { // 패
            			int loses = Integer.valueOf(data);
            			teamRank.setLoses(loses);
            		}else if(j%11==5) { // 승률
            			double winsRate = Double.valueOf(data);
            			teamRank.setWinsRate(winsRate);
            		}else if(j%11==6) { // 게임차
            			double gamesBehind = 0;
//            			1위인 경우 0이 아닌 -로 적혀있기 때문
            			if(!data.equals("-")) {
            				gamesBehind = Double.valueOf(data);
            			}
            			teamRank.setGamesBehind(gamesBehind);
            		}
            	}
            	teamRanks.add(teamRank);
            }
//            출력 테스트
            for(int i=0; i<dataElements.size(); i++) {
            	final String data = dataElements.get(i).text();
            	System.out.println(data);
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        return teamRanks;
    }
}
