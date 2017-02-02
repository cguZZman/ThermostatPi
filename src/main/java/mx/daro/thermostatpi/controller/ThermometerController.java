package mx.daro.thermostatpi.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.prefs.Preferences;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mx.daro.thermostatpi.model.Thermometer;
import mx.daro.thermostatpi.util.Constants;

@RestController
@RequestMapping("/thermometers")
public class ThermometerController {
	
	private Preferences preferences;
	
	@RequestMapping(method=RequestMethod.GET)
	public List<Thermometer> list(){
		List<Thermometer> list = new ArrayList<Thermometer>();
		String[] thermometerList = preferences.get(Constants.PREFERENCE_THERMOMETER_LIST, Constants.EMPTY).split(Constants.COMMA);
		for (String thermometerData : thermometerList) {
			String[] data = thermometerData.split(Constants.EQUALS);
			Thermometer thermometer = new Thermometer(data[0]);
			thermometer.setValue(getTemperature(data[1], Constants.SCALE_CELSIUS));
			thermometer.setStatus(Constants.STATUS_ON);
			list.add(thermometer);
		}
		return list;
	}
	
	@RequestMapping(path="/{id}", method=RequestMethod.GET)
	public Thermometer read(@PathVariable String id, @RequestParam(required=false) String scale) throws IOException {
		Thermometer thermometer = new Thermometer(id);
		if (scale == null) scale = Constants.SCALE_CELSIUS;
		thermometer.setScale(scale);
		String[] thermometerList = preferences.get(Constants.PREFERENCE_THERMOMETER_LIST, Constants.EMPTY).split(Constants.COMMA);
		if (thermometerList.length > 0) {
			for (String thermometerData : thermometerList) {
				String[] data = thermometerData.split(Constants.EQUALS);
				if (data[0].equals(id)){
					thermometer.setValue(getTemperature(data[1], scale));
					thermometer.setStatus(Constants.STATUS_ON);
				}
			}
		} else {
			thermometer.setStatus(Constants.STATUS_NOT_FOUND);
		}
		return thermometer;
	}
	
	public double getTemperature(String data, String scale) {
		return getTemperature(Double.valueOf(data), scale);
	}
	
	public double getTemperature(double value, String scale) {
		if (scale.equals(Constants.SCALE_FAHRENHEIT)) {
			value = value * 1.8 + 32.0;
		} else if (scale.equals(Constants.SCALE_KELVIN)) {
			value += 273.15;
		}
		return value;
	}
	
	@Autowired
	public void setPreferences(Preferences preferences) {
		this.preferences = preferences;
	}
}
