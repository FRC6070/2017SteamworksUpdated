package org.usfirst.frc.team6070.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	
	public static int leftFront = 1;
	public static int leftBack = 0;
	public static int RightFront = 2;
	public static int RightBack = 3;
	
	public static int climber1 = 4;
	public static int climber2 = 5;
	
	public static int gearmanip = 6;
	
	public static int gearisthere = 7;
	
	public static double forwardval = 3;
	public static double turnangle = 60.0;
	
	public static double forwardthing = 105;
	
	// Disclaimer: The below variables are of team 1241, Theory6.
	
	public static final int driveWheelRadius = 3;//wheel radius in inches
	public static final int drivePulsePerRotation = 256; //encoder pulse per rotation
	public static final double driveGearRatio = 1/1; //ratio between wheel and encoder
	public static final double driveEncoderPulsePerRot = drivePulsePerRotation*driveGearRatio; //pulse per rotation * gear ratio
	public static final double driveEncoderDistPerTick =(Math.PI*2*driveWheelRadius)/driveEncoderPulsePerRot;
	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	// public static int leftMotor = 1;
	// public static int rightMotor = 2;

	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
}
