package model;

public class Userscore {
	String username;
	int score;
	
	public Userscore(String username,int score){
		this.username=username;
		this.score=score;
	}
	public int getScore() {
		return this.score;
	}
	
	public String getUsername() {
		return this.username;
	}
	
}
