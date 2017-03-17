package org.usfirst.frc.team6070.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team6070.robot.*;

/**
 *
 */
public class AutoDrive extends Command {
	double dist;
	boolean done = false;
	Timer mytimer;
	double timeout;
	double timeprev;
	double timenow;
    public AutoDrive(double dist, double timeout) {
    	requires (Robot.DriveBase);
    	this.dist = dist;
    	mytimer = new Timer();
    	mytimer.reset();
    	mytimer.start();
    	this.timeout = timeout;
    	timeprev = 0;
    	timenow = 0;
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() 
    {
    	Robot.DriveBase.setdirection();
    	Robot.DriveBase.getAccelPID();
    	Robot.DriveBase.getGyroPID();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.DriveBase.driveStraight(dist);
    	timenow = mytimer.get();
    	Robot.DriveBase.updateaccel(timeprev, timenow);
    	timeprev = timenow;
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return mytimer.get() > timeout; //|| OI.xbox.getStickButton(Hand.kLeft);
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.DriveBase.stop();
    	Robot.DriveBase.resetAccel();
    	Robot.DriveBase.accelPID.resetPID();
    	Robot.DriveBase.gyroPID.resetPID();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
