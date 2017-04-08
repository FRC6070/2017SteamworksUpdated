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
    	/*if (OI.right.getRawButton(6))
    	{
    		Robot.climber.climbWithSpeed(0.25);
    	}
    	else if (OI.right.getRawButton(7))
    	{
    		Robot.climber.climb(2);
    	}
    	else if (OI.xbox.getYButton())
    	{
    		Robot.climber.climb(3);
    	}
    	else if (OI.xbox.getXButton())
    	{
    		Robot.climber.climb(4); // Change back to -1, currently for Ball test
    	}
    	else
    	{
    		Robot.climber.climb(0);
    	}*/
    	
    	Robot.climber.climbWithSpeed(-(OI.right.getRawAxis(2)+1)/2);
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
