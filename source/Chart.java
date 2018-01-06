
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
public class Chart {
    public void createChar(List<Node> algos,int simulation){
        DefaultCategoryDataset datasetbin = new DefaultCategoryDataset();
        DefaultCategoryDataset datasettime = new DefaultCategoryDataset();
        for(Node node : algos)
        {
            datasetbin.setValue(node.numberOfBins, "numberOfBins", node.algorithm.getName());
            datasettime.setValue(node.time , "time", node.algorithm.getName());
        }
        JFreeChart chartbin = ChartFactory.createBarChart("simulation n° "+simulation,
                "algos", "number of bins", datasetbin, PlotOrientation.VERTICAL,
                false, true, false);
        JFreeChart charttime = ChartFactory.createBarChart("simulation n° "+simulation,
                "algos", "time (Ns)", datasettime, PlotOrientation.VERTICAL,
                false, true, false);
        try {
            ChartUtilities.saveChartAsJPEG(new File("simulations/simulation_time_"+simulation+".jpeg"), charttime, 500, 300);
            ChartUtilities.saveChartAsJPEG(new File("simulations/simulation_bin_"+simulation+".jpeg"), chartbin, 500, 300);
        } catch (IOException e) {
            System.err.println("Problem occurred creating chart.");

        }
    }


}
