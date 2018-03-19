import java.io.*;
import java.util.ArrayList;
import java.util.TreeMap;

public class PerformanceAnalysisHash<K, V> implements PerformanceAnalysis {

    // The input data from each file is stored in this/ per file
    private ArrayList<String> inputData = new ArrayList<>();
    private HashTable hash;
    private TreeMap tmap;
    private long hashInsertTime;
    private long hashInsertMemory;
    private long tmapInsertTime;
    private long tmapInsertMemory;
    private long hashDeleteTime;
    private long hashDeleteMemory;
    private long tmapDeleteTime;
    private long tmapDeleteMemory;
    private long hashSearchTime;
    private long hashSearchMemory;
    private long tmapSearchTime;
    private long tmapSearchMemory;
    private String fileName;
    private String operationType;
    private String structureType;
    
    public PerformanceAnalysisHash(){
    }

    public PerformanceAnalysisHash(String details_filename){
        //TODO: Save the details of the test data files
        try {
            loadData(details_filename);
        }
        catch (IOException e) {
            System.out.println("IOException occurred.");
        }
    }
    @Override
    public void compareDataStructures() {
        //TODO: Complete this function which compares the ds and generates the details
        compareInsertion();
    }

    @Override
    public void printReport() {
        //TODO: Complete this method
    	System.out.println("------------------------------------------------------------------------------------------------");
    	System.out.println("|            FileName|      Operation| Data Structure|   Time Taken (micro sec)|     Bytes Used|");
    	System.out.println("------------------------------------------------------------------------------------------------");
    	
    	printHelper("PUT", "HASHTABLE", hashInsertTime, hashInsertMemory);
    	printHelper("PUT", "TREEMAP", tmapInsertTime, tmapInsertMemory);
    	printHelper("GET", "HASHTABLE", hashSearchTime, hashSearchMemory);
    	printHelper("GET", "TREEMAP", tmapSearchTime, tmapSearchMemory);
    	printHelper("REMOVE", "HASHTABLE", hashDeleteTime, hashDeleteMemory);
    	printHelper("REMOVE", "TREEMAP", tmapDeleteTime, tmapDeleteMemory);
    	System.out.println("------------------------------------------------------------------------------------------------");
    }
    
    private void printHelper(String operationType, String structureType, long time, long mem) {
    	System.out.print("|");
    	for (int i = 0; i < 20 - fileName.length(); i++) {
    		System.out.print(" ");
    	}
    	System.out.print(fileName + "|");
    	for (int i = 0; i < 15 - operationType.length(); i++) {
    		System.out.print(" ");
    	}
    	System.out.print(operationType + "|");
    	for (int i = 0; i < 15 - structureType.length(); i++) {
    		System.out.print(" ");
    	}
    	System.out.print(structureType + "|");
    	for (int i = 0; i < 25 - String.valueOf(time).length(); i++) {
    		System.out.print(" ");
    	}
    	System.out.print(time + "|");
    	for (int i = 0; i < 15 - String.valueOf(mem).length(); i++) {
    		System.out.print(" ");
    	}
    	System.out.println(mem + "|");
    }

    @Override
    public void compareInsertion() {
        //TODO: Complete this method
        compareHashInsertion();
        compareTreeMapInsertion();
    }
    
    private void compareHashInsertion() {
        long current = System.nanoTime();
        long curMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        hash = new HashTable<K, V>(10, .75);
        for (int i = 0; i < inputData.size(); i++) {
            hash.put(i, inputData.get(i));
        }
        hashInsertTime = System.nanoTime() - current;
        hashInsertMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
    }
    
    private void compareTreeMapInsertion() {
        long current = System.nanoTime();
        tmap = new TreeMap();
        for (int i = 0; i < inputData.size(); i++) {
            tmap.put(i, inputData.get(i));
        }
        tmapInsertTime = System.nanoTime() - current;
        tmapInsertMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
    }

    @Override
    public void compareDeletion() {
        //TODO: Complete this method
        compareHashDeletion();
        compareTreeMapDeletion();
    }
    
    private void compareHashDeletion() {
        hash = new HashTable<K, V>(50, .75); 
        for (int i = 0; i < inputData.size(); i++) {
            hash.put(i, inputData.get(i));
        }
        long curTime = System.nanoTime();
        long curMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        for (int i = inputData.size() - 1; i >= 0; i--) {
            hash.remove(i);
        }
        hashDeleteTime = System.nanoTime() - curTime;
        hashDeleteMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory() - curMem;
    }
    
    private void compareTreeMapDeletion() {
        tmap = new TreeMap();
        for (int i = 0; i < inputData.size(); i++) {
            tmap.put(i, inputData.get(i));
        }
        long curTime = System.nanoTime();
        long curMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        for (int i = inputData.size() - 1; i >= 0; i--) {
            tmap.remove(i);
        }
        tmapDeleteTime = System.nanoTime() - curTime;
        tmapDeleteMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory() - curMem;
    }

    @Override
    public void compareSearch() {
        //TODO: Complete this method
        compareHashSearch();
        compareTreeMapSearch();
    }
    
    private void compareHashSearch() {
        hash = new HashTable<K, V>(1000000, .75); 
        for (int i = 0; i < inputData.size(); i++) {
            hash.put(i, inputData.get(i));
        }
        long curTime = System.nanoTime();
        long curMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        for (int i = inputData.size() - 1; i >= 0; i--) {
            hash.get(i);
        }
        hashSearchTime = System.nanoTime() - curTime;
        hashSearchMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory() - curMem;
    }
    
    private void compareTreeMapSearch() {
        tmap = new TreeMap();
        for (int i = 0; i < inputData.size(); i++) {
            tmap.put(i, inputData.get(i));
        }
        long curTime = System.nanoTime();
        long curMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        for (int i = inputData.size() - 1; i >= 0; i--) {
            tmap.get(i);
        }
        tmapSearchTime = System.nanoTime() - curTime;
        tmapSearchMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory() - curMem;
    }

    /*
    An implementation of loading files into local data structure is provided to you
    Please feel free to make any changes if required as per your implementation.
    However, this function can be used as is.
     */
    @Override
    public void loadData(String filename) throws IOException {

        // Opens the given test file and stores the objects each line as a string
    	fileName= filename.substring(5);
        File file = new File(filename);
        BufferedReader br = new BufferedReader(new FileReader(file));
//      inputData = new ArrayList<>();
        String line = br.readLine();
        while (line != null) {
            inputData.add(line);
            line = br.readLine();
        }
        br.close();
    }
    
//    public static void main(String[] args) {
//        PerformanceAnalysisHash analysis = new PerformanceAnalysisHash("StringSmall.txt");
//        analysis.compareDataStructures();
//    }
}