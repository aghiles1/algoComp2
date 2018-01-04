import java.util.ArrayList;
import java.util.List;

public class Algo {

    /**
     * exécution des 5 algorithmes sur les exemples fournis (exemple100.txt,
     *exemple500.txt, exemple1000.txt) et sur un exemple que vous donnez, dans un fichier ascii
     *nommé "monexemple.txt".
     * @param args
     */
    public static void main(String[] args) throws Exception {
        int binSize = 5;
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
        AbstractBP currentAlgo = new NextFit(100 , itemsSizes); // ou autre
        currentAlgo.run();
        System.out.println(" Size  : "+currentAlgo.getBins().size() );
    }
}
