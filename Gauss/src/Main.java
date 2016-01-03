

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.ojalgo.type.keyvalue.MapEntry;


    

public class Main {
        
    //public Object[] solution = new Object[]
    
    public static double[][] matrix =
        {{1,-1, 1, 1,-1, 2, 1, 1},
         {0, 2, 1, 1, 0, 1,-1, 2},
         {0, 0, 0, 1, 1, 2, 1, 0},
         {0, 0, 0, 0, 1, 0, 1, 1},
         {0, 0, 0, 0, 0, 0, 3, 3}};
    
    public static int columns = 7;
    public static int rows = 5;
    
    
    public static String[] paramString = {"m","n","p","r","s","t","u","v","w","z"};
    public static List<String> params = new ArrayList<String>(Arrays.asList(paramString));
    
    public static List<Map<String, Double>> rowsMaps = new ArrayList<Map<String, Double>>();
    
    public static Map<String, Map<String,Double>> result=new HashMap<String, Map<String,Double>>();
    
    
    /*
    public static List<Map<String, Double>> solution(){
        // back substitution
        int N;
        if(rows>columns) N=columns-1;
        else N=rows;
        Map<String, Double> sum = new HashMap<String, Double>();
        for (int i = N - 1; i >= 0; i--) {
            for (int j = i + 1; j < N; j++) {
                sum += matrix[i][j] * x.get(j);
            }
            x.get(i) = (matrix[i][columns-1] - sum) / matrix[i][i];
        }
        return x;
    }
    */
    public static Map<String,Double> multiplyByConst(Map<String,Double> map, double a){
        Double d = new Double(a);
        Map<String,Double> returnedMap = new HashMap<String,Double>();
        for(String s : map.keySet())
        {
        	returnedMap.put(s, map.get(s)*d);
        }
        return returnedMap;
    }
    
    public static Map<String,Double> divideByConst(Map<String,Double> map, double a){
        Double d = new Double(a);
        Map<String,Double> returnedMap = new HashMap<String,Double>();
        for(String s : map.keySet())
        {
        	returnedMap.put(s, map.get(s)/d);
        }
        return returnedMap;
    }
    
    //sum all maps at list
    public static Map<String, Double> add(List<Map<String, Double>> maplist) {
        Map<String, Double> result = new HashMap<String, Double>();
        for (Map<String, Double> map : maplist) {
            for (Map.Entry<String, Double> entry : map.entrySet()) {
                String key = entry.getKey();
                Double current = result.get(key);
                result.put(key, current == null ? entry.getValue() : entry.getValue() + current);
            }
        }
        return result;
    }
    
    
    public static void addToRowsMap(){
    	for(int i=rows-1;i>=0;i--){
            Map<String,Double> map= new HashMap<String,Double>();
            for(int j=0;j<columns;j++){
                StringBuilder s = new StringBuilder("x");
                s.append(j);
                Double d = new Double(matrix[i][j]);
                map.put(s.toString(),d);
            }
            map.put("n", matrix[i][columns]);
            rowsMaps.add(map);
        }
    }
    
    
    
    public static void main(String[] args)
    { 
    	
    	addToRowsMap();
    	
        Map<String,Double> map6= new HashMap<String,Double>();
        map6.put("n", 1.0);
        result.put("x6", map6);
        Map<String,Double> map5= new HashMap<String,Double>();
        map5.put("t", 1.0);
        result.put("x5", map5);
        Map<String,Double> map4= new HashMap<String,Double>();
        map4.put("n", 0.0);
        result.put("x4", map4);
        Map<String,Double> map3= new HashMap<String,Double>();
        map3.put("n", -1.0);
        map3.put("t", -2.0);
        result.put("x3", map3);
        Map<String,Double> map2= new HashMap<String,Double>();
        map2.put("u", 1.0);
        result.put("x2", map2);
        /*Map<String,Double> map1= new HashMap<String,Double>();
        map1.put("n", 2.0);
        map1.put("t", 0.5);
        map1.put("u", -0.5);
        result.put("x1", map1);
        Map<String,Double> map0= new HashMap<String,Double>();
        map0.put("n", 3.0);
        map0.put("t", 0.5);
        map0.put("u", -0.5);
        result.put("x0", map0);*/
        
        
        
        System.out.println("result \n" + result);
        Map<String,Double> mapOfCurrentRow= new HashMap<String,Double>();
        mapOfCurrentRow = rowsMaps.get(3);
        System.out.println("Map of Current row \n" + mapOfCurrentRow + "\n\n");
        
       
        List<Map<String, Double>> listOfMaps = new ArrayList<Map<String, Double>>();
        String key = null;
        Double constant = 0.0;
        for (Map.Entry<String, Map<String, Double>> mapsEntry : result.entrySet()) {
            key = mapsEntry.getKey();
            constant = mapOfCurrentRow.get(key);
	            Map<String,Double> multipliedRow = new HashMap<String,Double>();
	            multipliedRow = multiplyByConst(mapsEntry.getValue(), constant*(-1));
	            listOfMaps.add(multipliedRow);
	            mapOfCurrentRow.put(key, null);
        }
        System.out.println((listOfMaps));
        
        Map<String,Double> helper= new HashMap<String,Double>();
        
        for(Map.Entry<String,Double> map : mapOfCurrentRow.entrySet()){
        	String s = map.getKey();
        	Double d = map.getValue();
        	if(d!=null && d!=0)
        	helper.put(s, d);
        		
        }
        listOfMaps.add(helper);
        helper = add(listOfMaps);
        System.out.println(helper);
        helper = divideByConst(helper, 2);
        System.out.println(helper);
        int counter = 6;
        for(Map.Entry<String,Double> map : helper.entrySet()){
        	String s = map.getKey();
        	Double d = map.getValue();
        	if(s.startsWith("x")){
        		helper.put(params.get(0), helper.get(s));
        		helper.remove(s);
        		params.remove(0);
        	}
        	System.out.println(map);
        		
        }
    }


    public static void solve(Map<String, Double> row) {
        
    }
}

