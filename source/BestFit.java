import java.util.ArrayList;
import java.util.List;

public class BestFit extends AbstractBP
{
    public BestFit(int binSize, List<Item> items)
    {
        super(binSize, items);
    }

    private Bin avlTreeBestFit(AVLTree3<Bin> avlTree, Item item){
        Bin best = null;
        boolean found = false;
        AVLTreeNode<Bin> node = avlTree.root;
        while (node != null && !found){
            int result = Integer.compare(node.getKey().getFreeSpace(), item.getSize());
            //System.out.println("free : " + node.getKey().getFreeSpace() + " item : " + item.getSize() + " result : " + result);
            if(result > 0){
                if(best == null || Integer.compare(node.getKey().getFreeSpace(), best.getFreeSpace()) < 0){
                    best = node.getKey();
                }
                node = node.getLeft();
            }else if(result < 0){
                node = node.getRight();
            }
            else {
                best = node.getKey();
                found = true;
            }
        }
        return best;
    }

    @Override
    public void run() throws Exception
    {
        reset(binSize, items);

        long debut = System.nanoTime();

        bins = new ArrayList<>();
        AVLTree3<Bin> tree = new AVLTree3<>();
        for(Item item :items){
            Bin b = avlTreeBestFit(tree, item);
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
        time = (System.nanoTime()-debut)/NSTOMS;
    }
}