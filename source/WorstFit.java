
import java.util.*;

public class WorstFit extends AbstractBP
{
    public WorstFit(int binSize, List<Item> items)
    {
        super(binSize, items);
    }

    private Bin getWorstBin(Item item) throws ItemsSizeException
    {
        int worst = -1;
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

    @Override
    public void run() throws Exception
    {
        reset(binSize, items);

        long debut = System.nanoTime();
        /*
        for(Item item : items){
            Bin worstBin = getWorstBin(item);
            worstBin.addItem(item);
        }*/

        bins = new ArrayList<>();

        Queue<Bin> heap = new PriorityQueue<Bin>(new Comparator<Bin>() {
            @Override
            public int compare(Bin o1, Bin o2) {
                return Integer.compare(o2.getFreeSpace(), o1.getFreeSpace());
            }
        });

        Bin b;
        for(Item item : items){
            b = heap.poll();
            if(b != null && b.getFreeSpace() < item.getSize()){
                heap.offer(b);
                b = null;
            }
            if(b == null){
                b = new Bin(binSize);
                bins.add(b);
            }
            b.addItem(item);
            heap.offer(b);
        }
        time = (System.nanoTime() - debut)/NSTOMS;
    }
}