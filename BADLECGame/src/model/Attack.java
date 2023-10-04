package model;

public class Attack {
	private int id;
	private String name;
	private String type;
	private int damage;
	
	public Attack(int id, String name, String type, int damage) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.damage = damage;
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

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}
	
}
