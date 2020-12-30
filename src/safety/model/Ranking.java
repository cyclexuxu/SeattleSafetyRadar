package safety.model;

public class Ranking {

	private double rating;
	private String mcpp;
	private int ranking;
	

	public Ranking(double rating, String mcpp, int ranking) {
		this.ranking = ranking;
		this.rating = rating;
		this.mcpp = mcpp;
	}

	public int getRanking() {
		return ranking;
	}

	public void setRanking(int ranking) {
		this.ranking = ranking;
	}
	
	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public String getMcpp() {
		return mcpp;
	}

	public void setMcpp(String mcpp) {
		this.mcpp = mcpp;
	}
	
	
}
