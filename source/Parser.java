import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Parser
{
    private int binSize;
    private List<Integer> itemsSizes;

    public Parser(String fileName) throws Exception
    {
        itemsSizes = new ArrayList<>();

        FileReader fileReader = new FileReader(System.getProperties().get("user.dir") + "/" + fileName);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String line;

        if(bufferedReader.readLine() != null)
        {
            line = bufferedReader.readLine();

            if(line != null)
            {
                binSize = Integer.parseInt(line);
            }
        }

        if(bufferedReader.readLine() != null)
        {
            line = bufferedReader.readLine();

            if(line != null)
            {
                String items[] = line.split(", ");

                for(String string : items)
                {
                    if(string.contains("."))
                    {
                        string = string.replace(".", "");
                    }

                    itemsSizes.add(Integer.parseInt(string));
                }
            }
        }

        bufferedReader.close();
        fileReader.close();
    }

    public int getBinSize()
    {
        return binSize;
    }

    public List<Integer> getItemsSizes()
    {
        return itemsSizes;
    }
}
