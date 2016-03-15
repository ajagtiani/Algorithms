import java.util.Arrays;


public class AlgoC {
	
	/**
	 * FindLCSAlgoC is the Hirschberg Algorithm used to find the Longest Common sequence
	 * between two strings, it is a Quadratic-time-linear-Space algorithm.
	 * This algorithm uses the Quadratic-time-linear-Space algorithm (AlgoB) to find LCS
	 * length between two string and then decides where to split the current strings to 
	 * apply the function again on a smaller problem until the strings becomes trivial to solve. 
	 * @param m:Length of A string
	 * @param n:Length of B string
	 * @param A: First String
	 * @param B: Second String
	 * @return
	 */
	public String FindLCSAlgoC(int m, int n, String A, String B){
//		System.out.println(m + "    " + A);
//		System.out.println(n+ "    " + B);
		
		/*
		 * if one of the strings is empty then return nothing, else if one of the strings' length is 1
		 * then just search if the other string contains that character or not, if yes then return that
		 * character otherwise return nothing.
		 */
		if(n==0 || m==0){
			return "";
		}else if(m==1){
			if(B.contains(A)){
				return A;
			}else
				return "";
			
		}else if(n==1){
			if(A.contains(B)){
				return B;
			}else
				return "";
		}
		
		int i = (int)Math.floor(m/2);
		
		/*
		 * applying AlgoB and Reverse AlgoB to the substrings to decide where to
		 * find the last row in the dynamic programming array in order to find the
		 * best k to decide where to split the strings for the sub problems.
		 */
		int[] L1 = AlgoB(i,n,A.substring(0, i),B);
		int[] L2 = AlgoBRev(m-i,n,A.substring(i),B);
		
//		System.out.println("A0i: " + A.substring(0, i));
//		System.out.println("Aim: " + A.substring(i));
		
//		System.out.println("L1: " + Arrays.toString(L1));
//		System.out.println("L2: " + Arrays.toString(L2));
		
		//Zero column in L1 with n column in L2
		int M = L2[n];
		int K = 0;
		int Mtemp = -1;
		
		//columns between 1 and n
		for(int j=1;j<n;j++){
			Mtemp = L1[j]+L2[n-j];
			if(Mtemp > M){
				M= Mtemp;
				K = j;
			}
			
		}
		/*
		 * Since K is the smallest index for the largest sum between the two arrays
		 * then it will always be the first index when we find the largest M, that is why
		 * we are changing K at the same time when we changed M.
		 */
		
		//column n From L1 with column 0 in L2
		if(L1[n] > M){
			M = L1[n];
			K = n;
		}
		
		/*
		 * after finding the split index, we apply the recursive algorithm on the smallers 
		 * strings from A divided by i and from B divided by K.
		 */
		
		String C1 = FindLCSAlgoC(i,K,A.substring(0, i),B.substring(0, K));
		String C2 = FindLCSAlgoC(m-i,n-K,A.substring(i),B.substring(K));
//		System.out.println("K: " + K);
		return C1 + C2;
		
//		System.out.println("K: " + K);
//		
//		System.out.println("i: " + i +", " + A.substring(0, i) + " actural Length " + A.substring(0, i).length());
//		System.out.println("K: " + K +", " + B.substring(0, K) + " actural Length " + B.substring(0, K).length());
//		
//		System.out.println("m-i: " + (m-i) +", " + A.substring(i) + " actural Length " + A.substring(i).length());
//		System.out.println("n-K: " + (n-K) +", " + B.substring(K) + " actural Length " + B.substring(K).length());
//		return "";
	}

	/**
	 * This Function will find the length of the LCS between A and B,
	 * and it will return the last row of the dynamic programming array.
	 * the length will be in the last cell of the array.
	 * @param m
	 * @param n
	 * @param A
	 * @param B
	 * @return
	 */
	public int[] AlgoB(int m,int n, String A, String B){
		
		int[][] k = new int[2][n+1];
		//Initialize the second row to zeros.
		for(int i=0; i<=n;i++){
			k[1][i]=0;
		}
		
		for(int i=1;i<=m;i++){
			//copy the second row into the first row.
			for(int j=0; j<=n;j++){
				k[0][j]=k[1][j];
			}
			
			//Calculate the rows of the matrix
			for(int j=1; j<=n;j++){
				if(A.charAt(i-1)==B.charAt(j-1)){
					k[1][j]=k[0][j-1]+1;
				}else{
					k[1][j]=Math.max(k[1][j-1], k[0][j]) ;
				}
			}
			
			
		}
		//return the second row.
		return k[1];
		
	}
	
/**
 * This function will find the last row of the dynamic programming array
 * between the reversed strings A and B. it takes A and B without being reversed
 * and it will start creating the array from the end of the two strings.
 * @param m
 * @param n
 * @param A
 * @param B
 * @return
 */
public int[] AlgoBRev(int m,int n, String A, String B){
		
		int[][] k = new int[2][n+1];
		for(int i=0; i<=n;i++){
			k[1][i]=0;
		}
		
		for(int i=m-1;i>=0;i--){
			for(int j=0; j<=n;j++){
				k[0][j]=k[1][j];
			}
			
			for(int j=n-1; j>=0;j--){
				if(A.charAt(i)==B.charAt(j)){
					k[1][n-j]=k[0][n-(j+1)]+1;
				}else{
					k[1][n-j]=Math.max(k[1][n-(j+1)], k[0][n-j]) ;
				}
			}
			
			
		}
		
		return k[1];
		
	}

}
