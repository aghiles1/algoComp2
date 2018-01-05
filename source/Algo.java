
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
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
        Parser parser = new Parser("exemples/exemple100.txt");

        int binSize = parser.getBinSize();
        List<Item> items = parser.getItems();*/

        List<Item> items = new ArrayList<>();

        items.add(new Item(80, 1));
        items.add(new Item(91, 2));
        items.add(new Item(50, 4));
        items.add(new Item(85, 5));
        items.add(new Item(3, 6));
        items.add(new Item(8, 7));
        items.add(new Item(30, 8));
        items.add(new Item(9, 8));

        int binSize = 100;

        List<AbstractBP> algos = new ArrayList<>();

        algos.add(new NextFit(binSize , items));
        algos.add(new FirstFit(binSize , items));
        algos.add(new BestFit(binSize , items));
        algos.add(new WorstFit(binSize, items));
        algos.add(new AlmostWorst(binSize, items));

        runAndSave("exemples/output100.txt", items, binSize, algos);
    }

    public static void runAndSave(String fileName, List<Item> items, int binSize, List<AbstractBP> algos) throws IOException
    {
        FileWriter fileWriter = new FileWriter(System.getProperties().get("user.dir") + "/" + fileName);
        BufferedWriter bufWriter = new BufferedWriter(fileWriter);

        String text = "Max capacity of the bins : " + binSize + "\n";

        for(Item item : items)
        {
            text += "\n" + item.toString();
        }

        for(AbstractBP currentAlgo : algos)
        {
            text += "\n\n";
            text += currentAlgo.getName() + " : ";

            try
            {
                currentAlgo.run();
                text += "Size = " + currentAlgo.getBins().size() + "\n\n";

                List<Bin> bins = currentAlgo.getBins();

                for(Bin bin : bins)
                {
                    text += bin + "\n";
                }
            }

            catch (Exception ex)
            {
                text += "Exception : " + ex.getMessage() + "\n";
            }
        }

        bufWriter.write(text);

        bufWriter.close();
        fileWriter.close();
    }
}