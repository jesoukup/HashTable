public class AnalysisTest {
    public static void main(String[] args)  {

                // TODO Add code for checking command line arguments
                
                PerformanceAnalysisHash ana = new PerformanceAnalysisHash("data\\IntegerSmall.txt");
                ana.compareDataStructures();
                ana.printReport();
                PerformanceAnalysisHash ana2 = new PerformanceAnalysisHash("data\\IntegerLarge.txt");
                ana2.compareDataStructures();
                ana2.printReport();
                PerformanceAnalysisHash ana3 = new PerformanceAnalysisHash("data\\StringSmall.txt");
                ana3.compareDataStructures();
                ana3.printReport();
                PerformanceAnalysisHash ana4 = new PerformanceAnalysisHash("data\\StringLarge.txt");
                ana4.compareDataStructures();
                ana4.printReport();
            }
}
