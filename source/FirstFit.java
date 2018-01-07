import java.util.ArrayList;
import java.util.List;

public class FirstFit extends AbstractBP
{
    public FirstFit(int binSize, List<Item> items)
    {
        super(binSize, items);
    }

    private Bin getFirstBin(Item item) throws ItemsSizeException
    {
        for(int i = 0; i < bins.size(); i++)
        {
            if(bins.get(i).canAddItem(item))
            {
                return bins.get(i);
            }
        }

        Bin bin = new Bin(binSize);
        bins.add(bin);

        return bin;
    }

    private Bin avlTreeFirstFit(AVLTree3<Bin> avlTree, Item item){
        Bin found = null;

        AVLTreeNode<Bin> node = avlTree.root;
        while(found == null && node != null){
            int rv = Integer.compare(node.getKey().getFreeSpace(), item.getSize());
            if(rv >= 0){
                found = node.getKey();
            }else {
                node = node.getRight();
            }
        }
        return found;
    }

    @Override
    public void run() throws Exception
    {
        reset(binSize, items);

        long debut = System.nanoTime();

        bins = new ArrayList<>();
        AVLTree3<Bin> tree = new AVLTree3<>();

        for(Item item : items){
            Bin b = avlTreeFirstFit(tree, item);
            if(b != null){
                tree.delete(b);
                b.addItem(item);
                tree.insert(b);
            }else {
                b = new Bin(binSize);
                b.addItem(item);
                bins.add(b);
                tree.insert(b);
            }
        }
/*
        for(int i = 0; i < items.size(); i++)
        {
            Item item = items.get(i);
            Bin firstBin = getFirstBin(item);
            firstBin.addItem(item);
        }*/


        time = (System.nanoTime() - debut)/NSTOMS;
    }
}