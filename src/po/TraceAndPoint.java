package po;

public class TraceAndPoint extends Trace {
	private String lat;
	private String lng;

	public TraceAndPoint() {
		super();
		lat = "0";
		lng = "0";
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

}
