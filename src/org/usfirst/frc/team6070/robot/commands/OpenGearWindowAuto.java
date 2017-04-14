package org.usfirst.frc.team6070.robot.commands;

import org.usfirst.frc.team6070.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class OpenGearWindowAuto extends Command {
	double timeOut;
	
	public OpenGearWindowAuto(double timeOut) {
		requires(Robot.gearwindow);
		this.timeOut = timeOut;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	setTimeout(timeOut);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.gearwindow.OpenGearWindow(0.3);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
