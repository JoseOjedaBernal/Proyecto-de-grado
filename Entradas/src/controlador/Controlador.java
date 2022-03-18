package controlador;
import com.github.strikerx3.jxinput.*;
import com.github.strikerx3.jxinput.enums.*;
import com.github.strikerx3.jxinput.exceptions.XInputNotLoadedException;


public class Controlador {
int s = 1;

	public Controlador() {
		
		try {
		XInputDevice14 device = XInputDevice14.getDeviceFor(0);
		if (device.poll()) {
		    // Retrieve the delta
		    XInputComponents components = device.getComponents();

		    
		    XInputAxes axes = components.getAxes();

		    // Buttons and axes have public fields (although this is not idiomatic Java)

		    // Retrieve button state
	

		    // Check if Guide button is supported


		    // Retrieve axis state
		    while(device.poll()) {

		    float acceleration = axes.lx;
		    float brake = axes.ly; 
		    
		    System.out.println(acceleration +" " +brake);
		    }
		} else {
		    // Controller is not connected; display a message
			System.out.print("nope");
		}
	}
		catch(XInputNotLoadedException e){
			
		}
		
	}
	
	
	
}
