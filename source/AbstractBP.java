import java.util.ArrayList;
import java.util.List;

public abstract class AbstractBP
{
    protected int binSize;
    protected List<Integer> bins;
    protected List<Integer> itemsSizes;

    public AbstractBP(int binSize, List<Integer> itemsSizes)
    {
        this.binSize = binSize;
        this.itemsSizes = itemsSizes;
        bins = new ArrayList<>();
    }

    public abstract void run();
}
