package org.usfirst.frc.team6070.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *	Western University Event Ball Auto (Defunct)
 */
public class BallsAuto extends CommandGroup {

    public BallsAuto() {
    	addSequential(new AutoDrive(0.6));
    	addSequential(new AutoTurn(45, 2));
    	addSequential(new AutoDrive(0.5, true));
    	addSequential(new DeliverBall(5, 0.4));
    	addSequential(new AutoDrive(0.6));
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
