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
        int maxCapacity = 0;
        int numberOfItems = 0;
        int numberOfSimulations = 0;
        int numberGenerator = 0;
        Generator generator = null;
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter the max capacities of the bins : ");
            maxCapacity = scanner.nextInt();
            System.out.println("Enter the number of items : ");
            numberOfItems = scanner.nextInt();
            System.out.println("Enter the number of simulations : ");
            numberOfSimulations = scanner.nextInt();
            System.out.println("Loi de probabilité utilisée pour la génération des items");
            System.out.println("0 - Poisson");
            System.out.println("1 - Exponnentielle");
            System.out.println("2 - Geometrique");
            System.out.println("3 - Uniforme");
            System.out.println("4 - Constante");
            System.out.println("5 - Normale");
            System.out.println("Tapez un chiffre :");
            numberGenerator = scanner.nextInt();
            while (numberGenerator > 5 || numberGenerator < 0) {
                System.out.println("Selectionner parmis les lois proposées de 0 à 6");
                numberGenerator = scanner.nextInt();
            }
            generator = new Generator(maxCapacity, numberOfItems, numberGenerator);
        }catch (Exception e){
            throw new NumberException("Integer");
        }

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

            saveSimulation((i+1), results);
        }

        saveSimulation(0, simulationsStats.getAverage());
    }

    public static void saveSimulation(int idSimulation, List<Node> results) throws IOException
    {
        Chart chart = new Chart();
        chart.createChar(results, idSimulation);

    }
}
