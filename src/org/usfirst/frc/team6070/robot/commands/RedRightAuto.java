package org.usfirst.frc.team6070.robot.commands;

import org.usfirst.frc.team6070.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Right side auto for Red
 */
public class RedRightAuto extends CommandGroup {

    public RedRightAuto() {
    	addSequential(new AutoDrive(107.0, RobotMap.forwardval, 0.0, false));
//    	if (driver){
    		addSequential(new AutoTurn(-RobotMap.turnangle, 2));
    		// Gear Window
    		//addParallel(new OpenGearWindowAuto(1)); // Nafeh added this, to pull the window up. Pls check and comment out if necessary
        	
    		// Move forward
    		addSequential (new AutoDrive(1));
        	
    		//Gear
          	addSequential(new AutoGearing());
          	addParallel(new AutoGearingBackSlow(3.25)); // Nafeh added this. TIGER CHECK THIS.
        	addSequential (new AutoDrive(1, true));

        	addSequential(new AutoTurn(0, 2));
        	addSequential(new AutoDrive(5));
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
