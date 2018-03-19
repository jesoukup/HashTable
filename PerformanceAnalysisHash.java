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
        System.out.println(hashInsertTime);
        System.out.println(hashInsertMemory);
        System.out.println(tmapInsertTime);
        System.out.println(tmapInsertMemory);
    }

    @Override
    public void compareInsertion() {
        //TODO: Complete this method
        compareHashInsertion();
        compareTreeMapInsertion();
    }
    
    private void compareHashInsertion() {
        long current = System.nanoTime();
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
    }

    @Override
    public void compareSearch() {
        //TODO: Complete this method
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
