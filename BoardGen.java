
import java.util.ArrayList;
import java.util.Random;

public class BoardGen{
	
	
	 static int sizeOfBoard=5;
	
	 //Array to store the tiles on the board
	 static String[][] listOfTiles=new String [sizeOfBoard][sizeOfBoard];
	
	//Array to store the frequencies of each type of tile 
	 public static int[] a=new int[]{15,4,3,2,1};
	

 enum TileType{ BLANK_TILE(14),DOUBLE_LETTER_TILE(4),TRIPPLE_LETTER_TILE(3),DOUBLE_WORD_TILE(2),TRIPPLE_WORD_TILE(1);
    
    private int frequency;
    
    private TileType(int frequency)
    {
    	this.frequency=frequency;
    }
    
    public int getFrequency()
     {
    	 return this.frequency;
     }
     
 }
 public static int calcMinimumDistance(int total)
 {
	 int[] dist=new int[5];
	 for(int i=0;i<a.length;i++)
	 {
		 dist[i]=a[i]-total;
	 }
	 
	 int smallest=dist[0];
	 
	 for(int j=0;j<dist.length;j++)
	 {
		 if(dist[j]<smallest)
			 smallest=dist[j];
	 }
	 //System.out.print(smallest);
	 if(smallest<0)
	 return (smallest*(-1));
	 else
		 return smallest;
	 
 }
 
    public static String getTile(int posx,int posy)
    {
    	
    	String val="16";
    	
    	//By default, the tile is blank
    	TileType t=TileType.BLANK_TILE;      
    	
    	//Sum of all the relative frequenciea
    	int total=0;
    	
    	//	System.out.println("In tileType...");
    		
    	for(TileType tile:TileType.values())
    			{
    				//System.out.println(tile.name());
    				total+=tile.getFrequency();
    			}
    			if(total>posx && total>posy)
    			{  
    				  int k=calcMinimumDistance(posy);
    				  val=Integer.toString(k);
    				  if(k==0)
    					  val="4";
    			}
    			
    	//System.out.println("The value has been returned "+val);
    	return val;
    }
    
    public static void generateBoard(int posx,int posy)
    {
    	//System.out.println("In generateBoard");
    	String s=getTile(posx, posy);
    	listOfTiles[posx][posy]=s;
    	}
   
    public static void show()
    {
    	System.out.println("In show...");
    	for(int i=0;i<listOfTiles.length;i++)
    	{
    		for(int j=0;j<listOfTiles.length;j++)
    		{
    		
    		   System.out.print(listOfTiles[i][j]+" " );	
    		}
    		System.out.println();
    	}
    }
    
    
    public static void main(String str[])
    {
    	Random r;
    	
    	for(int i=0;i<listOfTiles.length;i++)
		{
			for(int j=0;j<listOfTiles.length;j++)
			{
			r=new Random();
			int posx=r.nextInt(sizeOfBoard);
			int posy=r.nextInt(sizeOfBoard);
	    	if(listOfTiles[posx][posy]==null)
			generateBoard(posx, posy);}
		
			}
    			
		for(int i=0;i<listOfTiles.length;i++)
		{
			for(int j=0;j<listOfTiles.length;j++)
				if(listOfTiles[i][j]==null)
					listOfTiles[i][j]="16";
		}
		
		for(int i=0;i<listOfTiles.length;i++)
		{
			for(int j=0;j<listOfTiles.length;j++)
			{
				String s=listOfTiles[i][j];
				
				switch(Integer.parseInt(s))
				{
				case 16:
					listOfTiles[i][j]="BLANK_TILE";
					break;
				case 4:
					listOfTiles[i][j]="DOUBLE_LETTER_TILE";
					break;
				case 3:
					listOfTiles[i][j]="TRIPPLE_LETTER_TILE";
					break;
				case 2:
					listOfTiles[i][j]="DOUBLE_WORD_TILE";
					break;
				case 1:
					listOfTiles[i][j]="TRIPPLE_WORD_TILE";
					break;
				}
			}
				
			
		}
		
		
    	//System.out.print("The board is");
    	show();
    }
   
}
