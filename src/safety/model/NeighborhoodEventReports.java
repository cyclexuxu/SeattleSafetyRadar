package safety.model;

public class NeighborhoodEventReports {
	
	private String MCPP;
	private String mostFrequentEventType;
	private String leastFrequentEventType;
	
	public NeighborhoodEventReports(String mCPP, String mostFrequentEventType, String leastFrequentEventType) {
		this.MCPP = mCPP;
		this.mostFrequentEventType = mostFrequentEventType;
		this.leastFrequentEventType = leastFrequentEventType;
	}

	public String getMCPP() {
		return MCPP;
	}

	public void setMCPP(String mCPP) {
		MCPP = mCPP;
	}

	public String getMostFrequentEventType() {
		return mostFrequentEventType;
	}

	public void setMostFrequentEventType(String mostFrequentEventType) {
		this.mostFrequentEventType = mostFrequentEventType;
	}

	public String getLeastFrequentEventType() {
		return leastFrequentEventType;
	}

	public void setLeastFrequentEventType(String leastFrequentEventType) {
		this.leastFrequentEventType = leastFrequentEventType;
	}
	
	
	

}
