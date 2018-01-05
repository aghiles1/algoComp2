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
     * dés qu'on peut pas mettre un item dans un bin on passe au bin suivant ect...
     * @throws Exception
     */
    @Override
    public void run() throws Exception
    {
        reset(binSize, items);

        long debut = System.nanoTime();
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
        time = (System.nanoTime() - debut)/NSTOMS;
    }
}