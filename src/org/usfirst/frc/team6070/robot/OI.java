package org.usfirst.frc.team6070.robot;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import org.usfirst.frc.team6070.robot.commands.*;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
	public static XboxController xbox;
	//public static XboxController xboxTest;
	
	public static Joystick right;
	Button button;
	//Button button1;
	//Button button2;
	
	
	
	public OI () {
		xbox = new XboxController(0);
		
		//Test
		//xboxTest = new XboxController(3);
		
		right = new Joystick(1);
		
		
		button = new JoystickButton(right, 2);
		
		button.whenPressed(new AutoGearing());
		//button1 = new JoystickButton(xbox, 10);
		//button2 = new JoystickButton(xbox, 9);
		//button1.whenPressed(new AutoDrive(2));
	//	button2.whenPressed(new AutoTurn(0, 2));
		//slowclimb.whenPressed(new FastClimb(1));
		//.slowclimb.whenReleased(new FastClimb(0));
		
	}
	
	public static double driveX (){
		if (Math.abs(xbox.getX()) <= 0.1){
			return 0;
			
		} else {
			return xbox.getX()*Math.abs(xbox.getX());
		}
		
		
	}
	
	public static double driveYleft (){
		if (Math.abs(xbox.getY(Hand.kLeft)) <= 0.1){
			return 0;
			
		} else {
			return xbox.getY(Hand.kLeft)*Math.abs(xbox.getY(Hand.kLeft));
		}
		
	}
	public static double driveYright()
	{
		if (Math.abs(xbox.getY(Hand.kRight)) <= 0.1){
			return 0;
			
		} else {
			return xbox.getY(Hand.kRight)*Math.abs(xbox.getY(Hand.kRight));
		}
	}
	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());
}
