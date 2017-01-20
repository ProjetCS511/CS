package org.example.follow.me.manager.impl;

import org.example.binary.follow.me.configuration.FollowMeConfiguration;
import org.example.follow.me.manager.EnergyGoal;
import org.example.follow.me.manager.FollowMeAdministration;
import org.example.follow.me.manager.IlluminanceGoal;

import java.util.Map;

public class FollowMeManagerImpl implements FollowMeAdministration {

	/** Field for followMeConfiguration dependency */
	private FollowMeConfiguration[] followMeConfiguration;

	/** Bind Method for followMeConfiguration dependency */
	public void bindFollowMeConfiguration(FollowMeConfiguration followMeConfiguration, Map properties) {
	}

	/** Unbind Method for followMeConfiguration dependency */
	public void unbindFollowMeConfiguration(FollowMeConfiguration followMeConfiguration, Map properties) {
		// TODO: Add your implementation code here
	}

	/** Component Lifecycle Method */
	public void stop() {
		// TODO: Add your implementation code here
	}

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

	@Override
	public void setEnergySavingGoal(EnergyGoal energyGoal) {
		followMeConfiguration[0].setMaximumAllowedEnergyInRoom(energyGoal.getMaximumEnergyInRoom());
		
	}

	@Override
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
}
