package controlador;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import com.github.strikerx3.jxinput.*;
import com.github.strikerx3.jxinput.enums.*;
import com.github.strikerx3.jxinput.exceptions.XInputNotLoadedException;
import interfaz.ScatterAdd;
import modelo.ClassificationPrediction;
import modelo.Gamepad;

public class Controlador {
int stop = 0;
private static final Random rand = new Random();

	public Controlador() throws Exception {
	ClassificationPrediction cp = new ClassificationPrediction();
	cp.ini();

	Gamepad g = new Gamepad();
	ScatterAdd n = new ScatterAdd("titulo");	
	n.ejecutar();
	

	while (g.getDevice().poll()) {
		    n.series.clear();
		       
	        n.series.add(g.getX(),g.getY());
	        cp.predict(g.getX(),g.getY());
	        try {
				TimeUnit.MILLISECONDS.sleep(10);;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
		
	}
	
	
	
}
