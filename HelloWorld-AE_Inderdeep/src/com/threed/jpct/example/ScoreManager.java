package com.threed.jpct.example;
import com.threed.jpct.example.LetterManager;
public class ScoreManager {
	public static float total=0;
	
	public float get_Score_Of_Word(String word)
	{
		
		char str[]=word.toCharArray();
		for(int i=0; str[i]!='\0';i++)
		{
			float score= LetterManager.get_Score_Of_Letter(str[i]);
			total=total+score;
		}
		return total;
	}

}
