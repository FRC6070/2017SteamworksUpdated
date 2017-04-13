package org.usfirst.frc.team6070.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *	@author Stephen Guo
 */
public class StephenAutonomous extends CommandGroup {

	int driver;
    public StephenAutonomous() {
		driver = -1;
    	if (DriverStation.getInstance().getAlliance() == DriverStation.Alliance.Blue) {
    		driver = 1;
    	}
    	addSequential (new AutoDriveWithGyro (false, 0, 2.3)); // move forward
    	addSequential (new AutoGearing()); // initialize gear
    	addSequential (new AutoDrive (0.8, true)); // back away a bit
    	
    	/*  Turn 90 degrees left if you are on red
    	 *  90 degrees right if you are on blue
    	 */
    	addSequential (new AutoTurn(90*driver, 2));
    	addSequential ((new AutoDrive(2.2))); // clear the airship
    	addSequential (new AutoTurn(0, 2)); // turn to 0 degrees, facing the retrieval zone
    	addSequential (new AutoDrive(3)); // drive into the neutral zone, as far as possible without getting penalties
    	
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
