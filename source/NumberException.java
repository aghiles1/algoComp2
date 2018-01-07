public class NumberException extends Exception{
    public NumberException(String s)
    {
        super("you should use " + s + "!!!");
    }
}
