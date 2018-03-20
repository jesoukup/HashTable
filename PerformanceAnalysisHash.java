import java.io.*;
import java.util.ArrayList;
import java.util.TreeMap;

/**
 * This class compares the TreeMap with the HashTable to see which one is more efficient with memory and speed
 */
public class PerformanceAnalysisHash<K, V> implements PerformanceAnalysis {
    private ArrayList<String> inputData = new ArrayList<>(); // This arraylist stores each line of the file
    private HashTable hash; // This is our hash table
    private TreeMap tmap; // This is our tree map
    private long hashInsertTime; // These variables store each method's speeds or memory
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
    private String fileName; // This saves what filename we are using
    private String operationType; // This saves what operation we are doing
    private String structureType; // This saves what structure we are using
    private ArrayList<String> files; // This arraylist contains our filenames
    
    public PerformanceAnalysisHash() {
    }

    /**
     * This method constructs our PerformanceAnalysisHash 
     * 
     * @param details_filename: the file we want to use to compare
     */
    public PerformanceAnalysisHash(String details_filename) {
        try {
            loadData(details_filename);
        }
        catch (IOException e) {
            System.out.println("IOException occurred.");
        }
    }
    
    /**
     * The important function that compares the implemented HashTable with
     * TreeMap of Java and generates the table with all the comparison details
     * This can internally call - compareInsertion, compareDeletion, CompareSearch
     * for all the test data provided.
     */
    @Override
    public void compareDataStructures() {
        compareInsertion();
        compareDeletion();
        compareSearch();
    }

    /** 
    * Function used to print out the final report
    */
    @Override
    public void printReport() {
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
    
    /**
     * This method helps print the report depending on what file, structure, and operation we are using
     * 
     * @param operationType: the operation we are testing
     * @param structureType: the structure we are testing
     * @param time: the time variable to check
     * @param mem: the memory variable to check
     */
    private void printHelper(String operationType, String structureType, long time, long mem) {
        time = time / 1000;
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

    /**
     * This method calls on helper methods to compare the insertion operation
     */
    @Override
    public void compareInsertion() {
        compareHashInsertion();
        compareTreeMapInsertion();
    }
    
    /**
     * This method tests the hash insertion
     */
    private void compareHashInsertion() {
        long curTime = System.nanoTime();
        long curMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        hash = new HashTable<K, V>(10, .75); 
        for (int i = 0; i < inputData.size(); i++) {
            hash.put(i, inputData.get(i));
        }
        hashInsertTime = System.nanoTime() - curTime;
        hashInsertMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory() - curMem;
    }
    
    /**
     * This method tests the treemap insertion
     */
    private void compareTreeMapInsertion() {
        long curTime = System.nanoTime();
        long curMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        tmap = new TreeMap();
        for (int i = 0; i < inputData.size(); i++) {
            tmap.put(i, inputData.get(i));
        }
        tmapInsertTime = System.nanoTime() - curTime;
        tmapInsertMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory() - curMem;
    }

    /**
     * This method calls on helper methods to compare the deletion operation
     */
    @Override
    public void compareDeletion() {
        compareHashDeletion();
        compareTreeMapDeletion();
    }
    
    /**
     * This method tests the hash deletion
     */
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
    
    /**
     * This method tests the treemap deletion
     */
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

    /**
     * This method calls on helper methods to compare the search operation
     */
    @Override
    public void compareSearch() {
        compareHashSearch();
        compareTreeMapSearch();
    }
    
    /**
     * This method tests the hash search
     */
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
    
    /**
     * This method tests the treemap search
     */
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
        ArrayList<String> files = new ArrayList<>();
        File datafile = new File(filename);
        BufferedReader brdr = new BufferedReader(new FileReader(datafile));
        String fileline = brdr.readLine();
        while (fileline != null) {
            String[] string = fileline.split(",");
            String newLine = string[0];
            files.add(newLine);
        }
        brdr.close();
        for (int i = 1; i < files.size(); i++) {
            // loadData(files.get(i));
            System.out.println(files.get(i));
        }
        fileName = filename.substring(5);
        File file = new File(filename);
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line = br.readLine();
        while (line != null) {
            inputData.add(line);
            line = br.readLine();
        }
        br.close();
    }
}
