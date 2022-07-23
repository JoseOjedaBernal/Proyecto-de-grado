package interfaz;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.*;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.xy.XYDataItem;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import controlador.Controlador;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class ScatterAdd3 extends JFrame {

	private static int stop = 0;
    private static final int N = 8;
    private static final int SIZE = 345;
    private static final String title = "Scatter Add Demo";
    private static final Random rand = new Random();
    public final static XYSeries series = new XYSeries("Original");
    private final XYSeries added = new XYSeries("Moved");
    FileWriter fw;

    public ScatterAdd3(String s) throws Exception{
        super(s);

	     //the true will append the new data
		    
        final ChartPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(SIZE, SIZE));
        this.add(chartPanel, BorderLayout.CENTER);
        JPanel control = new JPanel();
        this.add(control, BorderLayout.SOUTH);
            
    }

    private ChartPanel createDemoPanel() {
        JFreeChart jfreechart = ChartFactory.createScatterPlot(
            title, "X", "Y", createSampleData(),
            PlotOrientation.VERTICAL, true, true, false);
        XYPlot xyPlot = (XYPlot) jfreechart.getPlot();
        xyPlot.setDomainCrosshairVisible(true);
        xyPlot.setRangeCrosshairVisible(true);
        XYItemRenderer renderer = xyPlot.getRenderer();
        renderer.setSeriesPaint(0, Color.blue);
        adjustAxis((NumberAxis) xyPlot.getDomainAxis(), true);
        adjustAxis((NumberAxis) xyPlot.getRangeAxis(), false);
        xyPlot.setBackgroundPaint(Color.white);
        ChartPanel chartPanel = new ChartPanel(jfreechart);
        chartPanel.addChartMouseListener(new ChartMouseListener() {

			@Override
			public void chartMouseClicked(ChartMouseEvent event) {
				// TODO Auto-generated method stub
				Point2D p = chartPanel.translateScreenToJava2D(event.getTrigger().getPoint());
				Rectangle2D plotArea = chartPanel.getScreenDataArea();
				JFreeChart jfreechart = ChartFactory.createScatterPlot(
			            title, "X", "Y", createSampleData(),
			            PlotOrientation.VERTICAL, true, true, false);
			        
				
				double chartX = xyPlot.getDomainAxis().java2DToValue(p.getX(), plotArea, xyPlot.getDomainAxisEdge());
				double chartY = xyPlot.getRangeAxis().java2DToValue(p.getY(), plotArea, xyPlot.getRangeAxisEdge());
				
				try {
					fw = new FileWriter("iris.arff",true);
					fw.write(chartX+","+chartY+","+"SI\n");
					fw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				series.add(chartX,chartY);
				System.out.println(chartX + " " +chartY);
				
			}

			@Override
			public void chartMouseMoved(ChartMouseEvent event) {
				// TODO Auto-generated method stub
				
			}
        	
        });
        return chartPanel;
    }

    private void adjustAxis(NumberAxis axis, boolean vertical) {
        axis.setRange(-1.0, 1.0);
        axis.setTickUnit(new NumberTickUnit(0.1));
        axis.setVerticalTickLabels(vertical);
    }

    private XYDataset createSampleData() {
        XYSeriesCollection xySeriesCollection = new XYSeriesCollection();
        
        xySeriesCollection.addSeries(series);
        return xySeriesCollection;
    }
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		ejecutar();
		
	}
    public static void ejecutar() {
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run(){
                ScatterAdd3 demo = null;
				try {
					demo = new ScatterAdd3(title);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                demo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                demo.pack();
                demo.setLocationRelativeTo(null);
                demo.setVisible(true);
               
                 
            }
        });
        
    
    }
}
