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

        if(!algorithms.isEmpty())
        {
            for(int i = 0; i < numberOfSimulations; i++)
            {
                List<Item> items = generator.createItems();

                for(AbstractBP algo : algorithms)
                {
                    algo.reset(maxCapacity, items);
                }

                Algo.runAndSave("simulations/simulation_" + (i+1) + ".txt", items, maxCapacity, algorithms);

                int minBinSize = algorithms.get(0).getBins().size();
                double minTime = algorithms.get(0).time;

                int mostEfficientAlgoIndex = 0;
                int fastestAlgoIndex = 0;

                for(int j = 1; j < algorithms.size(); j++)
                {
                    AbstractBP algo = algorithms.get(j);

                    int currentSize = algo.getBins().size();
                    double currentTime = algo.time;

                    if(minBinSize > currentSize)
                    {
                        minBinSize = currentSize;
                        mostEfficientAlgoIndex = j;
                    }

                    if(minTime > currentTime)
                    {
                        minTime = currentTime;
                        fastestAlgoIndex = j;
                    }
                }

                AbstractBP mostEfficientAlgo = algorithms.get(mostEfficientAlgoIndex);
                AbstractBP fastestAlgo = algorithms.get(fastestAlgoIndex);

                System.out.println("Simulation n°" + (i+1) + " :");
                System.out.println("Most efficient algorithm = " + mostEfficientAlgo.getName());
                System.out.println("Fastest algorithm = " + fastestAlgo.getName());
                System.out.println();
            }
        }
    }
}
