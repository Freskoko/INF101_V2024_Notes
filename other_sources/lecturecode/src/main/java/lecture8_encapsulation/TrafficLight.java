package lecture8_encapsulation;

public class TrafficLight {

	private boolean red;
	private boolean yellow;
	private boolean green;
	
	public TrafficLight() {
		this(true,false,false);
	}
	
	public TrafficLight(boolean red, boolean yellow, boolean green) {
		if(!isValid(red, yellow, green)) {
			throw new IllegalArgumentException("Can not construct traffic light  with illegal state Red: "+red+" Yellow: "+yellow+" Green: "+green);
		}
		this.red = red;
		this.yellow = yellow;
		this.green = green;
	}

	static boolean isValid(boolean red, boolean yellow, boolean green) {
		if(green && (red || yellow)) {
			return false;
		}
		
		if(!red && !yellow && !green) {
			return false;
		}
		
		return true;

	}
	
	public boolean isRed() {
		return red;
	}
	public boolean isYellow() {
		return yellow;
	}
	public boolean isGreen() {
		return green;
	}
	
	public void next() {
		if(red) {
			if(!yellow) { //case 1
				yellow = true;
			}
			else { //case 2
				red=false;
				yellow=false;
				green=true;
			}
		}else {
			if(green) { //case 3
				green = false;
				yellow=true;
			}else { //case 4
				yellow=false;
				red=true;
			}
		}
	}
	
	
	
	
	
}
