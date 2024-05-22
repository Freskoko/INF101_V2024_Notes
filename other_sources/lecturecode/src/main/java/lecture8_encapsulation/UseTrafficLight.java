package lecture8_encapsulation;

public class UseTrafficLight {

	public static void main(String[] args) {
		TrafficLight light = new TrafficLight();
		TrafficLightVisualizer viewer = new TrafficLightVisualizer();
		light.next();
		//light.green = true; //not allowed with private fields
		try {
			light = new TrafficLight(true,true,true);
		}catch(IllegalArgumentException e){
			System.out.println("You created an illegal traffic light, default value will be used.");
			light = new TrafficLight();
		}
		
		viewer.view(light);
//		try {
//			viewer.run();
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
	}

}
