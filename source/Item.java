public class Item
{
    private int size;
    private int id;

    public Item(int size, int id)
    {
        this.size = size;
        this.id = id;
    }

    public int getSize()
    {
        return size;
    }

    public int getId()
    {
        return id;
    }

    @Override
    public String toString()
    {
        return "Item n°" + id + " : " + size;
    }
}