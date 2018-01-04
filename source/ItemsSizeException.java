public class ItemsSizeException extends Exception
{
    public ItemsSizeException()
    {
        super("the item size is greater then the bin size !!!");
    }
}
