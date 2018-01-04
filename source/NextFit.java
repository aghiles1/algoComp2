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
        bins.add(0);
        for (int i=0;i < itemsSizes.size(); i++){
            if (binSize < itemsSizes.get(i)){
                throw new ItemsSizeException();
            }
            if (bins.get(bins.size()-1) + itemsSizes.get(i) <= binSize){
                bins.set(bins.size()-1,bins.get(bins.size()-1)+itemsSizes.get(i));
            }else{
                bins.add(0);
                bins.set(bins.size()-1,bins.get(bins.size()-1)+itemsSizes.get(i));
            }

        }

    }

}
