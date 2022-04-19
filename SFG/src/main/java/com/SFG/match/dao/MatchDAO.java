package com.SFG.match.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.SFG.match.model.MatchSchedule;

@Repository
public interface MatchDAO {
	
	public MatchSchedule selectMatchScheduleByMonthAndDay(
			@Param("month") String month
			,@Param("day") int day);
	
	public List<MatchSchedule> selectMatchScheduleByMonth(String Smonth);
	
	public void insertMatchSchedule(
			@Param("yyyymmdd") String yyyymmdd
			,@Param("homeTeamId") int homeTeamId
			,@Param("awayTeamId") int awayTeamId
			,@Param("homeScore") int homeScore
			,@Param("awayScore") int awayScore
			,@Param("result") String result
			,@Param("time") String time
			);
	
	public void updateMatchSchedule(
			@Param("rowId") int rowId
			,@Param("yyyymmdd") String yyyymmdd
			,@Param("homeTeamId") int homeTeamId
			,@Param("awayTeamId") int awayTeamId
			,@Param("homeScore") int homeScore
			,@Param("awayScore") int awayScore
			,@Param("result") String result
			,@Param("time") String time
			);
	
	public void deleteMatchScheduleById(int id);
}
