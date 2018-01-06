
import java.io.File;
import java.io.IOException;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
public class Chart {
    public void createChar(List<Node> algos,int simulation){
        DefaultCategoryDataset datasetbin = new DefaultCategoryDataset();
        DefaultCategoryDataset datasettime = new DefaultCategoryDataset();
        String titre = "simulation n° "+simulation;
        String name = "simulations/graphs/simulation_time_"+simulation+".jpeg";
        String name2 = "simulations/graphs/simulation_bins_"+simulation+".jpeg";
        if (simulation == 0){
            titre = "global simulation";
            name = "simulations/graphs/global_simulation_time.jpeg";
            name2 = "simulations/graphs/global_simulation_bins.jpeg";
        }
        for(Node node : algos)
        {
            datasetbin.setValue(node.numberOfBins, "numberOfBins", node.algorithm.getName());
            datasettime.setValue(node.time , "time", node.algorithm.getName());
        }
        JFreeChart chartbin = ChartFactory.createBarChart(titre,
                "algos", "number of bins", datasetbin, PlotOrientation.VERTICAL,
                false, true, false);
        JFreeChart charttime = ChartFactory.createBarChart(titre,
                "algos", "time (Ns)", datasettime, PlotOrientation.VERTICAL,
                false, true, false);
        try {
            ChartUtilities.saveChartAsJPEG(new File(name), charttime, 500, 300);
            ChartUtilities.saveChartAsJPEG(new File(name2), chartbin, 500, 300);
        } catch (IOException e) {
            System.err.println("Problem occurred creating chart.");

        }
    }


}
