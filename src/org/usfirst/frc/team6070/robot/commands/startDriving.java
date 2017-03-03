package org.usfirst.frc.team6070.robot.commands;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.usfirst.frc.team6070.robot.OI;
import org.usfirst.frc.team6070.robot.Robot;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team6070.robot.gmfilewriter.GMFileWriter;

/**
 * Main Driving Class
 */
@SuppressWarnings("unused")
public class startDriving extends Command {

	double control; 
	// 1 is arcadeDrive, 0 is tankDrive, 2 is Nafeh-drive
	int thing = 0;
	int reverse = 1;
	double slow = 0.6;
	
	double inputLeft;
	double inputRight;
	
	double prevLeft = 0;
	double prevRight = 0;
	
	private static final double DELTA_LIMIT = 0.8;
	private static final double SPEED_UP_CONSTANT = 0.05;
	private static final double SLOW_DOWN_CONSTANT = 0.05;
	//public GMFileWriter gMFileWriter = new GMFileWriter();
	
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
    	control = 0;
    	if (OI.right.getTrigger())
    	{
    		Robot.DriveBase.resetAccel();
    	}
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
    		slow = 1;
    	}
    	else
    	{
    		slow = 0.6;
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
    		// Tank Drive
    		inputRight = -OI.driveYright()*slow;
			inputLeft = -OI.driveYleft()*slow;
			
			double deltaR = inputRight - prevRight;
			double deltaL = inputLeft - prevLeft;
			
			if (deltaR >= DELTA_LIMIT)
			{
				inputRight -= SLOW_DOWN_CONSTANT;
			}
			else if (-deltaR >= DELTA_LIMIT)
			{
				inputRight += SPEED_UP_CONSTANT;
			}
			
			if (deltaL >= DELTA_LIMIT)
			{
				inputLeft -= SLOW_DOWN_CONSTANT;
			}
			else if (-deltaL >= DELTA_LIMIT)
			{
				inputLeft += SPEED_UP_CONSTANT;
			}
			
			prevRight = inputRight;
			prevLeft = inputLeft;
			
    		if (reverse == -1)
    		{
    			Robot.DriveBase.drive(inputRight, inputLeft, true);
//    			ArrayList<Double> valueArray = new ArrayList<Double>();
//    			valueArray.add(oiDriveYRightDouble);
//    			valueArray.add(oiDriveYLeftDouble);
//    			Robot.arrayOfArrayVals.add(valueArray);
    		}
    		else
    		{
    			Robot.DriveBase.drive(inputLeft, inputRight, true);
    		}
    	}
    }
    
    void runArrayValsThread() {
    	new Thread(() -> {
    		while(!Thread.interrupted()) {
                
            }
    	}).start();
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
