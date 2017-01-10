package org.example.follow.me.manager.command;

import java.util.Map;

import org.apache.felix.ipojo.annotations.Bind;
import org.apache.felix.ipojo.annotations.Component;
import org.apache.felix.ipojo.annotations.Instantiate;
import org.apache.felix.ipojo.annotations.Invalidate;
import org.apache.felix.ipojo.annotations.Requires;
import org.apache.felix.ipojo.annotations.Unbind;
import org.apache.felix.ipojo.annotations.Validate;
import org.example.follow.me.api.FollowMeAdministration;
import org.example.follow.me.api.IlluminanceGoal;
import org.example.follow.me.api.EnergyGoal;

import fr.liglab.adele.icasa.command.handler.Command;
import fr.liglab.adele.icasa.command.handler.CommandProvider;

//Define this class as an implementation of a component :
@Component
// Create an instance of the component
@Instantiate(name = "follow.me.mananger.command")
// Use the handler command and declare the command as a command provider. The
// namespace is used to prevent name collision.
@CommandProvider(namespace = "followme")
public class FollowMeManagerCommandImpl implements  FollowMeAdministration {

	/** Field for followMeCommand dependency */
	@Requires(optional=true)
	private FollowMeAdministration[] followMeCommand;

	/** Bind Method for followMeCommand dependency */
	@Bind
	public void bindFollowMeCommand(FollowMeAdministration followMeAdministration, Map properties) {
		// TODO: Add your implementation code here
	} 

	/** Unbind Method for followMeCommand dependency */
	@Unbind
	public void unbindFollowMeCommand(FollowMeAdministration followMeAdministration, Map properties) {
		// TODO: Add your implementation code here
	}
 
	/** Component Lifecycle Method */
	@Invalidate
	public void stop() {
		// TODO: Add your implementation code here
	}

	/** Component Lifecycle Method */
	@Validate
	public void start() {
		// TODO: Add your implementation code here
		System.out.println("start command\n");
	}

	// Declare a dependency to a FollowMeAdministration service
	@Requires
	private FollowMeAdministration m_administrationService;

	/**
	 * Felix shell command implementation to sets the illuminance preference.
	 *
	 * @param goal
	 *            the new illuminance preference ("SOFT", "MEDIUM", "FULL")
	 */

	// Each command should start with a @Command annotation
	@Command
	public void setIlluminancePreference(IlluminanceGoal goal) {
		// The targeted goal
		// IlluminanceGoal illuminanceGoal;

		// TODO : Here you have to convert the goal string into an illuminance
		// goal and fail if the entry is not "SOFT", "MEDIUM" or "HIGH"
		if (new String("SOFT").equals(goal)) {
			m_administrationService.setIlluminancePreference(IlluminanceGoal.SOFT);
		} else if (new String("MEDIUM").equals(goal)) {
			m_administrationService.setIlluminancePreference(IlluminanceGoal.MEDIUM);
		} else if (new String("HIGH").equals(goal)) {
			m_administrationService.setIlluminancePreference(IlluminanceGoal.FULL);
		} else {
			System.out.println("Incorrect command please retry\n");
		}

	}

	@Command
	public IlluminanceGoal getIlluminancePreference() {
		// TODO : implement the command that print the current value of the goal
		System.out.println("The illuminance goal is " + m_administrationService.getIlluminancePreference().toString()); // ...
		return m_administrationService.getIlluminancePreference();
	}
	
	// Each command should start with a @Command annotation
		@Command
		public void setEnergySavingGoal(EnergyGoal energyGoal) {
		
			if (new String("LOW").equals(energyGoal)) {
				m_administrationService.setEnergySavingGoal(EnergyGoal.LOW);
			} else if (new String("MEDIUM").equals(energyGoal)) {
				m_administrationService.setEnergySavingGoal(EnergyGoal.MEDIUM);
			} else if (new String("HIGH").equals(energyGoal)) {
				m_administrationService.setEnergySavingGoal(EnergyGoal.HIGH);
			} else {
				System.out.println("Incorrect command please retry\n");
			}
		}

		@Command
		public EnergyGoal getEnergyGoal() {
			// TODO : implement the command that print the current value of the goal
			System.out.println("The energy goal goal is " + m_administrationService.getEnergyGoal().toString()); // ...
			return m_administrationService.getEnergyGoal();
		}

	

	
}
