package com.threed.jpct.example;

import java.util.ArrayList;



public class LetterManager {
	
	public static ArrayList<LetterData> list=new ArrayList<LetterData>();
	
	public static float get_Score_Of_Letter(char ch)   //character ch is in the word
	{
	     LetterData ob=new LetterData('a', 5, 2);
	     float score=ob.get_score();
	     return score;
	}
	
	public void Update_Placed_Letter(char ch)
	{
		LetterData ob=new LetterData('a', 5, 2);
		ob.score*=1.5;
		ob.frequency*=0.4;
	}

}
