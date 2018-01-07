
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
                return Integer.compare(o1.getCapacity(), o2.getCapacity());
            }
        });

        Bin b = null;
        for(Item item : items){
            if(!heap.isEmpty())
                b = heap.peek();
            if(b != null && b.getFreeSpace() < item.getSize()){
                b = null;
            }
            if(b == null){
                b = new Bin(binSize);
                bins.add(b);
            }
            b.addItem(item);
            heap.add(b);
        }
/*
        System.out.println("1234");
        while(!heap.isEmpty()){
            System.out.println("5678");
            System.out.println(heap.remove());
        }*/
        time = (System.nanoTime() - debut)/NSTOMS;
    }
}