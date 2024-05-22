package lecture6_interface;

public class Pokemon implements IPokemon{
	String name;
	int healthPoints;
	int strength;
	IAttack attack;
	
    // Constructor
	Pokemon(String name, int strength, IAttack attack){
		this.name = name;
		this.healthPoints = 100;
		this.strength = strength;
		this.attack = attack;
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

    /**
     * Attack another pokémon. The method conducts an attack by <code>this</code>
     * on <code>target</code>. Calculate the damage using the pokémons strength
     * and a random element. Reduce <code>target</code>s health.
     * 
     * If <code>target</code> has 0 HP then print that it was defeated.
     * 
     * @param target pokémon that is being attacked
     */
    public void attack(IPokemon target) {
    	attack.attack(target);
    }

    @Override
    public String toString() {
        return name+" has "+healthPoints+" left."; 
    }
    
    

}
