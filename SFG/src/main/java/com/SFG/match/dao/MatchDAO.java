package com.SFG.match.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.SFG.match.model.MatchSchedule;

@Repository
public interface MatchDAO {
	
	public MatchSchedule selectMatchScheduleByMonthAndDay(
			@Param("month") String month
			,@Param("day") int day);
}
