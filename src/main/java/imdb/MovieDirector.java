package imdb;

public class MovieDirector {
	private Integer director_id;
	private Integer movie_id;
	Oid _id;
	public MovieDirector() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MovieDirector(Integer director_id, Integer movie_id) {
		super();
		this.director_id = director_id;
		this.movie_id = movie_id;
	}
	public Integer getDirector_id() {
		return director_id;
	}
	public void setDirector_id(Integer director_id) {
		this.director_id = director_id;
	}
	public Integer getMovie_id() {
		return movie_id;
	}
	public void setMovie_id(Integer movie_id) {
		this.movie_id = movie_id;
	}
	public Oid get_id() {
		return _id;
	}
	public void set_id(Oid _id) {
		this._id = _id;
	}

}
