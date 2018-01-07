import java.util.*;

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
            if(b != null)
                return b;
            b2 = new Bin(binSize);
            bins.add(b2);
        }

        return b2;
    }

    @Override
    public void run() throws Exception
    {
        reset(binSize, items);

        long debut = System.nanoTime();

        /*
        boolean firsTime = true;
        for(Item item : items){
            if(firsTime){
                bins.get(0).addItem(item);
                firsTime = false;
            }else {
                Bin worstBin = getAlmostWorstBin(item);
                worstBin.addItem(item);
            }
        }*/

        bins = new ArrayList<>();
        Queue<Bin> heap = new PriorityQueue<Bin>(new Comparator<Bin>() {
            @Override
            public int compare(Bin o1, Bin o2) {
                return Integer.compare(o2.getFreeSpace(), o1.getFreeSpace());
            }
        });

        Bin b = null;
        Bin temp = null;
        for(Item item: items){
            if(heap.size() >= 2){
                temp = heap.poll();
                b = heap.poll();
            }else
                b = heap.poll();

            if(b != null && b.getFreeSpace() < item.getSize()){
                heap.offer(b);
                if(temp != null && temp.getFreeSpace() < item.getSize()){
                    heap.offer(temp);
                    temp = null;
                }
                b = temp;
                temp = null;
            }
            if(b == null){
                b = new Bin(binSize);
                bins.add(b);
            }

            b.addItem(item);
            heap.offer(b);
            if(temp != null){
                heap.offer(temp);
                temp = null;
            }
        }
        time = (System.nanoTime()-debut)/NSTOMS;
    }
}