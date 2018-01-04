package Algos;

import java.util.List;

public class FirstFit extends AbstractBP
{
    public FirstFit(int binSize, List<Integer> itemsSizes)
    {
        super(binSize, itemsSizes);
    }

    private int getIndexToSet(int currentItemSize)
    {
        for(int i = 0; i < bins.size(); i++)
        {
            int currentSize = bins.get(i);

            if(currentItemSize + currentSize <= binSize)
            {
                return i;
            }
        }

        return -1;
    }

    @Override
    public void run() throws Exception
    {
        bins.add(0);

        for(int i = 0; i < itemsSizes.size(); i++)
        {
            int currentItemSize = itemsSizes.get(i);

            if(currentItemSize > binSize)
            {
                throw new ItemsSizeException();
            }

            int binIndex = getIndexToSet(currentItemSize);

            if(binIndex == -1)
            {
                binIndex = bins.size();
                bins.add(0);
            }

            int newSize = bins.get(binIndex) + currentItemSize;

            bins.set(binIndex, newSize);
        }
    }
}
