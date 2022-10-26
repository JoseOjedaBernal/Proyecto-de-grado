package controlador;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import com.github.strikerx3.jxinput.*;
import com.github.strikerx3.jxinput.enums.*;
import com.github.strikerx3.jxinput.exceptions.XInputNotLoadedException;
import interfaz.ScatterAdd;
import interfaz.ScatterAdd2;
import weka.core.converters.ConverterUtils.DataSource;
import java.io.FileWriter;
import java.io.IOException;
import modelo.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import login.*;


public class Controlador implements ActionListener{
int stop = 0;
FileWriter fw;
panel_inicial vista;
panel_entrenamiento Panel_Entrenamiento;
panel_prueba Panel_Prueba;
private Controlador ejecutarEventos;
ClassificationPrediction cp;
private static final Random rand = new Random();

	public Controlador() throws Exception {
            vista = new panel_inicial(this);
            Panel_Entrenamiento = new panel_entrenamiento(this);
            Panel_Prueba= new panel_prueba(this);
            vista.setVisible(true);
            Panel_Entrenamiento.setVisible(false);
            Panel_Prueba.setVisible(false);
            cp = new ClassificationPrediction();
            cp.ini();
            Gamepad g = new Gamepad();
            while (g.getDevice().poll()) {
                Panel_Prueba.series.clear();
                Panel_Prueba.added.clear();
                Panel_Prueba.added.add(g.getX(),g.getY());
	        if(cp.predict(g.getX(),g.getY()).equals("NO")) {
	        Panel_Prueba.series.add(g.getX(),g.getY());	
	        }else{
	        Panel_Prueba.series.add(0,0);	
	        };
                try {
				TimeUnit.MILLISECONDS.sleep(20);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            }
		
	}
        
    public void escucharEventos() {
        panel_inicial.LearnProgram.addActionListener(this);
        panel_inicial.DeletData.addActionListener(this);
        panel_inicial.TurnUpCorrection.addActionListener(this);
        panel_entrenamiento.Entrenar.addActionListener(this);
        panel_entrenamiento.Limpiar.addActionListener(this);
        panel_entrenamiento.Regresar.addActionListener(this);
        panel_prueba.VOLVER.addActionListener(this);
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e){
       if(e.getSource()==panel_inicial.LearnProgram ||e.getSource()==panel_inicial.TurnUpCorrection||e.getSource()==panel_inicial.DeletData ){
        if (e.getSource()== panel_inicial.LearnProgram){
            vista.setVisible(false);
            Panel_Entrenamiento.setVisible(true);
        }
        else if (e.getSource()== panel_inicial.TurnUpCorrection) {
            vista.setVisible(false);
            Panel_Prueba.setVisible(true);
        }
        else if (e.getSource()== panel_inicial.DeletData) {
            
        }
       }
       if(e.getSource()==panel_entrenamiento.Entrenar || e.getSource()==panel_entrenamiento.Limpiar || e.getSource()==panel_entrenamiento.Regresar){
        if(e.getSource()==panel_entrenamiento.Entrenar){
            try {
                JOptionPane.showMessageDialog(Panel_Prueba, "Por favor Reinicie el programa para que se puedan agregar los cambios");
                System.exit(0);
            } catch (Exception ex) {
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            }


        }
        else if (e.getSource()==panel_entrenamiento.Limpiar) {
               Panel_Entrenamiento.series.clear();
               Panel_Entrenamiento.added.clear();
               try {
                 fw = new FileWriter("iris.arff");

                 fw.write("@RELATION iris \n" +
                            "\n" +
                            "@ATTRIBUTE X	NUMERIC\n" +
                            "@ATTRIBUTE Y 	NUMERIC\n" +
                            "@ATTRIBUTE Drift 	{SI,NO}\n" +
                            "\n" +
                            "@DATA"+"\n");
                 fw.close();
                 
            } catch (IOException i) {
                // TODO Auto-generated catch block
		i.printStackTrace();
            }
               
        }
        else if (e.getSource()==panel_entrenamiento.Regresar) {
            Panel_Entrenamiento.setVisible(false);
            vista.setVisible(true);

        }
       }
        if (e.getSource()== Panel_Prueba.VOLVER) {
            Panel_Prueba.setVisible(false);
            vista.setVisible(true);
           
        }
    }
	
	
	
}
