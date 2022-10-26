package login;
import controlador.Controlador;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.net.URL;
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

public class panel_prueba extends JFrame {
    Controlador controlador;
	private static int stop = 0;
    private static final int N = 8;
    private static final int SIZE = 345;
    private static final String title = "Scatter Add Demo";
    private static final Random rand = new Random();
    public final static XYSeries series = new XYSeries("deriva corregida");
    public final static XYSeries added = new XYSeries("deriva");

    public final static XYSeries seriescorrect = new XYSeries("DriftCorrected");


    public panel_prueba(Controlador vista) {
        controlador=vista;
        initComponents();
        this.setLocationRelativeTo(null);

    }

     private void initComponents() {

        BG = new javax.swing.JPanel();
        VOLVER = new javax.swing.JButton();
        BLUE = new javax.swing.JLabel();
        WHITE = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocationByPlatform(true);
        setUndecorated(true);
        setResizable(false);

        BG.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ChartPanel chartPanel1 = createDemoPanelDrift();
        chartPanel1.setPreferredSize(new Dimension(SIZE, SIZE));
        BG.add(chartPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 60, 350, 350));

        ChartPanel chartPanel2 = createDemoPanelCorrect();
        chartPanel2.setPreferredSize(new Dimension(SIZE, SIZE));
        BG.add(chartPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 350, 350));

        VOLVER.setBackground(new java.awt.Color(0, 153, 204));
        VOLVER.setFont(new java.awt.Font("Roboto Black", 0, 12)); // NOI18N
        VOLVER.setForeground(new java.awt.Color(255, 255, 255));
        VOLVER.setText("VOLVER");
        VOLVER.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        VOLVER.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        VOLVER.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VOLVERActionPerformed(evt);
            }
        });
        BG.add(VOLVER, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 220, 110, 50));
        URL url7 = panel_inicial.class.getResource("/img/99.jpg");
        BLUE.setIcon(new javax.swing.ImageIcon(url7)); // NOI18N
        BG.add(BLUE, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 860, 250));
        URL url8 = panel_inicial.class.getResource("/img/white.jpg");
        WHITE.setIcon(new javax.swing.ImageIcon(url8)); // NOI18N
        BG.add(WHITE, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 246, 860, 210));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(BG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(BG, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }
     
        private void VOLVERActionPerformed(java.awt.event.ActionEvent evt) {                                       
        // TODO add your handling code here:
        controlador.escucharEventos();
    } 
    
    private ChartPanel createDemoPanelDrift() {
        JFreeChart jfreechart = ChartFactory.createScatterPlot(
            "", "", "", createSampleDataDrift(),
            PlotOrientation.VERTICAL, true, true, false);
        XYPlot xyPlot = (XYPlot) jfreechart.getPlot();
        xyPlot.setDomainCrosshairVisible(true);
        xyPlot.setRangeCrosshairVisible(true);
        XYItemRenderer renderer = xyPlot.getRenderer();
        renderer.setSeriesPaint(0, Color.cyan);
        adjustAxis((NumberAxis) xyPlot.getDomainAxis(), true);
        adjustAxis((NumberAxis) xyPlot.getRangeAxis(), false);
        xyPlot.setBackgroundPaint(Color.white);
        ChartPanel chartPanel = new ChartPanel(jfreechart);
        chartPanel.setDomainZoomable(false);
        chartPanel.setRangeZoomable(false);
        return chartPanel;
    }
    private ChartPanel createDemoPanelCorrect() {
        JFreeChart jfreechart = ChartFactory.createScatterPlot(
            "", "", "", createSampleData(),
            PlotOrientation.VERTICAL, true, true, false);
        XYPlot xyPlot = (XYPlot) jfreechart.getPlot();
        xyPlot.setDomainCrosshairVisible(true);
        xyPlot.setRangeCrosshairVisible(true);
        XYItemRenderer renderer = xyPlot.getRenderer();
        renderer.setSeriesPaint(0, Color.green);
        adjustAxis((NumberAxis) xyPlot.getDomainAxis(), true);
        adjustAxis((NumberAxis) xyPlot.getRangeAxis(), false);
        xyPlot.setBackgroundPaint(Color.white);
        ChartPanel chartPanel = new ChartPanel(jfreechart);
        chartPanel.setDomainZoomable(false);
        chartPanel.setRangeZoomable(false);
        return chartPanel;
    }

    private void adjustAxis(NumberAxis axis, boolean vertical) {
        axis.setRange(-1.0, 1.0);
        axis.setTickUnit(new NumberTickUnit(0.1));
        axis.setVerticalTickLabels(vertical);
    }

    private XYDataset createSampleData() {
        XYSeriesCollection xySeriesCollection = new XYSeriesCollection();
        
        xySeriesCollection.addSeries(added);
        return xySeriesCollection;
    }
    
    private XYDataset createSampleDataDrift() {
        XYSeriesCollection xySeriesCollection = new XYSeriesCollection();
        
        xySeriesCollection.addSeries(series);
        return xySeriesCollection;
    }

    
    
    private javax.swing.JPanel BG;
    private javax.swing.JLabel BLUE;
    public static javax.swing.JButton VOLVER;
    private javax.swing.JLabel WHITE;
}
