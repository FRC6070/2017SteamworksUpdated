package org.usfirst.frc.team6070.robot.commands;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team6070.robot.*;

/**
 *
 */
public class AutoDrive extends Command {
	double dist;
	boolean done = false;
    public AutoDrive(double dist) {
    	//requires (Robot.DriveBase);
    	this.dist = dist;
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() 
    {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.DriveBase.setdirection();
    	Robot.DriveBase.drivestraight(dist);
    	Robot.DriveBase.resetAccel();
    	done = true;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return done; //|| OI.xbox.getStickButton(Hand.kLeft);
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
