
public class MemoiazationAlgo {

	int[][] MemArray;
	public static int MemCounter=  0;
	
	public int CalculateLCS(String X1, String X2){
		MemArray = new int[X1.length()][X2.length()];
		
		for(int i=0;i<X1.length();i++){
			for(int j=0; j<X2.length();j++){
				MemArray[i][j]=-1;
			}
		}
		
		return MemoLCSLength(X1,X2,X1.length()-1,X2.length()-1);
		
	}
	
	/**
	 * 
	 * @param x
	 * @param y
	 * @param i
	 * @param j
	 * @return
	 */
	public  int MemoLCSLength(String x, String y,int i, int j){
		MemCounter +=1;
		if(i<0 || j<0){
			return 0;
		}
		if(MemArray[i][j] >= 0){
			return MemArray[i][j];
		}
		if(x.charAt(i) == y.charAt(j)){
			MemArray[i][j] = MemoLCSLength(x,y,i-1,j-1) + 1;
		}else{
			int c1 = MemoLCSLength(x,y,i-1,j);
			int c2 = MemoLCSLength(x,y,i,j-1);
			
			if(c1 >= c2){
				
				MemArray[i][j] = c1;
			}else{
				
				MemArray[i][j] = c2;
			}
			
		}
		return MemArray[i][j];
	}
}
