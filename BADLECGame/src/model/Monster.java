package model;

public class Monster {
	
	private int id;
	private String name;
	private String type;
	private int minDamage;
	private int maxDamage;
	private int health;
	
	
	public Monster(int id, String name, String type, int minDamage, int maxDamage, int health) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.minDamage = minDamage;
		this.maxDamage = maxDamage;
		this.health = health;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public int getMinDamage() {
		return minDamage;
	}


	public void setMinDamage(int minDamage) {
		this.minDamage = minDamage;
	}


	public int getMaxDamage() {
		return maxDamage;
	}


	public void setMaxDamage(int maxDamage) {
		this.maxDamage = maxDamage;
	}


	public int getHealth() {
		return health;
	}


	public void setHealth(int health) {
		this.health = health;
	}
	

	
	
}
