import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Stat
{
    /**
     * effectue une série de simulations sur des exemples générés de manière aléatoire (le
     * nombre et la manière de générer seront fourni par une phase de dialogue input/output)
     * @param args
     */
    public static void main(String[] args) throws Exception
    {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the max capacities of the bins : ");

        int maxCapacity = scanner.nextInt();

        System.out.println("Enter the number of items : ");

        int numberOfItems = scanner.nextInt();

        System.out.println("Enter the number of simulations : ");

        int numberOfSimulations = scanner.nextInt();

        Generator generator = new Generator(maxCapacity, numberOfItems);

        List<AbstractBP> algorithms = new ArrayList<>();

        algorithms.add(new NextFit(0 , new ArrayList<>()));
        algorithms.add(new FirstFit(0 , new ArrayList<>()));
        algorithms.add(new BestFit(0 , new ArrayList<>()));
        algorithms.add(new WorstFit(0, new ArrayList<>()));
        algorithms.add(new AlmostWorst(0, new ArrayList<>()));

        SimulationsStats simulationsStats = new SimulationsStats(algorithms);

        for(int i = 0; i < numberOfSimulations; i++)
        {
            List<Item> items = generator.createItems();

            for(AbstractBP algo : algorithms)
            {
                algo.reset(maxCapacity, items);
            }

            Algo.runAndSave("simulations/traces/simulation_" + (i+1) + ".txt", items, maxCapacity, algorithms);

            System.out.println("Simulation n°" + (i+1) + " :\n");

            List<Node> results = new ArrayList<>();

            for(AbstractBP algo : algorithms)
            {
                int currentSize = algo.getBins().size();
                int currentTime =(int) (algo.time * 1000000);
                results.add(new Node(algo, currentSize, currentTime));
            }

            simulationsStats.addNewSimulation(results);

            System.out.println("\n");

            saveSimulation("simulations/histograms", (i+1), results);
        }

        saveSimulation("simulations/histograms/globalHistogram", simulationsStats.getAverage());
    }

    public static void saveSimulation(String relativeDirectory, int idSimulation, List<Node> results) throws IOException
    {
        String fileName = relativeDirectory + "/simulation_" + idSimulation + ".csv";
        saveSimulation(fileName, results);
    }

    public static void saveSimulation(String fileName, List<Node> results) throws IOException
    {
        FileWriter fileWriter = new FileWriter(System.getProperties().get("user.dir") + "/" + fileName);
        BufferedWriter bufWriter = new BufferedWriter(fileWriter);

        String text = "Algorithm,Number of bins,Execution time (Ns)\n";

        for(Node node : results)
        {
            text += node.algorithm.getName() + ",";
            text += node.numberOfBins + ",";
            text += node.time + "\n";
        }

        bufWriter.write(text);
        bufWriter.close();
        fileWriter.close();
    }
}
