
public class LetterData {

	public int id;
	public int score;
	public int frequency;
	
	
	
	public LetterData(int id,int score,int frequency)
	{
		this.id=id;
		this.frequency=frequency;
		this.score=score;
	}
	public int get_id()
	{
		return this.id;
	}
	public int get_score()
	{
		return this.score;
	}
	public int get_frequency()
	{
		 return this.frequency;
	}
}
  
