package imdb;

public class Roles {
	private Integer actor_id;
	private Integer movie_id;
	private String role;
	Oid _id;
	public Roles() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Roles(Integer actor_id, Integer movie_id, String role) {
		super();
		this.actor_id = actor_id;
		this.movie_id = movie_id;
		this.role = role;
	}
	public Integer getActor_id() {
		return actor_id;
	}
	public Oid get_id() {
		return _id;
	}
	public void set_id(Oid _id) {
		this._id = _id;
	}
	public void setActor_id(Integer actor_id) {
		this.actor_id = actor_id;
	}
	public Integer getMovie_id() {
		return movie_id;
	}
	public void setMovie_id(Integer movie_id) {
		this.movie_id = movie_id;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}

}
