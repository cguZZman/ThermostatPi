package mx.daro.thermostatpi;

import java.util.prefs.Preferences;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ThermostatpiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ThermostatpiApplication.class, args);
	}
	
	@Bean
	Preferences preferences(){
		return Preferences.userNodeForPackage(ThermostatpiApplication.class);
	}
}
