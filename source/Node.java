public class Node
{
    public AbstractBP algorithm;
    public int numberOfBins;
    public int time;

    public Node(AbstractBP algorithm, int numberOfBins, int time)
    {
        this.algorithm = algorithm;
        this.numberOfBins = numberOfBins;
        this.time = time;
    }
}
