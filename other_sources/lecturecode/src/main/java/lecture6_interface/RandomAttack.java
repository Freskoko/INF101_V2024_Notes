package lecture6_interface;

import java.util.Random;

public class RandomAttack implements IAttack{
	
	Random rnd;

	
	public RandomAttack() {
		rnd = new Random();
	}
	
	@Override
	public void attack(IPokemon target) {
		int dmg = rnd.nextInt(10);
		target.damage(dmg);		
	}

}
