package org.example.temperature.manager.configuration;

import org.apache.felix.ipojo.annotations.Bind;
import org.apache.felix.ipojo.annotations.Component;
import org.apache.felix.ipojo.annotations.Instantiate;
import org.apache.felix.ipojo.annotations.Invalidate;
import org.apache.felix.ipojo.annotations.Provides;
import org.apache.felix.ipojo.annotations.Requires;
import org.apache.felix.ipojo.annotations.Unbind;
import org.apache.felix.ipojo.annotations.Validate;
import org.example.temperature.configuration.TemperatureConfiguration;
import org.example.temperature.manager.administration.TemperatureManagerAdministration;

import java.util.Map;



@Component
//Create an instance of the component
@Instantiate(name = "TemperatureManager-1")
@Provides(specifications= TemperatureManagerAdministration.class)
public class TemperatureManagerImpl implements TemperatureManagerAdministration{

	
	@Requires(optional=true)
	/** Field for TemperatureConfiguration dependency */
	private TemperatureConfiguration[] TemperatureConfiguration;

	@Bind
	/** Bind Method for TemperatureConfiguration dependency */
	public void bindTemperatureConfiguration(TemperatureConfiguration temperatureConfiguration, Map properties) {
		// TODO: Add your implementation code here
	}

	@Unbind
	/** Unbind Method for TemperatureConfiguration dependency */
	public void unbindTemperatureConfiguration(TemperatureConfiguration temperatureConfiguration, Map properties) {
		// TODO: Add your implementation code here
	}

	@Invalidate
	/** Component Lifecycle Method */
	public void stop() {
		// TODO: Add your implementation code here
	}

	@Validate
	/** Component Lifecycle Method */
	public void start() {
		// TODO: Add your implementation code here
	}

	@Override
	public void temperatureIsTooHigh(String roomName) {
		float currentTemp= TemperatureConfiguration[0].getTargetedTemperature(roomName);
		TemperatureConfiguration[0].setTargetedTemperature(roomName, currentTemp-5);
		while ( TemperatureConfiguration[0].getTargetedTemperature(roomName) >= currentTemp -5){
			try {
				wait(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void temperatureIsTooLow(String roomName) {
		float currentTemp= TemperatureConfiguration[0].getTargetedTemperature(roomName);
		TemperatureConfiguration[0].setTargetedTemperature(roomName, currentTemp+5);
		while ( TemperatureConfiguration[0].getTargetedTemperature(roomName) <= currentTemp +5){
			try {
				wait(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
}
