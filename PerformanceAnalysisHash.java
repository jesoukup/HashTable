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
        compareDeletion();
        compareSearch();
    }

    @Override
    public void printReport() {
        //TODO: Complete this method
        System.out.println(hashInsertTime);
        System.out.println(hashInsertMemory);
        System.out.println(tmapInsertTime);
        System.out.println(tmapInsertMemory);
        System.out.println(hashDeleteTime);
        System.out.println(hashDeleteMemory);
        System.out.println(tmapDeleteTime);
        System.out.println(tmapDeleteMemory);
        System.out.println(hashSearchTime);
        System.out.println(hashSearchMemory);
        System.out.println(tmapSearchTime);
        System.out.println(tmapSearchMemory);
    }

    @Override
    public void compareInsertion() {
        //TODO: Complete this method
        compareHashInsertion();
        compareTreeMapInsertion();
    }
    
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
        File file = new File(filename);
        BufferedReader br = new BufferedReader(new FileReader(file));
//        inputData = new ArrayList<>();
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
