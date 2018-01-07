import java.util.*;

public class AlmostWorst extends AbstractBP
{
    public AlmostWorst(int binSize, List<Item> items)
    {
        super(binSize, items);
    }

    @Override
    public void run() throws Exception
    {
        reset(binSize, items);

        long debut = System.nanoTime();

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