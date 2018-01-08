
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Algo
{

    /*
     * exécution des 5 algorithmes sur les exemples fournis (exemple100.txt,
     *exemple500.txt, exemple1000.txt) et sur un exemple que vous donnez, dans un fichier ascii
     *nommé "monexemple.txt".
     */
    public static void main(String[] args) throws Exception
    {
        List<String> filesToLoad = new ArrayList<>();

        filesToLoad.add("exemple100.txt");
        filesToLoad.add("exemple500.txt");
        filesToLoad.add("exemple1000.txt");
        filesToLoad.add("monexemple.txt");

        for(String file : filesToLoad)
        {
            try
            {
                Parser parser = new Parser("exemples/" + file);

                int binSize = parser.getBinSize();
                List<Item> items = parser.getItems();

                List<AbstractBP> algos = new ArrayList<>();

                algos.add(new NextFit(binSize , items));
                algos.add(new FirstFit(binSize , items));
                algos.add(new BestFit(binSize , items));
                algos.add(new WorstFit(binSize, items));
                algos.add(new AlmostWorst(binSize, items));

                String outputFileName = file.replace("exemple", "output");

                if(file.contains("monexemple"))
                {
                    outputFileName = "outputMonExemple.txt";
                }

                runAndSave("exemples/" + outputFileName, items, binSize, algos);

                System.out.println("Created " + System.getProperties().get("user.dir") + "/exemples/" + outputFileName);
            }

            catch (IOException ex)
            {
                System.out.println("Unable to read the file " + System.getProperties().get("user.dir") + "/exemples/" + file);
            }
        }
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
                text += "\ntime = " + currentAlgo.time + " ms\n";
            }

            catch (Exception ex)
            {
                text += "Exception : " + ex.getMessage() + "\n";
            }
        }

        text += "\n\n";

        bufWriter.write(text);
        bufWriter.close();
        fileWriter.close();
    }
}