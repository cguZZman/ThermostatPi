package mx.daro.thermostatpi.task;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.prefs.Preferences;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import mx.daro.thermostatpi.util.Constants;

@Component
public class ThermometerTask {

	private Preferences preferences;
	
	@Scheduled(fixedDelay = 15000)
	public void thermometerTask(){
		File deviceFolder = new File(Constants.SYSTEM_FILE_DEVICES_DIR);
		StringBuffer sb = new StringBuffer();
		if (deviceFolder.exists()) {
			for (File sensorFolder : deviceFolder.listFiles((dir, name) -> name.startsWith(Constants.SYSTEM_FILE_THERMOMETER_ID_FILTER))){
				try {
					Double temperature = readTemperature(sensorFolder);
					if (temperature != null) {
						if (sb.length() > 0) {
							sb.append(Constants.COMMA);
						}
						sb.append(sensorFolder.getName());
						sb.append(Constants.EQUALS);
						sb.append(temperature);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
		}
		preferences.put(Constants.PREFERENCE_THERMOMETER_LIST, sb.toString());
	}
	
	public Double readTemperature(File sensorFolder) throws IOException {
		Double temperature = null;
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(new File(sensorFolder, Constants.SYSTEM_FILE_THERMOMETER_FILENAME)));
			String line = null;
			int lineNumber = 1;
			while ((line = br.readLine()) != null) {
				if (lineNumber == 1) {
					if (!line.endsWith(Constants.YES)) break;
				} else if (lineNumber == 2) {
					int index = line.indexOf(Constants.SYSTEM_FILE_THERMOMETER_STATUS_TOKEN);
					if (index > -1) {
						temperature = Double.valueOf(line.substring(index+2))/1000.0;
					}
					break;
				}
				lineNumber++;
			}
		} catch (Exception e) {
			throw e;
		} finally {
			if (br != null) {
				br.close();
			}
		}
		return temperature;
	}
	
	@Autowired
	public void setPreferences(Preferences preferences) {
		this.preferences = preferences;
	}
}
