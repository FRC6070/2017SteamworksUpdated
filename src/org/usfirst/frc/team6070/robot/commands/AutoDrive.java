package org.usfirst.frc.team6070.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team6070.robot.*;

/**
 *
 */
public class AutoDrive extends Command {
	
	Timer mytimer;
	double timeout;
	boolean backwards = false;
	boolean withenc = false;
	double dist = 0;
    public AutoDrive(double timeout) {
    	//requires (Robot.DriveBase);
    	this.timeout = timeout;
    	
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }
    public AutoDrive(double timeout, boolean backwards)
    {
    	this.timeout = timeout;
    	this.backwards = true;
    }
    
    public AutoDrive(double dist, double timeout, boolean backwards)
    {
    	this.timeout = timeout;
    	this.dist = dist;
    	this.backwards = backwards;
    	withenc = true;
    }

    // Called just before this Command runs the first time
    protected void initialize() 
    {
    	setTimeout(timeout);
    	Robot.DriveBase.resetEncoders();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (withenc)
    	{
    		Robot.DriveBase.driveStraightDist(dist, 0);
    	}
    	else
    	{
    		Robot.DriveBase.driveStraight(backwards);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.DriveBase.stop();
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}