public class AnalysisTest {
    public static void main(String[] args)  {

                // TODO Add code for checking command line arguments
                
                PerformanceAnalysisHash ana = new PerformanceAnalysisHash("data\\IntegerLarge.txt");
                ana.compareDataStructures();
                ana.printReport();
            }
}
