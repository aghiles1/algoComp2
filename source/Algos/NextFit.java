package Algos;

import java.util.ArrayList;
import java.util.List;

public class NextFit extends AbstractBP
{
    public NextFit(int binSize, List<Integer> itemsSizes)
    {
        super(binSize, itemsSizes);
    }


    /**
     * complexité : o(n) ou n est la taille de itemsSizes
     * @throws Exception
     */
    @Override
    public void run() throws Exception {
        bins = new ArrayList<>();
        for (int i=0;i < itemsSizes.size(); i++){
            if (bins.isEmpty()){
                bins.add(binSize);
            }
            if (binSize < itemsSizes.get(i)){
                throw new ItemsSizeException();
            }
            if (bins.get(bins.size()-1) > itemsSizes.get(i)){
                bins.set(bins.size()-1,bins.get(bins.size()-1)-itemsSizes.get(i));
            }else{
                bins.add(binSize);
                bins.set(bins.size()-1,bins.get(bins.size()-1)-itemsSizes.get(i));
            }

        }

    }

}
