package lecture6_interface;

public class SleepAttack implements IAttack{

	public SleepAttack() {
	}
	
	@Override
	public void attack(IPokemon target) {
		target.damage(100);
		
	}

}
