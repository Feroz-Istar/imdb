package imdb;

public class DirectorsGenres {
	private Integer director_id;
	private String genre;
	private Float prob;
	Oid _id;
	public DirectorsGenres() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DirectorsGenres(Integer director_id, String genre, Float prob) {
		super();
		this.director_id = director_id;
		this.genre = genre;
		this.prob = prob;
	}
	public Integer getDirector_id() {
		return director_id;
	}
	public void setDirector_id(Integer director_id) {
		this.director_id = director_id;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public Float getProb() {
		return prob;
	}
	public void setProb(Float prob) {
		this.prob = prob;
	}
	public Oid get_id() {
		return _id;
	}
	public void set_id(Oid _id) {
		this._id = _id;
	}
	
	
}
