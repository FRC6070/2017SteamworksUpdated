package org.usfirst.frc.team6070.robot.commands;

import org.usfirst.frc.team6070.robot.Robot;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team6070.robot.*;
import org.usfirst.frc.team6070.robot.subsystems.*;

/**
 *
 */
public class OpenGearWindow extends Command {

    public OpenGearWindow() {
        // Use requires() here to declare subsystem dependencies
         requires(Robot.gearwindow);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (OI.right.getRawButton(4)) {
    		Robot.gearwindow.moveWindow(0.3);
    	}
    	else if (OI.right.getRawButton(5))
    	{
    		Robot.gearwindow.moveWindow(-0.3);
    	}
    	else
    	{
    		Robot.gearwindow.moveWindow(0);
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
