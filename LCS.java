import java.util.Random;
import java.io.*;

/**
 * @authors:
 * 
 * Aditya Jagtiani
 * Muhannad Darnasser
 *
 */
public class LCS {

	/**
	 * @param args
	 */
	
	private static final long MEGABYTE = 1024L * 1024L;

	  public static long bytesToMegabytes(long bytes) {
	    return bytes / MEGABYTE;
	  }
	
	
	
	public static void main(String[] args) {
		
		
		// Get the Java runtime
	    Runtime runtime = Runtime.getRuntime();
	    // Run the garbage collector
	    FileWriter fstream = null;
	    
		try {
			fstream = new FileWriter("out.xls");
		    BufferedWriter out = new BufferedWriter(fstream);
		    out.write("X1 Length \t X2 length \t X1 \t X2 \t Naive Total Mem \t Naive Time \t naive Length \t Naive Rec Counter");
		    out.write("\t Memoiazation Total Mem \t Memoiazation Time \t Memoiazation Length \t Mem Rec Counter");
		    out.write("\t Dynamic Total Mem \t Dynamic Time \t Dynamic Length \t Dynamic LCS");
		    out.write("\t AlgoC Total Mem \t AlgoC Time \t AlgoC Length \t AlgoC LCS");
		    out.write("\t AlgoC No sub Total Mem \t AlgoC No sub Time \t AlgoC No sub Length \t AlgoC No sub LCS");
		    for(int sLength = 10; sLength <= 25; sLength ++ ){
		    	
		    	for(int numTimes = 0; numTimes < 10; numTimes ++){
		    		out.write("\n");
				    String x1 = GenerateRandomSeq(sLength);
					String x2= GenerateRandomSeq(sLength);
				    out.write( x1.length() +"\t " + x2.length());
				    out.write("\t" + x1);
				    out.write("\t" + x2);
					//Naive Algorithm
					if(x1.length()<15){
		//				out.write("\t Naive Algorithm");
						runtime.gc();
						long memoryBefore = runtime.totalMemory() - runtime.freeMemory();
						//out.write("\n Used memory is bytes Before: " + memoryBefore);
						long startTime = System.nanoTime();
						NaiveAlgo L = new NaiveAlgo();
						int s = L.NaiveLCSLength(x1, x2, x1.length()-1, x2.length()-1);
				
					    long elapsedTime = System.nanoTime() - startTime;
						long memoryAfter = runtime.totalMemory() - runtime.freeMemory();
						//out.write("\n Used memory is bytes After: " + memoryAfter);
						out.write("\t" + (memoryAfter - memoryBefore));
						out.write("\t" + elapsedTime);
						out.write("\t" +s);
						out.write("\t" +L.NaiveCounter);
						L.NaiveCounter = 0;
		
						
					}else{
						out.write("\t\t\t\t");
					}
					
					//Memoiazation
					{
						//out.write("\n \n Memoization Algorithm");
						runtime.gc();
						long memoryBefore = runtime.totalMemory() - runtime.freeMemory();
						//out.write("\n Used memory is bytes Before: " + memoryBefore);
						long startTime = System.nanoTime();
						
						
						MemoiazationAlgo L = new MemoiazationAlgo();
						int s = L.CalculateLCS(x1, x2);
				
					    long elapsedTime = System.nanoTime() - startTime;
						long memoryAfter = runtime.totalMemory() - runtime.freeMemory();
						out.write("\t" + (memoryAfter - memoryBefore));
						out.write("\t" + elapsedTime);
						out.write("\t" +s);
						out.write("\t" +L.MemCounter);
						L.MemCounter = 0;
						
					}
					
					//Dynamic LCS
					{
						//out.write("\n \n Dynamic LCS");
						runtime.gc();
						long memoryBefore = runtime.totalMemory() - runtime.freeMemory();
						//out.write("\n Used memory is bytes Before: " + memoryBefore);
						long startTime = System.nanoTime();
						
						DynamicLCS L2 = new DynamicLCS();
						String s = L2.FindLCS(x1, x2);
				
					    long elapsedTime = System.nanoTime() - startTime;
						long memoryAfter = runtime.totalMemory() - runtime.freeMemory();
						out.write("\t" + (memoryAfter - memoryBefore));
						out.write("\t" + elapsedTime);
						out.write("\t" +s.length());
						out.write("\t" +s);
						
					}
					
					//AlgoC
					{
						//out.write("\n \n Naive Algo C");
						runtime.gc();
						long memoryBefore = runtime.totalMemory() - runtime.freeMemory();
						//out.write("\n Used memory is bytes Before: " + memoryBefore);
						long startTime = System.nanoTime();
						
						AlgoC L = new AlgoC();
						String s = L.FindLCSAlgoC(x1.length(), x2.length(), x1, x2);
				
					    long elapsedTime = System.nanoTime() - startTime;
						long memoryAfter = runtime.totalMemory() - runtime.freeMemory();
						out.write("\t" + (memoryAfter - memoryBefore));
						out.write("\t" + elapsedTime);
						out.write("\t" +s.length());
						out.write("\t" +s);
						
					}
					//AlgoC No Substring
					{
						//out.write("\n \n AlgoC No SubSTring");
						runtime.gc();
						long memoryBefore = runtime.totalMemory() - runtime.freeMemory();
						//out.write("\n Used memory is bytes Before: " + memoryBefore);
						long startTime = System.nanoTime();
						
						
						AlgoCNoSub L3 = new AlgoCNoSub();
						String s = L3.FindLCS(x1, x2);
				
					    long elapsedTime = System.nanoTime() - startTime;
						long memoryAfter = runtime.totalMemory() - runtime.freeMemory();
						out.write("\t" + (memoryAfter - memoryBefore));
						out.write("\t" + elapsedTime);
						out.write("\t" +s.length());
						out.write("\t" +s);
					}
		    	}
		    	out.write("\n\n");
		    }
		
		
			out.close();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		System.out.println("End Of Test");
		
		/*AlgoC L = new AlgoC();
		AlgoCNoSub L3 = new AlgoCNoSub();
		long startTimeNano = System.nanoTime( );
		String s = L3.FindLCS(x1, x2);
		
		long time1 = System.nanoTime() - startTimeNano;
		System.out.println("Algo C No Sub: " +s);
		System.out.println ("Memo Time For (S1,S2) = " + (double)time1 / 1000000.0 + " msec");
		System.exit(0);
		
		DynamicLCS L2 = new DynamicLCS();
		System.out.println("Algo C: " +L.FindLCSAlgoC(x1.length(), x2.length(), x1, x2));
		
		int len2 = NaiveLCSLength(x1,x2,x1.length()-1,x2.length()-1);
		L2.FindLCS(x1, x2);
		
		 System.out.println (" LCS length = " + len2);
		 System.out.println("S1: " + x1);
			System.out.println("S2: " + x2);
		System.exit(0);
		
		
		
		System.out.println("S1: " + x1);
		System.out.println("S2: " + x2);
		

		int sLen = MemoLCSLength(x1,x2,x1.length()-1,x2.length()-1);
		
		System.out.println ("Memo Time For (S1,S2) = " + (double)time1 / 1000000.0 + " msec, LCS length = " + sLen);
		 startTimeNano = System.nanoTime( );

		int len = NaiveLCSLength(x1,x2,x1.length()-1,x2.length()-1);
		
		 time1 = System.nanoTime() - startTimeNano;
		 System.out.println ("Naive Time For (S1,S2) = " +(double)time1 / 1000000.0 + " msec, LCS length = " + len);*/
	}
	
	/**
	 * A generator to generate strings to be used in the testing.
	 * @param length: the length of the needed string.
	 * @return
	 */
	public static String GenerateRandomSeq(int length){
		Random a = new Random();
		String s="";
		char[] alpha = {'A','C','G','T','1','2'};
		for(int i =0;i<length;i++){
			s += alpha[a.nextInt(alpha.length)];
		}
		return s;
	}

}
