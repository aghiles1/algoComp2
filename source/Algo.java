
import java.util.ArrayList;
import java.util.List;

public class Algo
{

    /**
     * exécution des 5 algorithmes sur les exemples fournis (exemple100.txt,
     *exemple500.txt, exemple1000.txt) et sur un exemple que vous donnez, dans un fichier ascii
     *nommé "monexemple.txt".
     * @param args
     */
    public static void main(String[] args) throws Exception
    {
        /*
        Parser parser = new Parser("exemples/exemple1000.txt");

        System.out.println(parser.getBinSize());
        List<Integer> itemsSizes = parser.getItemsSizes();
        */

        List<Integer> itemsSizes = new ArrayList<>();

        itemsSizes.add(50);
        itemsSizes.add(70);
        itemsSizes.add(50);
        itemsSizes.add(20);
        itemsSizes.add(40);
        itemsSizes.add(20);
        itemsSizes.add(50);
        itemsSizes.add(10);
        itemsSizes.add(60);

        List<AbstractBP> algos = new ArrayList<>();

        algos.add(new NextFit(100 , itemsSizes));
        algos.add(new FirstFit(100 , itemsSizes));

        printItemsSizes(itemsSizes);
        runAll(algos);
    }

    public static void printItemsSizes(List<Integer> itemsSizes)
    {
        System.out.print("Items : ");

        for(Integer integer : itemsSizes)
        {
            System.out.print(integer + "  ");
        }

        System.out.println("\n");
    }

    public static void printBin(List<Integer> bins)
    {
        System.out.print("Size : " + bins.size() + " et Bins = ");

        for(Integer integer : bins)
        {
            System.out.print(integer + "  ");
        }

        System.out.println();
    }

    public static void runAll(List<AbstractBP> algos)
    {
        for(AbstractBP currentAlgo : algos)
        {
            try
            {
                currentAlgo.run();
                printBin(currentAlgo.getBins());
            }

            catch (Exception ex)
            {
                System.out.println(ex.getMessage());
            }
        }
    }
}
