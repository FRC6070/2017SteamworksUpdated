package org.usfirst.frc.team6070.robot.commands;

import org.usfirst.frc.team6070.robot.OI;
import org.usfirst.frc.team6070.robot.Robot;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class startDriving extends Command {

	int control = 1;
	// 1 is arcadeDrive, 0 is tankDrive, 2 is Nafeh-drive
	int thing = 0;
	int reverse = 1;
	double slow = 1;
    public startDriving() {
    	requires (Robot.DriveBase);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (OI.xbox.getTriggerAxis(Hand.kRight) > 0.8)
    	{
    		reverse = -1;
    	}
    	else
    	{
    		reverse = 1;
    	}
    	if (OI.xbox.getTriggerAxis(Hand.kLeft) > 0.8)
    	{
    		slow = 0.6;
    	}
    	else
    	{
    		slow = 1;
    	}
    	if (OI.driveYleft() > 0.1)
    	{
    		thing = 1;
    	}
    	else if (OI.driveYleft() < -0.1)
    	{
    		thing = -1;
    	}
    	else
    	{
    		thing = 0;
    	}
    	if (control == 0)
    	{
    		Robot.DriveBase.drive(OI.driveYleft()*reverse*slow, OI.driveYright()*reverse*slow, true);
    	}
    	else if (control == 1)
    	{
    		Robot.DriveBase.drive(OI.driveYright()*reverse*slow, OI.driveX()*slow);
    	}
    	else if (control == 2)
    	{
    		Robot.DriveBase.drive(thing * OI.xbox.getTriggerAxis(Hand.kRight), OI.xbox.getX(Hand.kRight));
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
