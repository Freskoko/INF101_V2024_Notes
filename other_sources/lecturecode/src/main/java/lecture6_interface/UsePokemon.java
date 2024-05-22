package lecture6_interface;

public class UsePokemon {

	
	public static void main(String[] args) {
		Pokemon mew = new Pokemon("Mew", 6, new RandomAttack());
		
		Pokemon snorLax = new Pokemon("SnorLax", 12, new SleepAttack());
		snorLax.healthPoints=1000;
		
		IPokemon attacker = snorLax;
		IPokemon defender = new FirePokemon("CharMander", 12);
	}
}
