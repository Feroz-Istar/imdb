package imdb;

public class Actors {
	private Integer id;
	private String first_name;
	private String last_name;
	private String gender;
	Oid _id;
	public Actors() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Actors(Integer id, String first_name, String last_name, String gender) {
		super();
		this.id = id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.gender = gender;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Oid get_id() {
		return _id;
	}
	public void set_id(Oid _id) {
		this._id = _id;
	}
		
}
