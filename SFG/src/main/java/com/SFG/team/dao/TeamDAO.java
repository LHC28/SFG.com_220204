package com.SFG.team.dao;

import org.springframework.stereotype.Repository;

import com.SFG.team.model.Team;

@Repository
public interface TeamDAO {

	public Team selectTeamByTeamId(int teamId);
}
