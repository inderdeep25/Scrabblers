package com.threed.jpct.example;

public class LetterData {

	public char id;
	public float score;
	public int frequency;
	
	public LetterData(char id,int score,int frequency)
	{
		this.id=id;
		this.frequency=frequency;
		this.score=score;
	}
	public char get_id() 
	{
		return this.id;
	}
	public float get_score()
	{
		return this.score;
	}
	public int get_frequency()
	{
		 return this.frequency;
	}
}
  
