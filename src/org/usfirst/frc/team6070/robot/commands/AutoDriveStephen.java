package org.usfirst.frc.team6070.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team6070.robot.Robot;

import edu.wpi.first.wpilibj.Timer;

/**
 *
 */
public class AutoDriveStephen extends Command {
	

	double timeout;
	Timer timer;

    public AutoDriveStephen(double timeout) {
    	
    	timer = new Timer ();
    	timer.reset();
    	timer.start();
    	this.timeout = timeout;
      
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.DriveBase.driveStraight();
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return timer.get() > timeout;
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
