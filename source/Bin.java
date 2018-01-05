import java.util.ArrayList;
import java.util.List;

public class Bin
{
    private int maxCapacity;
    private int capacity;
    private List<Item> items;

    public Bin(int maxCapacity)
    {
        this.maxCapacity = maxCapacity;
        capacity = 0;
        items = new ArrayList<>();
    }

    public boolean addItem(Item item) throws ItemsSizeException
    {
        boolean added = canAddItem(item);

        if(added)
        {
            capacity += item.getSize();
            items.add(item);
        }

        return added;
    }

    public boolean canAddItem(Item item) throws ItemsSizeException
    {
        boolean canAdd = false;

        if(item.getSize() > maxCapacity)
        {
            throw new ItemsSizeException();
        }

        if(capacity + item.getSize() <= maxCapacity)
        {
            canAdd = true;
        }

        return canAdd;
    }

    public int getCapacity()
    {
        return capacity;
    }

    public List<Item> getItems()
    {
        return items;
    }

    @Override
    public String toString()
    {
        String string = "[" + capacity + ", {";

        for (int i = 0; i < items.size()-1; i++)
        {
            String itemId = "n°" + items.get(i).getId();
            string += itemId + ":" + items.get(i).getSize() + "  ";
        }

        string += "n°" + items.get(items.size() - 1).getId();
        string += ":" + items.get(items.size() - 1).getSize() + "}]";

        return string;
    }
}
