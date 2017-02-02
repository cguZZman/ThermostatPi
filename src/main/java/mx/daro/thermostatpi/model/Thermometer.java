package mx.daro.thermostatpi.model;

public class Thermometer extends Device {

	private String id;
	private double value;
	private String scale;
	
	public Thermometer(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public String getScale() {
		return scale;
	}

	public void setScale(String scale) {
		this.scale = scale;
	}

	
}
