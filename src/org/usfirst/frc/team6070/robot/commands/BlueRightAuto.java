package org.usfirst.frc.team6070.robot.commands;

import org.usfirst.frc.team6070.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Right side auto for Blue alliance
 */
public class BlueRightAuto extends CommandGroup {

    public BlueRightAuto() {
    	addSequential(new AutoDrive(105.5, RobotMap.forwardval, 0.0, false)); // Nafeh changed this 5:16 pm; old = 106.0
//    	if (driver){
    		addSequential(new AutoTurn(RobotMap.turnangle, 2));
        	addSequential (new AutoDrive(1));
        	//Gear
        	
        	addSequential(new AutoGearing());
        	
        	addParallel(new AutoGearingBackSlow(0.8)); // Nafeh added this. TIGER CHECK THIS.
        	
        	addSequential (new AutoDrive(1, true));
        	addSequential(new AutoTurn(50, 2));
        	addSequential(new AutoDrive(1.5));
        	addSequential(new AutoTurn(0, 2));
        	addSequential(new AutoDrive(3));
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
