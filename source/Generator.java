import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Generator
{
    private int binSize;
    private int numberOfItems;

    public Generator(int binSize, int numberOfItems)
    {
        this.binSize = binSize;
        this.numberOfItems = numberOfItems;
    }

    public List<Item> createItems()
    {
        List<Item> items = new ArrayList<>();

        int value;
        Random random = new Random();

        for(int i = 0; i < numberOfItems; i++)
        {
            value = random.nextInt(binSize);
            items.add(new Item(value, i+1));
        }

        return items;
    }
}
