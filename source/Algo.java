import java.util.ArrayList;
import java.util.List;

public class Algo {

    /**
     * exécution des 5 algorithmes sur les exemples fournis (exemple100.txt,
     *exemple500.txt, exemple1000.txt) et sur un exemple que vous donnez, dans un fichier ascii
     *nommé "monexemple.txt".
     * @param args
     */
    public static void main(String[] args)
    {
        int binSize = 5;
        List<Integer> itemsSizes = new ArrayList<>();

        AbstractBP currentAlgo = new BestFit(5, itemsSizes); // ou autre
    }
}
