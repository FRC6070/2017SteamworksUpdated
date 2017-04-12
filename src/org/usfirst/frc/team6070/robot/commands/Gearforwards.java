package org.usfirst.frc.team6070.robot.commands;

import org.usfirst.frc.team6070.robot.OI;
import org.usfirst.frc.team6070.robot.Robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Gearforwards extends Command {
	
    public Gearforwards() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires (Robot.gear);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (OI.right.getY() < -0.8)
    	{
    		Robot.gear.forwards();
    	}
    	else if (OI.right.getY() < -0.4)
    	{
    		Robot.gear.foreslow();
    	}
    	else if (OI.right.getY() > 0.4 && !Robot.gear.isfullback())
    	{
    		Robot.gear.backslow();
    	}
    	else
    	{
    		Robot.gear.stop();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.gear.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
