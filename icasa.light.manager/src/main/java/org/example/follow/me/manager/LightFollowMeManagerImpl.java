package org.example.follow.me.manager;


import java.util.Map;

import org.apache.felix.ipojo.annotations.Bind;
import org.apache.felix.ipojo.annotations.Component;
import org.apache.felix.ipojo.annotations.Instantiate;
import org.apache.felix.ipojo.annotations.Invalidate;
import org.apache.felix.ipojo.annotations.Provides;
import org.apache.felix.ipojo.annotations.Requires;
import org.apache.felix.ipojo.annotations.Unbind;
import org.apache.felix.ipojo.annotations.Validate;
import org.example.follow.me.api.EnergyGoal;
import org.example.follow.me.api.FollowMeAdministration;
import org.example.follow.me.api.FollowMeConfiguration;
import org.example.follow.me.api.IlluminanceGoal;
import org.example.follow.me.api.TemperatureManagerAdministration;
import org.example.follow.me.api.TemperatureConfiguration;

 
//Define this class as an implementation of a component :
@Component
//Create an instance of the component
@Instantiate(name = "FollowMeManager-1")
@Provides(specifications= {FollowMeAdministration.class , TemperatureManagerAdministration.class})
public class LightFollowMeManagerImpl implements FollowMeAdministration, TemperatureManagerAdministration {

	@Requires(optional=true)
	/** Field for followMeConfiguration dependency */
	private FollowMeConfiguration[] followMeConfiguration;

	@Requires
	private FollowMeAdministration m_administrationService;
	
	@Bind
	/** Bind Method for followMeConfiguration dependency */
	public void bindFollowMeConfiguration(FollowMeConfiguration followMeConfiguration, Map properties) {
	}

	@Unbind
	/** Unbind Method for followMeConfiguration dependency */
	public void unbindFollowMeConfiguration(FollowMeConfiguration followMeConfiguration, Map properties) {
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
		System.out.println("start manager....\n");
	}

	@Override
	public void setIlluminancePreference(IlluminanceGoal illuminanceGoal) {
		followMeConfiguration[0].setMaximumNumberOfLightsToTurnOn(illuminanceGoal.getNumberOfLightsToTurnOn());
	}

	@Override
	public IlluminanceGoal getIlluminancePreference() {
		switch (followMeConfiguration[0].getMaximumNumberOfLightsToTurnOn()){
		case 1 :
			return IlluminanceGoal.SOFT;
		case 2 :
			return IlluminanceGoal.MEDIUM;
		case 3 :
			return IlluminanceGoal.FULL;
		default:
			return null;
		}
	}

	public void setEnergySavingGoal(EnergyGoal energyGoal) {
		followMeConfiguration[0].setMaximumAllowedEnergyInRoom(energyGoal.getMaximumEnergyInRoom());
		
	}


	public EnergyGoal getEnergyGoal() {
		switch ((int)followMeConfiguration[0].getMaximumAllowedEnergyInRoom()){
		case 100 :
			return EnergyGoal.LOW;
		case 200 :
			return EnergyGoal.MEDIUM;
		case 300 :
			return EnergyGoal.HIGH;
		default:
			return null;
		}
	}
	
	
	//////////////////////////////////// Temperature
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
