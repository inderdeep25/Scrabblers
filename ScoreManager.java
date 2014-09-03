
public class ScoreManager {
	public static int total=0;
	
	public int get_Score_Of_Word(String word)
	{
		
		char str[]=word.toCharArray();
		for(int i=0; str[i]!='\0';i++)
		{
			int score=LetterManager.get_Score_Of_Letter(str[i]);
			total=total+score;
		}
		return total;
	}

}
