import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.regex.Pattern;



public class Main {
	private static double[][]  matrix =
        { { 6, -3, 2, 4, 5, 5},
          { 0, 0, 1, 2, 4, 8},
          { 0, 0, 2, 4, 8, 12},
          { 0, 0, 0, 6, 1, 9}
};
	
	static int rows = 4;
	static int columns = 5+1;
	static int rankLeft = 2;
	static int rank = 2;
	static int freeValuesCounter = columns - 1 - rankLeft;
	
	static String[] result = new String[columns-1];
	
	private static void printMatrix(String string, double[][] doubles){
		System.out.println();
		System.out.println(string + "\n");
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				System.out.print(doubles[i][j] +"\t");
			}
			System.out.println();
		}
	}
	
	public boolean isZeroRow(int r){
		boolean isOk=false;
		int counter = 0;
		for (int i = 0; i < columns; i++) {
			if (matrix[r][i]==0) 
				counter+=1;
		}
		
		if (counter==matrix.length) 
			isOk=true;
		else isOk=false;
		
		return isOk;
	}
	
	//ok
	public static boolean isStringFraction(String string){
		String[] substrings = string.split(Pattern.quote("."));
		System.out.println("-----------" + string);
		
		int s1=0,s2;
		if(substrings.length>2 || string==null)
			return false;
		else{
			try{
				for(String s:substrings){
					Integer.parseInt(s);
				}
				s1=Integer.parseInt(substrings[0]);
				s1=Integer.parseInt(substrings[1]);
				return true;
			}
			catch (NumberFormatException e){
				return false;
			}
		}
	}
	
	public static String solutionResult(String s){
		StringBuilder sb = new StringBuilder();
		sb.append(s + " ");
		System.out.print(sb.toString());
		return sb.toString();
	}
	
	public static String[] stringSolution(double[] doubles){
		String[] arr = new String[doubles.length];
		int iterator=0;
		for(int i = 0; i < doubles.length; i++){
			String s = String.valueOf(doubles[iterator]);
			arr[iterator]= s;
				solutionResult(s);
			if(s!="NaN" || s!="Infinity") result[i]=s;
			iterator+=1;
		}
		return arr;
	}
	
	public static double[] solution(){
		// back substitution
		int N;
		if(rows>columns) N=columns-1;
		else N=rows;
        double[] x = new double[N];
        for (int i = N - 1; i >= 0; i--) {
            double sum = 0.0;
            for (int j = i + 1; j < N; j++) {
                sum += matrix[i][j] * x[j];
            }
            x[i] = (matrix[i][columns-1] - sum) / matrix[i][i];
        }
        stringSolution(x);
        return x;
	}
	
	
	private static int frobenyResult(){
		 int frob=2;
	        
	        if (rankLeft!=rank) frob=-1;
	        else if (rankLeft==rank && rank<columns-1) frob=1;
	        else if (rankLeft==rank && rank==columns-1)frob=0;
	        
	     return frob;
	}
	
    private static void frobeny(){
        String eq = "";
        String statement = "";

        switch (frobenyResult()) {
            case -1:
                eq= "h(A)" + "\u2260" + "h'(A)";
                statement = " Sustava nema riesenie";
                break;
            case 1:
                eq= "h(A) = h'(A) < n \t\t" + rank + "<" + columns;
                statement = " Sustava ma nekonecne vela rieseni";
                break;
            case 0:
                eq= "h(A) = h'(A) = n = " + columns;
                statement = " Sustava ma prave jedno riesenie";
                break;
            default: statement = " Nevalidny stav";
                break;
        }
        System.out.println(statement);
    }
	
    private static void linearCombination(){
    	int values = matrix.length-rank;
    }
    
    private static String[] result(){
    	
    	int n = columns-1;
    	Object[] diag = new Object[n];
    	
    	Double zeroObj = 0.0;
    	String[] bigParams = {"m","n","p","r","s","t","u","v","w","z"};
    	String[] smallParams = {"t","s","u","v","w"};
    	
    	String[] params = null;
    	
    	if (n<6) 
			params = smallParams;
    	else
    		params = bigParams;
    	
    	int paramCounter = 0;
    	
			for (int j = n-1; j >=0 ; j--) {
				if(j<rows)
				  diag[j]=matrix[j][j];
				else
					diag[j]=null;
				//unvalid diag. value
				if (diag[j]==null || zeroObj == (double) diag[j]) {
					diag[j] = params[paramCounter];
					result[j]= params[paramCounter];
					paramCounter+=1;
					int helper = j+1;
					System.out.println(" Nech x" + helper + " = " + diag[j]);
				}
				//valid diag. value -> print row -> try to solve
				else{
					prinRow(j);
				}
			}
			return params;
    }
    
    private static String sign(double d){
    	String s="";
    	if (d<0) s=" ";
    	else s="+";
    	return s;
    }
    
    private static void printValue(double d, int index){
    	index+=1;
    	if (d!=0)
    			System.out.print(sign(d) + d + "x" + index + " ");
    }
    
    private static void prinRow(int r){
    	for (int i = 0; i < columns-1; i++) {
    		printValue(matrix[r][i],i);
		}
    	System.out.print("= " + matrix[r][columns-1]);
    	System.out.println();
    }
    
    private static void printResult(){
    	for(int i=0; i<result.length; i++){
    		if(result[i]==null) System.out.println("null");
    		else System.out.println(result[i].toString());
		}
    }
    
	public static void main(String[] args) {
		
		//printMatrix("Matrix", matrix);
		//triangleForm(matrix);
		printMatrix("Matrix", matrix);
		//frobeny();
		
		
		//test();
		//stringSolution(solution());
		//result();
		//printResult();
		prinRow(2);
	}
	
}
