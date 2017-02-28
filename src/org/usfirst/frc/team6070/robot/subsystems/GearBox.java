package org.usfirst.frc.team6070.robot.subsystems;

import org.usfirst.frc.team6070.robot.OI;
import org.usfirst.frc.team6070.robot.RobotMap;
import org.usfirst.frc.team6070.robot.commands.Gearforwards;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Potentiometer;

/**
 *
 */
public class GearBox extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public DigitalInput digi = new DigitalInput(0);
	Talon gearing = new Talon(RobotMap.gearmanip);
	//Potentiometer pot = new AnalogPotentiometer(1, 360, 30);

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new Gearforwards());
    }
    public void forwards()
    {
    	gearing.set(0.7);
    }
    public void backwards()
    {
    	gearing.set(-0.7);
    }
    public void foreslow()
    {
    	gearing.set(0.3);
    }
    public void backslow()
    {
    	gearing.set(-0.3);
    }
    public void stop()
    {
    	gearing.set(0);
    }
//    public double getPot()
//    {
//    	return pot.get();
//    }
    public boolean isfullback()
    {
    	try 
    	{
    		if (OI.right.getThrottle() > 0.8)
	    	{
	    		return true;
	    	}
	    	else
	    	{
	    		return digi.get();
	    	}
    	}
    	catch(Exception e)
    	{
    		return digi.get();
    	}
    }
}

