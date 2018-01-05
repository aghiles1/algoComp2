import java.util.List;

public class BestFit extends AbstractBP
{
    public BestFit(int binSize, List<Item> items)
    {
        super(binSize, items);
    }

    private Bin getBestBin(Item item) throws ItemsSizeException
    {
        int firstIndexToLookFor = -1;

        for(int i = 0; i < bins.size(); i++)
        {
            if(bins.get(i).canAddItem(item))
            {
                firstIndexToLookFor = i;
                break;
            }
        }

        if(firstIndexToLookFor == -1)
        {
            Bin bin = new Bin(binSize);
            bins.add(bin);

            return bin;
        }

        int max = bins.get(firstIndexToLookFor).getCapacity() + item.getSize();

        int minIndex = firstIndexToLookFor;

        for(int i = firstIndexToLookFor + 1; i < bins.size(); i++)
        {
            if(bins.get(i).canAddItem(item) && max < bins.get(i).getCapacity() + item.getSize())
            {
                max = bins.get(i).getCapacity() + item.getSize();
                minIndex = i;
            }
        }

        return bins.get(minIndex);
    }

    public void run() throws Exception
    {
        for(int i = 0; i < items.size(); i++)
        {
            Item item = items.get(i);
            Bin lastBin = getBestBin(item);
            lastBin.addItem(item);
        }
    }
}