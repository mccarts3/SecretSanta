package secretsanta;

public class Person {
	private String name;
	private String email;
	private String secretSanta;
	
	public Person(String name, String email) {
		this.name = name;
		this.email = email;
		this.secretSanta = null;
	}
	
	public Person(String name, String email, String secretSanta) {
		this.name = name;
		this.email = email;
		this.secretSanta = secretSanta;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setSecretSanta(String secretSanta) {
		this.secretSanta = secretSanta;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public String getSecretSanta() {
		return this.secretSanta;
	}
}
