package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Userscore {
	List<Integer> scores=new ArrayList<>();
	String username;
	
	public Userscore(String username){
		this.username=username;
	}
	
	public void addscore(int score) {
		scores.add(score);
	}
	
	public void setScores(List<Integer> scores) {
		this.scores = scores;
	}
	public List<Integer> getScores() {
		return scores;
	}
	
	public int getMax() {
		return Collections.max(scores);
	}
	
	public String getUsername() {
		return username;
	}
	
}
