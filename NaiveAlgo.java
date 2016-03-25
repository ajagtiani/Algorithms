
public class NaiveAlgo {

	public static int NaiveCounter = 0;
	/**
	 * Naive algorithm to calculate the length of the LCS between two strings
	 * @param x
	 * @param y
	 * @param i
	 * @param j
	 * @return
	 */
	public int NaiveLCSLength(String x, String y,int i, int j){
		NaiveCounter +=1;
		if(i<0 || j<0){
			return 0;
		}

		if(x.charAt(i) == y.charAt(j)){
			return NaiveLCSLength(x,y,i-1,j-1)+1;
		}else{
			int c1 = NaiveLCSLength(x,y,i-1,j);
			int c2 = NaiveLCSLength(x,y,i,j-1);
			
			if(c1 > c2){
				return c1;
			}else{
				return c2;
			}
			
		}
		
	}

	/**
	 * This function is a Naive way to find the value of LCS.
	 * @param x:string 1
	 * @param y: String 2
	 * @param i: length of string 1
	 * @param j: length of string 2
	 * @param n_LCS: passed empty when first called, it contains the current LCS  that will be returned at the end.
	 * @return
	 */
	public String NaiveLCS(String x, String y,int i, int j, String n_LCS){
		NaiveCounter += 1;
		if(i<0 || j<0){
			return "";
		}
		if(x.charAt(i) == y.charAt(j)){
			n_LCS = NaiveLCS(x,y,i-1,j-1,n_LCS) + x.charAt(i) + n_LCS;
		}else{
			String c1 = NaiveLCS(x,y,i-1,j,n_LCS);
			String c2 = NaiveLCS(x,y,i,j-1,n_LCS);
			
			if(c1.length() > c2.length()){
				n_LCS =  c1 + n_LCS;
			}else{
				n_LCS =  c2 + n_LCS;
			}
			
		}
		return n_LCS;
	}
}
