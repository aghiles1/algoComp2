import java.util.ArrayList;
import java.util.List;

public class SimulationsStats
{
    private int numberOfSimulations;
    private List<Node> simulationsSum;

    public SimulationsStats(List<AbstractBP> algortihms)
    {
        numberOfSimulations = 0;
        simulationsSum = new ArrayList<>();

        for(int i = 0; i < algortihms.size(); i++)
        {
            Node node = new Node(algortihms.get(i), 0, 0);
            simulationsSum.add(node);
        }
    }

    public void addNewSimulation(List<Node> results)
    {
        numberOfSimulations++;

        for(int i = 0; i < simulationsSum.size(); i++)
        {
            Node node = simulationsSum.get(i);
            node.numberOfBins += results.get(i).numberOfBins;
            node.time += results.get(i).time;
        }
    }

    public List<Node> getAverage()
    {
        List<Node> average = new ArrayList<>();

        for(int i = 0; i < simulationsSum.size(); i++)
        {
            Node node = simulationsSum.get(i);

            double binAverage = (node.numberOfBins * 1.0) / numberOfSimulations;
            double timeAverage = (node.time * 1.0) / numberOfSimulations;

            Node averageNode = new Node(node.algorithm, (int) (Math.round(binAverage)), (int) (Math.round(timeAverage)));
            average.add(averageNode);
        }

        return average;
    }
}