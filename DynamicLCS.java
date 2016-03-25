/**
 * 
 * @author mnaser
 *This class will only store the directions for the dynamic programming
 *array. if d = true then use the diagonal.
 *		 if d = false then go to the left
 *if the object in the array is null then go up.
 */
class pointers{
	public Boolean d = false ;
	
	pointers(){
		d = false;
	}
	
}

public class DynamicLCS {
	//b is a two dimensional array that stores the directions while 
	//calculating the original Matrix to count the LCS, it will be used
	//to reconstruct the common string.
	pointers[][] b;
	
	/**
	 * This function will be used to find one of the LCS between string X and String Y
	 * @param X
	 * @param Y
	 */
	public String FindLCS(String X,String Y){
		int m = X.length();
		int n = Y.length();
		b = new pointers[m][n];
		int[][] c = new int[m+1][n+1];
		
		for(int i = 0; i <= m;i++)
			c[i][0] = 0;
		for(int i=0; i<=n; i++)
			c[0][i]=0;
		
		for(int i =1;i <= m; i++){
			for(int j = 1;j <= n; j++){
				if(X.charAt(i-1) == Y.charAt(j-1)){
					c[i][j]=c[i - 1][ j-1]+1;
					b[i-1][j-1]= new pointers();
					b[i-1][j-1].d = true;
					
				}else if(c[i - 1][ j]>= c[i][j- 1]){
					c[i][j] = c[i - 1][ j];
					b[i-1][j-1] = new pointers();
					
				}else {
					c[i][j] = c[i][ j - 1];
					
					
				}
			
			}
			
		}
		
		
		return  printLCS(X,m-1,n-1);
	
	}
	
	/**
	 * This function will use the matrix b to reconstruct one of the LCS 
	 * between strings X and Y.
	 * @param X
	 * @param i: length of string X
	 * @param j: length of string Y
	 * @return
	 */
	public String printLCS(String X,int i, int j){
		String s="";
		while(i>=0 && j>=0){
			if(b[i][j] == null){
				j-=1;
			}else if(b[i][j].d){
				
				s = X.charAt(i) + s;
				i -=1;
				j-=1;
			}else{
				
				i-=1;
			}
		}
		
		return s;

	}
	
}
