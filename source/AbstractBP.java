
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractBP
{
    protected int binSize;
    protected List<Bin> bins;
    protected List<Item> items;

    public AbstractBP(int binSize, List<Item> items)
    {
        this.binSize = binSize;
        this.items = new ArrayList<>(items);
        bins = new ArrayList<>();
        bins.add(new Bin(binSize));
    }

    public abstract void run() throws Exception;

    public List<Bin> getBins() {
        return bins;
    }

    public String getName()
    {
        return this.getClass().getName();
    }
}
