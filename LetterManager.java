import java.util.ArrayList;



public class LetterManager {
	
	public ArrayList<LetterData> list=new ArrayList<LetterData>();
	
	public int get_Score_Of_Letter(char ch)   //character ch is in the word
	{
	     LetterData ob=new LetterData();
	     int score=ob.get_Score();
	     return score;
	}
	
	public void Update_Placed_Letter(char ch)
	{
		LetterData ob=new LetterData();
		ob.score-=2;
		ob.frequency-=1;
	}

}
