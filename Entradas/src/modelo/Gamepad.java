package modelo;

import java.util.concurrent.TimeUnit;

import com.github.strikerx3.jxinput.XInputAxes;
import com.github.strikerx3.jxinput.XInputComponents;
import com.github.strikerx3.jxinput.XInputDevice14;
import com.github.strikerx3.jxinput.exceptions.XInputNotLoadedException;



public class Gamepad {
	XInputComponents components;
    XInputAxes axes;
    XInputDevice14 device;
	public Gamepad() {
		try {
		device = XInputDevice14.getDeviceFor(0);
		
		if (device.poll()) {
		    components = device.getComponents();	  
		    axes = components.getAxes();
		} else {
			System.out.print("nope");
		}
	}
		catch(XInputNotLoadedException e){
			
		}	
		
	}
	
	public float getX() {
		return axes.lx;
	}
	public float getY() {
		return axes.ly;
	}
	public XInputDevice14 getDevice() {
		return device;
	}
	
}
