package lecture_19_inheritance;

/**
 * Class for storing various types of clothing;
 * @author mva021
 * 
 * This type of Object mainly stores information with brand, size and type being examples.
 * Such variables are called field variables or instance variables.
 * The collection of field variables is called the state of the Object
 * These variables all should have get methods, and could have set methods if you want to be able to change the property.
 * Brand typically don't change, unless you buy cheap clothing and replace the labels with proper brands in order to earn some extra bucks.
 * The important part of a State is those field variables that can change.
 * 
 * Which field variables can change in an object of this class?
 */
public class Clothing {
	private String brand;
	private int size;
	private boolean isDirty;
	private String type;

	/**
	 * Constructor is called once for each time you want to make a new clothing item.
	 * Ideally all properties are set to reasonable default values in the constructor.
	 * In lecture we did not get the time to set all properties properly.
	 * @param brand
	 * @param size
	 * @param type
	 */
	public Clothing(String brand, int size, String type) {
		this.brand = brand;
		this.size = size;
		//should probably check that input is a valid type.
		//in a bigger application we should probably make type an Object on its own 
		//so we could have a better control that it was a proper clothingType rather than any string
		this.type = type; 
		this.isDirty = false; //assume new clothing are clean?
	}

	public String getType() {
		return this.type;
	}
	
	public String getBrand() {
		return brand;
	}

	public int getSize() {
		return this.size;
	}

	/**
	 * Method called by a "Person" object when jumpingInMuddyPuddle() method is activated
	 * Changes the state of this object, i.e. the field variable isDirty
	 */
	public void makeDirty(){
		isDirty = true;
	}

	/**
	 * Method called by e.g. the "WashingMachine" object or "Maid" object
	 * Changes the state of this object, i.e. the field variable isDirty
	 */
	public void makeClean(){
		isDirty = false;
	}
	
	/**
	 * Method used by the drawers, shelves e.g. to check if the item can be placed
	 * @return
	 */
	public boolean isDirty() {
		return isDirty;
	}
	
	/**
	 * Method called by the "WashingMachine" object
	 * This is a method that changes the state of the Object by changing the field variable size.
	 */
	public void shrink() {
		size = size-1;
	}
}
