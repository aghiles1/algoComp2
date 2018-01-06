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

            Sort sort = new Sort(results);

            List<Node> efficients = sort.getByEffectiveness();
            List<Node> timeSpeed = sort.getByTime();

            System.out.println("Efficacité :");

            for(Node node : efficients)
            {
                System.out.println(node.algorithm.getName());
            }

            System.out.println("\nTemps :");

            for(Node node : timeSpeed)
            {
                System.out.println(node.algorithm.getName());
            }

            System.out.println("\n");

            saveSimulation((i+1), results);
        }
    }

    public static void saveSimulation(int idSimulation, List<Node> results) throws IOException
    {
        Chart chart = new Chart();
        chart.createChar(results, idSimulation);

    }
}
