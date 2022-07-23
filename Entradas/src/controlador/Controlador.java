package controlador;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import com.github.strikerx3.jxinput.*;
import com.github.strikerx3.jxinput.enums.*;
import com.github.strikerx3.jxinput.exceptions.XInputNotLoadedException;
import interfaz.ScatterAdd;
import interfaz.ScatterAdd2;
import interfaz.TestRedirect;
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
	ScatterAdd2 n2 = new ScatterAdd2("titulo 2");	
	TestRedirect t = new TestRedirect();
	n.ejecutar();
	n2.ejecutar();
	

	while (g.getDevice().poll()) {
		    n.series.clear();
		    n2.series.clear();
		       
	        n.series.add(g.getX(),g.getY());
	        if(cp.predict(g.getX(),g.getY()).equals("NO")) {
	        n2.series.add(g.getX(),g.getY());	
	        }else{
	        n2.series.add(0,0);	
	        };
	        try {
				TimeUnit.MILLISECONDS.sleep(10);;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
		
	}
	
	
	
}
