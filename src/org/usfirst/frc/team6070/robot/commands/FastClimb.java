package org.usfirst.frc.team6070.robot.commands;

import org.usfirst.frc.team6070.robot.*;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class FastClimb extends Command {

    public FastClimb() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.climber);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (OI.xbox.getAButton())
    	{
    		Robot.climber.climb(1);
    	}
    	else if (OI.xbox.getBButton())
    	{
    		Robot.climber.climb(2);
    	}
    	else if (OI.xbox.getXButton())
    	{
    		Robot.climber.climb(-1);
    	}
    	else
    	{
    		Robot.climber.climb(0);
    	}
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
