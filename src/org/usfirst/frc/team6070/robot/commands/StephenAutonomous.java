package org.usfirst.frc.team6070.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class StephenAutonomous extends CommandGroup {

	boolean driver;
    public StephenAutonomous() {
		driver = false;
    	if (DriverStation.getInstance().getAlliance() == DriverStation.Alliance.Blue) {
    		driver = true;
    	}
    	//Okay I see, so use the if statement to add a set of commands one way, and another 
    	//yep
    	addSequential (new AutoDrive (2.4));
    	//addSequential (new AutoGearing());
    	//addSequential (new AutoDrive (2));
    	
    	if (driver){
    		addSequential (new AutoTurn(90, 2)); 
    		addSequential ((new AutoDrive(4)));
    		addSequential (new AutoTurn(0, 2));
    		addSequential (new AutoDrive(10));
    		
    		
    	}
    	else {
    		addSequential (new AutoTurn (-90,2)); //
    		addSequential (new AutoDrive(4));
    		addSequential (new AutoTurn (0, 2));
    		addSequential (new AutoDrive(10));
    		
    		
    		}
    	
    	// Go to right lift, deliver gear, back up, turn until parallel to airship, 
    	//move forward to clear airship, turn so that you are facing retrieval zone
    	// move as far into neutral zone as possible without crossing into opposing launchpad
    	// approach the lift perpendicular to it, program centerlift
    	// Got it 
    
    	
       	// Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}
