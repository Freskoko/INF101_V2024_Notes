package lecture6_interface;

public class FirePokemon implements IPokemon {

	String name;
	int healthPoints;
	int strength;
    
    // Constructor
	public FirePokemon(String name, int strength){
		this.name = name;
		this.healthPoints = 100;
		this.strength = strength;
	}

	/**
     * Get name of the pokémon
     * @return name of pokémon
     */
    public String getName() {
        return name;
    }

    /**
     * Get strength of the pokémon
     * @return strength of pokémon
     */
    public int getStrength() {
        return strength;
    }

    /**
     * Get current health points of pokémon
     * @return current HP of pokémon
     */
    public int getCurrentHP() {
        return healthPoints;
    }
    
    /**
     * Check if the pokémon is alive. 
     * A pokemon is alive if current HP is higher than 0
     * @return true if current HP > 0, false if not
     */
    public boolean isAlive() {
        return healthPoints>0;
    }

    /**
     * Damage the pokémon. This method reduces the number of
     * health points the pokémon has by <code>damageTaken</code>.
     * If <code>damageTaken</code> is higher than the number of current
     * health points then set current HP to 0.
     *
     * @param damageTaken
     */
    public void damage(int damageTaken) {
        healthPoints -= damageTaken;
        if(healthPoints<0)
        	healthPoints = 0;
    }


	@Override
	public void attack(IPokemon target) {
		int dmg = this.getStrength()*target.getStrength();
		target.damage(dmg);
		
	}


}
