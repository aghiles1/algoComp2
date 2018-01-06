import java.util.ArrayList;
import java.util.List;

public class Sort
{
    private List<Node> nodes;

    public Sort(List<Node> nodes)
    {
        this.nodes = nodes;
    }

    private void swap(List<Node> list, int i, int j)
    {
        Node nodeI = list.get(i);
        list.set(i, list.get(j));
        list.set(j, nodeI);
    }

    public List<Node> getByTime()
    {
        List<Node> result = new ArrayList<>(nodes);

        if(!result.isEmpty())
        {
            for(int i = 0; i < result.size() - 1; i++)
            {
                int min = i;

                for(int j = i + 1; j < result.size(); j++)
                {
                    if(result.get(j).time < result.get(min).time)
                    {
                        min = j;
                    }
                }

                if(min != i)
                {
                    swap(result, i, min);
                }
            }
        }

        return result;
    }

    public List<Node> getByEffectiveness()
    {
        List<Node> result = new ArrayList<>(nodes);

        if(!result.isEmpty())
        {
            for(int i = 0; i < result.size() - 1; i++)
            {
                int min = i;

                for(int j = i + 1; j < result.size(); j++)
                {
                    if(result.get(j).numberOfBins < result.get(min).numberOfBins)
                    {
                        min = j;
                    }
                }

                if(min != i)
                {
                    swap(result, i, min);
                }
            }
        }

        return result;
    }
}
