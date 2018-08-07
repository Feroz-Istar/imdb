package imdb;

public class Movies {
	private Integer id;
	private String name;
	private String year;
	private Float rank;
	public Movies() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Movies(Integer id, String name, String year, Float rank) {
		super();
		this.id = id;
		this.name = name;
		this.year = year;
		this.rank = rank;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public Float getRank() {
		return rank;
	}
	public void setRank(Float rank) {
		this.rank = rank;
	}

	
}
