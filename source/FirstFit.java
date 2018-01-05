import java.util.List;

public class FirstFit extends AbstractBP
{
    public FirstFit(int binSize, List<Item> items)
    {
        super(binSize, items);
    }

    private Bin getFirstBin(Item item) throws ItemsSizeException
    {
        for(int i = 0; i < bins.size(); i++)
        {
            if(bins.get(i).canAddItem(item))
            {
                return bins.get(i);
            }
        }

        Bin bin = new Bin(binSize);
        bins.add(bin);

        return bin;
    }

    @Override
    public void run() throws Exception
    {
        reset(binSize, items);

        long debut = System.nanoTime();
        for(int i = 0; i < items.size(); i++)
        {
            Item item = items.get(i);
            Bin firstBin = getFirstBin(item);
            firstBin.addItem(item);
        }
        time = (System.nanoTime() - debut)/NSTOMS;
    }
}