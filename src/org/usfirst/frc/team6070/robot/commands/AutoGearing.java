package org.usfirst.frc.team6070.robot.commands;

import org.usfirst.frc.team6070.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoGearing extends Command {

	Timer mytimer;
	boolean done;
    public AutoGearing() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.gear);
    	this.done = false;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	mytimer = new Timer();
    	mytimer.reset();
    	mytimer.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (mytimer.get() < 0.6)
    	{
    		Robot.DriveBase.drive(0.4, 0);
    		//Robot.gear.forwards();
    	}
    	else if (mytimer.get() < 1.0)
    	{
    		Robot.DriveBase.stop();
    		Robot.gear.forwards();
    	}
    	else if (mytimer.get() < 1.8)
    	{
    		Robot.DriveBase.drive(-0.4, 0);
    	}
//    	else if (mytimer.get() < 2.5 && !Robot.gear.isfullback())
//    	{
//    		Robot.gear.backwards();
//    	}
    	else
    	{
    		done = true;
    	}	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return done;
    }

    // Called once after isFinished returns true
    protected void end() {
    	mytimer.stop();
    	mytimer.reset();
    	Robot.gear.stop();
    	done = false;
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
