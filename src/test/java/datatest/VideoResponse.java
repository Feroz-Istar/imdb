package datatest;


public class VideoResponse {
	private Integer id;
	private String url;
	private String large_url;
	private Integer source_id;
	private String copyright;
	private String site;
	
	
	
	public VideoResponse() {
		super();
		// TODO Auto-generated constructor stub
	}



	public VideoResponse(Integer id, String url, String large_url, Integer source_id, String copyright, String site) {
		super();
		this.id = id;
		this.url = url;
		this.large_url = large_url;
		this.source_id = source_id;
		this.copyright = copyright;
		this.site = site;
	}



	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public String getUrl() {
		return url;
	}



	public void setUrl(String url) {
		this.url = url;
	}



	public String getLarge_url() {
		return large_url;
	}



	public void setLarge_url(String large_url) {
		this.large_url = large_url;
	}



	public Integer getSource_id() {
		return source_id;
	}



	public void setSource_id(Integer source_id) {
		this.source_id = source_id;
	}



	public String getCopyright() {
		return copyright;
	}



	public void setCopyright(String copyright) {
		this.copyright = copyright;
	}



	public String getSite() {
		return site;
	}



	public void setSite(String site) {
		this.site = site;
	}
	
	
	

}