import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Parser
{
    private int binSize;
    private List<Item> items;

    public Parser(String fileName) throws Exception
    {
        items = new ArrayList<>();

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

                    this.items.add(new Item(Integer.parseInt(string), this.items.size() + 1));
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

    public List<Item> getItems()
    {
        return items;
    }
}