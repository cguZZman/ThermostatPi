package mx.daro.thermostatpi.model;

public class Thermostat {
	private Thermometer thermometer;
	private double temperature;
	private String fan;
	private String system;
	
	public Thermometer getThermometer() {
		return thermometer;
	}
	public void setThermometer(Thermometer thermometer) {
		this.thermometer = thermometer;
	}
	public double getTemperature() {
		return temperature;
	}
	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}
	public String getFan() {
		return fan;
	}
	public void setFan(String fan) {
		this.fan = fan;
	}
	public String getSystem() {
		return system;
	}
	public void setSystem(String system) {
		this.system = system;
	}
	
}
