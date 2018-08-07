package imdb;

public class MoviesGenres {
	private Integer movie_id;
	private String genre;
	public MoviesGenres() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MoviesGenres(Integer movie_id, String genre) {
		super();
		this.movie_id = movie_id;
		this.genre = genre;
	}
	public Integer getMovie_id() {
		return movie_id;
	}
	public void setMovie_id(Integer movie_id) {
		this.movie_id = movie_id;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}

}
