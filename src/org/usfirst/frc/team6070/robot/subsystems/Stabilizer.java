package org.usfirst.frc.team6070.robot.subsystems;

import org.usfirst.frc.team6070.robot.RobotMap;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team6070.robot.commands.*;
/**
 *
 */
public class Stabilizer extends Subsystem {
	
	Spark climber2 = new Spark(RobotMap.climber1);

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new OpenGearWindow());
    }
    
    public void moveWindow(double speed){
    	climber2.set(speed);
    }
    

    
    
}

