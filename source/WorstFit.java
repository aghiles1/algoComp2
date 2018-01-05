
import java.util.List;

public class WorstFit extends AbstractBP
{
    public WorstFit(int binSize, List<Item> items)
    {
        super(binSize, items);
    }

    private Bin getWorstBin(Item item) throws ItemsSizeException
    {
        int worst = 0;
        Bin b = null;
        for(Bin bibi : bins){
            if(binSize - (bibi.getCapacity() + item.getSize()) > worst){
                worst = binSize - (bibi.getCapacity() + item.getSize());
                b = bibi;
            }
        }

        if(b == null){
            b = new Bin(binSize);
            bins.add(b);
        }

        return b;
    }

    public void run() throws Exception
    {
        for(Item item : items){
            Bin worstBin = getWorstBin(item);
            worstBin.addItem(item);
        }
    }
}