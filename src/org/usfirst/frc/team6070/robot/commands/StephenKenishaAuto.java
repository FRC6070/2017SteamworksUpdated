package org.usfirst.frc.team6070.robot.commands;

import org.usfirst.frc.team6070.robot.RobotMap;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *	Left Side Autonomous for Blue
 *	@author Stephen Guo
 *	@author Kenisha Arora
 */
public class StephenKenishaAuto extends CommandGroup {

	boolean driver;
    public StephenKenishaAuto() {
    	driver = false;
    	if (DriverStation.getInstance().getAlliance() == DriverStation.Alliance.Blue){
    		driver = true;
    	}
    	
    	//addSequential(new AutoDriveWithGyro(false, RobotMap.forwardval, 0.0)); // <--- wtf
    	addSequential(new AutoDrive(106.5, RobotMap.forwardval, 0.0, false));
//    	if (driver){
    		addSequential(new AutoTurn(RobotMap.turnangle, 2));
        	addSequential (new AutoDrive(1));
        	//Gear
        	
        	addSequential(new AutoGearing());
        	
        	addParallel(new AutoGearingBackSlow(3.25)); // Nafeh added this. TIGER CHECK THIS.
        	addSequential (new AutoDrive(1, true));
        	addSequential(new AutoTurn(-50, 2));
        	addSequential(new AutoDrive(1.5));
        	addSequential(new AutoTurn(0, 2));
        	addSequential(new AutoDrive(4.5));
   
    	//Red side (left), forward, turn, gear, back, turn netural zone
    	//Blue side (right) as it is mirrored
    	
    	
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