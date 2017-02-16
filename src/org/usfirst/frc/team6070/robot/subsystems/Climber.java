package org.usfirst.frc.team6070.robot.subsystems;

import org.usfirst.frc.team6070.robot.RobotMap;
import org.usfirst.frc.team6070.robot.commands.FastClimb;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Climber extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	Spark climber1 = new Spark(RobotMap.climber1);
	Spark climber2 = new Spark(RobotMap.climber2);

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	//setDefaultCommand(new FastClimb(0));
    	setDefaultCommand(new FastClimb());
    }
    
    public void climb(int level)
    {
    	if (level == 1)
    	{
    		climber1.set(-0.3);
    		climber2.set(-0.3);
    	}
    	else if (level == 2)
    	{
    		climber1.set(-0.5);
    		climber2.set(-0.5);
    	}
    	else if (level == -1)
    	{
    		climber1.set(0.3);
    		climber2.set(0.3);
    	}
    	else
    	{
    		climber1.set(0);
    		climber2.set(0);
    	}
    }
}

