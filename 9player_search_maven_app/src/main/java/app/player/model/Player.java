package app.player.model;

public class Player 
{
	private int id;
	private String name;
	private String teamName;
	private long contact;
	private String gender;
	private int age;
	
	public Player() {
	//default constructor
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

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public long getContact() {
		return contact;
	}

	public void setContact(long contact) {
		this.contact = contact;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Player(int id, String name, int age, String gender, String teamName, long contact) {
		super();
		this.id = id;
		this.name = name;
		this.teamName = teamName;
		this.contact = contact;
		this.gender = gender;
		this.age = age;
	}

	@Override
	public String toString() {
		return "Player [id=" + id + ", name=" + name + ", age=" + age + 
				", gender="	+ gender + ", teamName=" + teamName + ", contact=" + contact + "]";
	}
}
