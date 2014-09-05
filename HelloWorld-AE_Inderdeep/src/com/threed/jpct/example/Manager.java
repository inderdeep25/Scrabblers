package com.threed.jpct.example;

import java.util.Random;
class Manager{
Random r;
static int m;

public int[][] createBoard(int m){
	double dw=0.125*(m*m-1),dl=0.0833*(m*m-1),tw=0.0833*(m*m-1),tl=0.0416*(m*m-1),bl=(m*m-1)-dw-dl-tw-tl;
	System.out.println(dw+" "+dl+" "+tw+" "+tl+" "+bl);
	int[][] a=new int[19][19];
	r=new Random();
	int i=0,j=0;
		for(i=0;i<11;i++)
			for(j=0;j<11;j++)
				a[i][j]=0;
					//--------------------------Main Functionality Starts here------------------------------------
		for(i=0;i<m;i++){				//row loop
			for(j=0;j<m;j++){			//column loop
				if(i==m/2 && j==m/2){
								//to find the centre point
					//System.out.print("C");
					a[i][j]=10;
					}
				else if(i!=m-1 && j!=m-1 && i!=0 && j!=0){

								//check tiles other than boundary

					if(r.nextInt(5)==1 && dw>0 && a[i+1][j]==0 && a[i+1][j+1]==0 && a[i+1][j-1]==0 && a[i-1][j-1]==0 && a[i-1][j]==0 && a[i-1][j+1]==0 && a[i][j-1]==0 && a[i][j+1]==0){
						//System.out.print("dw");a[i][j]=1;
						a[i][j]=1;
						dw--;
						}
					else if(r.nextInt(5)==2 && tl>0 && a[i+1][j]==0 && a[i+1][j+1]==0 && a[i+1][j-1]==0 && a[i-1][j-1]==0 && a[i-1][j]==0 && a[i-1][j+1]==0 && a[i][j-1]==0 && a[i][j+1]==0){
						//System.out.print("tl");
						a[i][j]=2;
						tl--;
						}
					else if(r.nextInt(5)==3 && tw>0 && a[i+1][j]==0 && a[i+1][j+1]==0 && a[i+1][j-1]==0 && a[i-1][j-1]==0 && a[i-1][j]==0 && a[i-1][j+1]==0 && a[i][j-1]==0 && a[i][j+1]==0){
						//System.out.print("tw");
						a[i][j]=3;
						tw--;}

					else if(r.nextInt(5)==4 && dl>0 && a[i+1][j]==0 && a[i+1][j+1]==0 && a[i+1][j-1]==0 && a[i-1][j-1]==0 && a[i-1][j]==0 && a[i-1][j+1]==0 && a[i][j-1]==0 && a[i][j+1]==0){
						//System.out.print("dl");
						a[i][j]=4;
						dl--;
						}
					else {
						//System.out.print("[]");
						a[i][j]=0;
						}
				}

				else if(i==m-1 && j==0){


								//check tile of lower left corner.


					if(r.nextInt(5)==1 && dw>0 && a[m-2][j+1]==0 && a[m-1][j+1]==0 && a[m-2][j]==0){
						//System.out.print("dw");
						a[i][j]=1;
						dw--;}
					else if(r.nextInt(5)==2 && tl>0 && a[m-2][j+1]==0 && a[m-1][j+1]==0 && a[m-2][j]==0){
						//System.out.print("tl");
						a[i][j]=2;
						tl--;
						}
					else if(r.nextInt(5)==3 && tw>0 && a[m-2][j+1]==0 && a[m-1][j+1]==0 && a[m-2][j]==0){
						//System.out.print("tw");
						a[i][j]=4;
						tw--;
						}

					else if(r.nextInt(5)==4 && dl>0 && a[m-2][j+1]==0 && a[m-1][j+1]==0 && a[m-2][j]==0){
						//System.out.print("dl");
						a[i][j]=4;
						dl--;
						}
					else{
						//System.out.print("[]");
						a[i][j]=0;
						}
				}
				else if(i==0 && j==m-1){


								//check tile of upper right corner.


					if(r.nextInt(5)==1 && dw>0 && a[1][m-1]==0 && a[0][m-2]==0 && a[1][m-2]==0){
						a[i][j]=1;	
						//System.out.print("dw");
						dw--;
						}
					else if(r.nextInt(5)==2 && tl>0 && a[1][m-1]==0 && a[0][m-2]==0 && a[1][m-2]==0){
						//System.out.print("tl");
						a[i][j]=2;
						tl--;
						}
					else if(r.nextInt(5)==3 && tw>0 && a[1][m-1]==0 && a[0][m-2]==0 && a[1][m-2]==0){
						//System.out.print("tw");
						a[i][j]=3;
						tw--;	
						}

					else if(r.nextInt(5)==4 && dl>0 && a[1][m-1]==0 && a[0][m-2]==0 && a[1][m-2]==0){
						//System.out.print("dl");
						a[i][j]=4;
						dl--;
						}
					else {
						//System.out.print("[]");
						a[i][j]=0;
						}
				}
				else if(i==0 && j==0){


								//check tile on upper left corner


					if(r.nextInt(5)==1 && dw>0 && a[1][0]==0 && a[1][1]==0 && a[0][1]==0){
						a[i][j]=1;
						System.out.print("dw");
						dw--;
						}
					else if(r.nextInt(5)==2 && tl>0 && a[1][0]==0 && a[1][1]==0 && a[0][1]==0){
						//System.out.print("tl");
						a[i][j]=2;
						tl--;
						}
					else if(r.nextInt(5)==3 && tw>0 && a[1][0]==0 && a[1][1]==0 && a[0][1]==0){
						//System.out.print("tw");
						a[i][j]=3;
						tw--;
						}

					else if(r.nextInt(5)==4 && dl>0 && a[1][0]==0 && a[1][1]==0 && a[0][1]==0){
						//System.out.print("dl");
						a[i][j]=4;
						dl--;
						}
					else {
					//	System.out.print("[]");
						a[i][j]=0;
						}
				}
				else if(i==m-1 && j==m-1){

								//check tile on lower right corner


					if(r.nextInt(5)==1 && dw>0 && a[m-2][m-2]==0 && a[m-2][m-1]==0 && a[m-1][m-2]==0){
						//System.out.print("dw");
						a[i][j]=1;
						dw--;}
					else if(r.nextInt(5)==2 && tl>0 && a[m-2][m-2]==0 && a[m-2][m-1]==0 && a[m-1][m-2]==0){
						//System.out.print("tl");
						a[i][j]=2;
						tl--;
						}
					else if(r.nextInt(5)==3 && tw>0 && a[m-2][m-2]==0 && a[m-2][m-1]==0 && a[m-1][m-2]==0){
						//System.out.print("tw");
						a[i][j]=3;
						tw--;	
						}

					else if(r.nextInt(5)==4 && dl>0 && a[m-2][m-2]==0 && a[m-2][m-1]==0 && a[m-1][m-2]==0){
						//System.out.print("dl");
						a[i][j]=4;
						dl--;
						}
					else {
						//System.out.print("[]");
						a[i][j]=0;
						}
				}
				else if(i==0 && j!=0 && j!=m-1){

								//check tiles on the upper boundary but not corner


					if(r.nextInt(5)==1 && dw>0 && a[i][j-1]==0 && a[i][j+1]==0 && a[i+1][j-1]==0 && a[i+1][j]==0 && a[i+1][j+1]==0){
						//System.out.print("dw");
						a[i][j]=1;dw--;}
					else if(r.nextInt(5)==2 && tl>0 && a[i][j-1]==0 && a[i][j+1]==0 && a[i+1][j-1]==0 && a[i+1][j]==0 && a[i+1][j+1]==0){
						//System.out.print("tl");
						a[i][j]=2;
						tl--;
						}
					else if(r.nextInt(5)==3 && tw>0 && a[i][j-1]==0 && a[i][j+1]==0 && a[i+1][j-1]==0 && a[i+1][j]==0 && a[i+1][j+1]==0){
						//System.out.print("tw");
						a[i][j]=3;
						tw--;
						}

					else if(r.nextInt(5)==4 && dl>0 && a[i][j-1]==0 && a[i][j+1]==0 && a[i+1][j-1]==0 && a[i+1][j]==0 && a[i+1][j+1]==0){
						//System.out.print("dl");
						a[i][j]=4;
						dl--;
						}
					else {
						//System.out.print("[]");
						a[i][j]=0;
						}
				}
				else if(i==m-1 && j!=0 && j!=m-1){


								//check tiles on the lower boundary but not corner


					if(r.nextInt(5)==1 && dw>0 && a[i][j-1]==0 && a[i][j+1]==0 && a[i-1][j-1]==0 && a[i-1][j]==0 && a[i-1][j+1]==0){
						a[i][j]=1;
						//System.out.print("dw");
						dw--;
						}
					else if(r.nextInt(5)==2 && tl>0 && a[i][j-1]==0 && a[i][j+1]==0 && a[i-1][j-1]==0 && a[i-1][j]==0 && a[i-1][j+1]==0){
						//System.out.print("tl");
						a[i][j]=2;
						tl--;
						}
					else if(r.nextInt(5)==3 && tw>0 && a[i][j-1]==0 && a[i][j+1]==0 && a[i-1][j-1]==0 && a[i-1][j]==0 && a[i-1][j+1]==0){
						//System.out.print("tw");
						a[i][j]=3;
						tw--;
						}
					else if(r.nextInt(5)==4 && dl>0 && a[i][j-1]==0 && a[i][j+1]==0 && a[i-1][j-1]==0 && a[i-1][j]==0 && a[i-1][j+1]==0){
						//System.out.print("dl");
						a[i][j]=4;
						dl--;
						}
					else {
						//System.out.print("[]");
						a[i][j]=0;
						}
				}
				else if(j==0 && i!=0 && i!=m-1){


								//check tiles on the left boundary but not corner


					if(r.nextInt(5)==1 && dw>0 && a[i-1][j]==0 && a[i+1][j]==0 && a[i+1][j+1]==0 && a[i][j+1]==0 && a[i-1][j+1]==0){
						a[i][j]=1;
						//System.out.print("dw");
						dw--;
						}
					else if(r.nextInt(5)==2 && tl>0 && a[i-1][j]==0 && a[i+1][j]==0 && a[i+1][j+1]==0 && a[i][j+1]==0 && a[i-1][j+1]==0){
						//System.out.print("tl");
						a[i][j]=2;
						tl--;
						}
					else if(r.nextInt(5)==3 && tw>0 && a[i-1][j]==0 && a[i+1][j]==0 && a[i+1][j+1]==0 && a[i][j+1]==0 && a[i-1][j+1]==0){
						//System.out.print("tw");
						a[i][j]=3;
						tw--;
						}

					else if(r.nextInt(5)==4 && dl>0 && a[i-1][j]==0 && a[i+1][j]==0 && a[i+1][j+1]==0 && a[i][j+1]==0 && a[i-1][j+1]==0){
						//System.out.print("dl");
						a[i][j]=4;
						dl--;
						}		
					else {
						//System.out.print("[]");
						a[i][j]=0;
						}
				}
				else if(j==m-1 && i!=0 && i!=m-1){


								//check tiles on the right boundary but not corner



					if(r.nextInt(5)==1 && dw>0 && a[i-1][j]==0 && a[i+1][j]==0 && a[i+1][j-1]==0 && a[i][j-1]==0 && a[i-1][j-1]==0){
						a[i][j]=1;	
						//System.out.print("dw");
						dw--;
						}
					else if(r.nextInt(5)==2 && tl>0 && a[i-1][j]==0 && a[i+1][j]==0 && a[i+1][j-1]==0 && a[i][j-1]==0 && a[i-1][j-1]==0){
						//System.out.print("tl");
						a[i][j]=2;
						tl--;
						}
					else if(r.nextInt(5)==3 && tw>0 && a[i-1][j]==0 && a[i+1][j]==0 && a[i+1][j-1]==0 && a[i][j-1]==0 && a[i-1][j-1]==0){
						//System.out.print("tw");
						a[i][j]=3;
						tw--;}

					else if(r.nextInt(5)==4 && dl>0 && a[i-1][j]==0 && a[i+1][j]==0 && a[i+1][j-1]==0 && a[i][j-1]==0 && a[i-1][j-1]==0){
						//System.out.print("dl");
						a[i][j]=4;
						dl--;	
						}
					else {
						//System.out.print("[]");
						a[i][j]=0;
						}
				}
/*     NOT NEEDED BUT LET IT REMAIN
				else if(r.nextInt(5)==0 && bl>0){
					a[i][j]=0;
					System.out.print("[]");
					bl--;
					}
				else if(r.nextInt(5)==1 && dw>0){
					a[i][j]=1;	
					System.out.print("dw");
					dw--;	
					}
				else if(r.nextInt(5)==2 && tl>0){
					a[i][j]=2;
					System.out.print("tl");
					tl--;
					}
				else if(r.nextInt(5)==3 && tw>0){
					a[i][j]=3;
					System.out.print("tw");
					tw--;	
					}

				else if(r.nextInt(5)==4 && dl>0){
					a[i][j]=4;
					System.out.print("dl");
					dl--;
					}

				else
					{
					a[i][j]=0;
					System.out.print("[]");
					}
*/
					}
				//System.out.println("");
			}
	
	return a;
		}

}