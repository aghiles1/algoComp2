import java.util.ArrayList;
import java.util.List;

public class NextFit extends AbstractBP
{
    public NextFit(int binSize, List<Item> items)
    {
        super(binSize, items);
    }


    /**
     * complexité : o(n) ou n est la taille de itemsSizes
     * @throws Exception
     */
    @Override
    public void run() throws Exception
    {
        for (int i = 0; i < items.size(); i++)
        {
            Item item = items.get(i);
            Bin bin = bins.get(bins.size() - 1);

            if (bin.canAddItem(item))
            {
                bin.addItem(item);
            }

            else
            {
                bin = new Bin(binSize);
                bins.add(bin);
                bin.addItem(item);
            }
        }
    }
}