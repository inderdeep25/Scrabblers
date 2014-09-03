import java.util.ArrayList;



public class LetterManager {
	
	public ArrayList<LetterData> list=new ArrayList<LetterData>();
	
	public float get_Score_Of_Letter(char ch)   //character ch is in the word
	{
	     LetterData ob=new LetterData();
	     float score=ob.get_Score();
	     return score;
	}
	
	public void Update_Placed_Letter(char ch)
	{
		LetterData ob=new LetterData();
		ob.score*=1.5;
		ob.frequency*=0.4;
	}

}
