package mx.daro.thermostatpi.controller;

import java.io.IOException;
import java.util.List;
import java.util.prefs.Preferences;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import mx.daro.thermostatpi.model.Thermometer;
import mx.daro.thermostatpi.model.Thermostat;
import mx.daro.thermostatpi.util.Constants;

@RestController
@RequestMapping("/thermostat")
public class ThermostatController {
	
	private Preferences preferences;
	private ThermometerController thermometerController;
	
	@RequestMapping(method=RequestMethod.GET)
	public Thermostat read() throws IOException{
		Thermostat thermostat = new Thermostat();
		thermostat.setFan(preferences.get(Constants.PREFERENCE_THERMOSTAT_FAN, Constants.STATUS_AUTO));
		thermostat.setSystem(preferences.get(Constants.PREFERENCE_THERMOSTAT_SYSTEM, Constants.STATUS_OFF));
		thermostat.setTemperature(preferences.getDouble(Constants.PREFERENCE_THERMOSTAT_TEMPERATURE, Constants.DEFAULT_TEMPERATURE));
		String scale = preferences.get(Constants.PREFERENCE_THERMOSTAT_SCALE, Constants.SCALE_CELSIUS);
		List<Thermometer> thermometers = thermometerController.list();
		if (thermometers != null && !thermometers.isEmpty()) {
			Thermometer thermometer = thermometers.get(0);
			thermometer.setValue(thermometerController.getTemperature(thermometer.getValue(), scale));
			thermometer.setScale(scale);
			thermostat.setThermometer(thermometer);
		}
		return thermostat;
	}
	
	@Autowired
	public void setPreferences(Preferences preferences) {
		this.preferences = preferences;
	}
	
	@Autowired
	public void setThermometerController(ThermometerController thermometerController) {
		this.thermometerController = thermometerController;
	}
}
