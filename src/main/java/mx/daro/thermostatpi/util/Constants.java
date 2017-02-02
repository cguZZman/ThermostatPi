package mx.daro.thermostatpi.util;

public class Constants {
	public static final String COMMA = ",";
	public static final String EQUALS = "=";
	public static final String YES = "YES";
	public static final String EMPTY = "";
	
	public static final String STATUS_ON = "on";
	public static final String STATUS_OFF = "off";
	public static final String STATUS_AUTO = "auto";
	public static final String STATUS_NOT_FOUND = "not_found";
	
	public static final String SCALE_CELSIUS = "C";
	public static final String SCALE_FAHRENHEIT = "F";
	public static final String SCALE_KELVIN= "K";
	
	public static final double DEFAULT_TEMPERATURE = 23;
	
	public static final String SYSTEM_FILE_DEVICES_DIR = "/sys/bus/w1/devices/";
	public static final String SYSTEM_FILE_THERMOMETER_ID_FILTER = "28-";
	public static final String SYSTEM_FILE_THERMOMETER_FILENAME = "w1_slave";
	public static final String SYSTEM_FILE_THERMOMETER_STATUS_TOKEN = "t=";
	
	public static final String PREFERENCE_THERMOMETER_LIST = "thermometer.list";
	public static final String PREFERENCE_THERMOSTAT_FAN = "thermostat.fan";
	public static final String PREFERENCE_THERMOSTAT_SYSTEM = "thermostat.system";
	public static final String PREFERENCE_THERMOSTAT_TEMPERATURE = "thermostat.temperature";
	public static final String PREFERENCE_THERMOSTAT_SCALE = "thermostat.scale";
}
