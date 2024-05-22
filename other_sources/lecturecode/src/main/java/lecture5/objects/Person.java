package lecture5.objects;

public class Person {

	int age;
	String name;
	
	//Konstruktør brukes til å lage nye objekter.
	Person(String nameInput){
		this.age = 0;
		this.name = nameInput;
	}

	Person(String name,int age){
		this.age = age;
		this.name = name;
	}
	
	@Override
	public String toString() {
		return name+" is "+age+" years";
	}
	
	void growOlder() {
		age = age+1;
	}
}
