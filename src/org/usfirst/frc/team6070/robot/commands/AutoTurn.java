package org.usfirst.frc.team6070.robot.commands;

import org.usfirst.frc.team6070.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoTurn extends Command {

	double angle;
	boolean done = false;
	Timer mytime;
    public AutoTurn(double angle) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.DriveBase);
    	this.angle = angle;
    	mytime = new Timer();
    	mytime.reset();
    	mytime.start();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//Robot.DriveBase.resetGyro();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.DriveBase.setdirection();
    	//Robot.DriveBase.resetGyro();
    	Robot.DriveBase.turnPID(angle, 0.6);
    	//Robot.DriveBase.resetAccel();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return done || (mytime.get() > 3);
    }

    // Called once after isFinished returns true
    protected void end() {
    	//Robot.DriveBase.resetGyro();
    	Robot.DriveBase.accelPID.resetPID();
    	Robot.DriveBase.gyroPID.resetPID();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
