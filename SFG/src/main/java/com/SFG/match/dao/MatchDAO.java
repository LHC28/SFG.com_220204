package com.SFG.match.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.SFG.match.model.MatchSchedule;

@Repository
public interface MatchDAO {
	
	public List<MatchSchedule> scheduleByMonth(String month);
}
