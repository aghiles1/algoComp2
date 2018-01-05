import java.util.List;

public class AlmostWorst extends AbstractBP
{
    public AlmostWorst(int binSize, List<Item> items)
    {
        super(binSize, items);
    }

    private Bin getAlmostWorstBin(Item item) throws ItemsSizeException
    {
        int worst = -1;
        int almostWorst = -1;
        Bin b = null;
        Bin b2 = null;
        for(Bin bibi : bins){
            if(binSize - (bibi.getCapacity() + item.getSize()) > worst){
                almostWorst = worst;
                worst = binSize - (bibi.getCapacity() + item.getSize());
                b2 = b;
                b = bibi;
            }
        }

        if(b2 == null){
            b2 = new Bin(binSize);
            bins.add(b2);
        }

        return b2;
    }

    public void run() throws Exception
    {
        boolean firsTime = true;
        for(Item item : items){
            if(firsTime){
                bins.get(0).addItem(item);
                firsTime = false;
            }else {
                Bin worstBin = getAlmostWorstBin(item);
                worstBin.addItem(item);
            }
        }
    }
}