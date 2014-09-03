public class LetterData {

	public int id;
	public float score;
	public int frequency;
	
	public LetterData(){
	
	}
	
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
	public float get_score()
	{
		return this.score;
	}
	public int get_frequency()
	{
		 return this.frequency;
	}
}
  
