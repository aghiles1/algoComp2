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

    public int getFreeSpace(){
        return maxCapacity - capacity;
    }

    public List<Item> getItems()
    {
        return items;
    }

    @Override
    public String toString()
    {
        String string = "[" + capacity + ", {";

        for (Item item : items)
        {
            string += "n°" + item.getId() + ":" + item.getSize() + " ";
        }

        string += "}]";

        return string;
    }
}