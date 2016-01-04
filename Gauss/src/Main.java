
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NavigableMap;
import java.util.Set;
import java.util.TreeMap;

public class Main {

    // public Object[] solution = new Object[]

    public static double[][] matrix = { { 1, -1, 1, 1, -1, 2, 1, 1 }, { 0, 2, 1, 1, 0, 1, -1, 2 },
            { 0, 0, 0, 1, 1, 2, 1, 0 }, { 0, 0, 0, 0, 1, 0, 1, 1 }, { 0, 0, 0, 0, 0, 0, 3, 3 } };

    public static int columns = 7;
    public static int rows = 5;

    public static String[] paramString = { "m", "n", "p", "r", "s", "t", "u", "v", "w", "z" };
    public static List<String> params = new ArrayList<String>(Arrays.asList(paramString));

    public static List<Map<String, Double>> rowsMaps = new ArrayList<Map<String, Double>>();

    public static Map<String, Map<String, Double>> result = new TreeMap<String, Map<String, Double>>();

    
    public static Map<String, Double> multiplyByConst(Map<String, Double> map, double a) {
        Double d = new Double(a);
        Map<String, Double> returnedMap = new TreeMap<String, Double>();
        for (String s : map.keySet()) {
            returnedMap.put(s, map.get(s) * d);
        }
        return returnedMap;
    }

    public static Map<String, Double> divideByConst(Map<String, Double> map, double a) {
        Double d = new Double(a);
        Map<String, Double> returnedMap = new TreeMap<String, Double>();
        for (String s : map.keySet()) {
            returnedMap.put(s, map.get(s) / d);
        }
        return returnedMap;
    }

    // sum all maps at list
    public static Map<String, Double> add(List<Map<String, Double>> maplist) {
        Map<String, Double> result = new TreeMap<String, Double>();
        for (Map<String, Double> map : maplist) {
            for (Map.Entry<String, Double> entry : map.entrySet()) {
                String key = entry.getKey();
                Double current = result.get(key);
                result.put(key, current == null ? entry.getValue() : entry.getValue() + current);
            }
        }
        return result;
    }

    public static void addToRowsMap() {
        for (int i = rows - 1; i >= 0; i--) {
            Map<String, Double> map = new HashMap<String, Double>();
            for (int j = 0; j < columns; j++) {
                StringBuilder s = new StringBuilder("x");
                s.append(j);
                Double d = new Double(matrix[i][j]);
                if(d!=0)
                map.put(s.toString(), d);
            }
            map.put("", matrix[i][columns]);
            rowsMaps.add(map);
        }
    }

    public static void main(String[] args) {

        addToRowsMap();

          for(int row = 0; row<rowsMaps.size();row++){
        
        System.out.println("RESULT  \n" + result);
        
        
     // START OF SUBSTITUTION
        
        Map<String, Double> mapOfCurrentRow = rowsMaps.get(row);

        List<Map<String, Double>> listOfMapsAtRow = new ArrayList<Map<String, Double>>();
        Map<String, Double> tempMap = new TreeMap<String, Double>();

        System.out.println(result);
        System.out.println(mapOfCurrentRow);

        for (int i = 0; i < columns; i++) {
            String s = "x" + i;
            if (result.get(s) != null) {
                Map<String, Double> subs = substituteMaps(result, mapOfCurrentRow, s);
                listOfMapsAtRow.add(subs);
                removeFromMapByKey(mapOfCurrentRow, s);
            }
        }
        listOfMapsAtRow.add(mapOfCurrentRow);
        tempMap = add(listOfMapsAtRow);
        
        System.out.println(tempMap);
        
      // END OF SUBSTITUTION


        //SORT BECAUSE OF ITERATING AT MAP
        tempMap=sortByKeys(tempMap);
        
        System.out.println(tempMap);
        
        //PARAMETRIZATION
        parametrize(tempMap);
        
        System.out.println(tempMap);
        
        //DIVIDE ALL ROW BY VALUE OF PARAMETER
        tempMap = divideByConst(tempMap, findConstantOfParameter(tempMap));
        
        System.out.println(tempMap);
        String key = "x"+findIndexOfParameter(tempMap);
        
        removeParameter(tempMap);
        
        System.out.println(tempMap);
        
        result.put(key, tempMap);
        
        System.out.println("RESULT  \n" + result);
          }
    }

    public static Double findConstantOfParameter(Map<String,Double> map){
    	for (Map.Entry<String,Double> entry : map.entrySet()) {
    		if (entry.getKey().startsWith("x")) {
    		   return entry.getValue();
    		  }
    		}
    	return 0.0;
    }

    public static String findIndexOfParameter(Map<String,Double> map){
    	for (Map.Entry<String,Double> entry : map.entrySet()) {
    		if (entry.getKey().startsWith("x")) {
    		   return entry.getKey().substring(1, entry.getKey().length());
    		  }
    		}
    	return null;
    }
    
    public static void removeParameter(Map<String,Double> map){
    	Iterator<Map.Entry<String, Double>> iter = map.entrySet().iterator();
        while (iter.hasNext()) {
            Entry<String, Double> entry = iter.next();
            if (entry.getKey().startsWith("x")) 
                iter.remove();
        }
    }
    
    public static void nothing() {
        // for(int i = 0; i<rowsMaps.size();i++){
        System.out.println("result \n" + result);
        Map<String, Double> mapOfCurrentRow = new HashMap<String, Double>();
        mapOfCurrentRow = rowsMaps.get(4);
        System.out.println("Current row \n" + mapOfCurrentRow + "\n\n");

        List<Map<String, Double>> listOfMapsAtRow = new ArrayList<Map<String, Double>>();

        for (Map.Entry<String, Map<String, Double>> mapsEntry : result.entrySet()) {
            String key = mapsEntry.getKey();
            Double constant = mapOfCurrentRow.get(key);
            Map<String, Double> multipliedRow = new HashMap<String, Double>();
            multipliedRow = multiplyByConst(mapsEntry.getValue(), constant * (-1));
            listOfMapsAtRow.add(multipliedRow);
            // cause of putting null to used values -> find an only unused
            // values
            mapOfCurrentRow.put(key, null);
            System.out.println(mapOfCurrentRow);
        }
        System.out.println((listOfMapsAtRow));

        Map<String, Double> helper = new HashMap<String, Double>();

        // dosadi aj volne nezname, ktore sa v result nenachadzaju a hodnotu na
        // pravej strane
        for (Map.Entry<String, Double> map : mapOfCurrentRow.entrySet()) {
            String s = map.getKey();
            Double d = map.getValue();
            if (d != null && d != 0)
                helper.put(s, d);
        }
        listOfMapsAtRow.add(helper);
        // scita
        helper = add(listOfMapsAtRow);
        System.out.println(helper);
        // vydeli hodnotou nachadzajucu sa pri volnej premennej
        helper = divideByConst(helper, 3);
        System.out.println(helper);

        // snazim sa o priradenie parametrov
        // tato metoda az po dosadeni z result!
        // metoda-parametrisize

        Map<String, Double> tempMap = new HashMap<String, Double>();
        Iterator<Map.Entry<String, Double>> iter = helper.entrySet().iterator();
        while (iter.hasNext()) {
            Entry<String, Double> entry = iter.next();
            
                if (entry.getKey().startsWith("x")) {
                    iter.remove();
                    tempMap.put(params.get(0), entry.getValue());
                    params.remove(0);
                
            }
        }
        helper.putAll(tempMap);
        System.out.println("helper after \n" + helper);
        // }
    }

    public static Map<String, Double> findMapValueByKey(Map<String, Map<String, Double>> map, String key) {
        return result.get(key);
    }

    public static Double findDoubleValueByKey(Map<String, Double> map, String key) {
        return map.get(key);
    }

    public static Map<String, Double> substituteMaps(Map<String, Map<String, Double>> maps, Map<String, Double> map,
            String key) {
        return multiplyByConst(findMapValueByKey(maps, key), findDoubleValueByKey(map, key) * (-1));
    }

    public static void removeFromMapByKey(Map<String, Double> map, String key) {
        Iterator<Map.Entry<String, Double>> iter = map.entrySet().iterator();
        while (iter.hasNext()) {
            Entry<String, Double> entry = iter.next();
            if (entry.getKey().equals(key)) 
                iter.remove();
        }
    }

    public static int numberOfParameters(Map<String, Double> map) {
        int counter = 0;
        for (String s : map.keySet()) {
            if (s.startsWith("x"))
                counter += 1;
        }
        return counter;
    }

    
    public static Map<String,Double> sortByKeys(Map<String,Double> map) { 
        List<Map<String,Double>> list = new LinkedList(map.entrySet());
        Collections.sort(list, new Comparator() {
             public int compare(Object o1, Object o2) {
                return ((Comparable) ((Map.Entry) (o1)).getKey())
                   .compareTo(((Map.Entry) (o2)).getKey())*(-1);
             }
        });
        Map<String,Double> sortedHashMap = new LinkedHashMap();
        for (Iterator it = list.iterator(); it.hasNext();) {
               Map.Entry<String,Double> entry = (Map.Entry<String,Double>) it.next();
               sortedHashMap.put(entry.getKey(), entry.getValue());
        } 
        return sortedHashMap;
   }
    
    public static void parametrize(Map<String,Double> map){
        int numberOfParams = numberOfParameters(map);
        Map<String, Double> tempMap = new TreeMap<String, Double>();
        Iterator<Map.Entry<String, Double>> iter = map.entrySet().iterator();
        while (iter.hasNext()) {
            Entry<String, Double> entry = iter.next();
            String s = entry.getKey();
            Double d = entry.getValue();
            if (s.startsWith("x") && numberOfParams>1) {
                iter.remove();
                tempMap.put(params.get(0), d);
                params.remove(0);
                numberOfParams-=1;
            }
        }
        map.putAll(tempMap);
    }
    
    public static String findLastKey(Map<String, Double> map){
        List<String> list = new ArrayList<String>(map.keySet());
        return list.get(list.size());
    }
    
}