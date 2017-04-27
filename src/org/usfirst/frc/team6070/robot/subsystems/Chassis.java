package org.usfirst.frc.team6070.robot.subsystems;
import org.usfirst.frc.team6070.robot.RobotMap;

import org.usfirst.frc.team6070.robot.commands.*;

//import com.analog.adis16448.frc.ADIS16448_IMU;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team6070.robot.theory6PID.PIDController;


/**
 *	This is the main drivetrain.
 *	@author Tiger Kong
 */
@SuppressWarnings("unused")
public class Chassis extends Subsystem {
	
	Victor LF = new Victor (RobotMap.leftFront);
	Victor LB = new Victor (RobotMap.leftBack);
	Victor RF = new Victor (RobotMap.RightFront);
	Victor RB = new Victor (RobotMap.RightBack);
	
	public RobotDrive drive = new RobotDrive (LF, LB, RF, RB);
		 
	Accelerometer accel = new BuiltInAccelerometer(Accelerometer.Range.k4G);
	double acc = 0;
	double vel = 0;
	public double dist = 0;
	double anglefix;
	
	double kpgyro = 0.1;
	double kigyro = 0.0;
	double kdgyro = 0.02;
	
	double kpaccel = 0.7;
	double kiaccel = 0.0;
	double kdaccel = 0.0;
	
	double kpdrive = 0.11;
	double kidrive = 0.0;
	double kddrive = 0.05;
	
	double kPTutorial = 0.3;
	
	
	// MARK: Encoders
	/*
	 * Wheel Diameter = 6 inches
	 * Circumference Formula = 2pir = dpi
	 * Wheel Circumference, theoretical = 6pi inches = 18.84955 inches
	 * Wheel Circumference, measured = 19 inches
	 */
	Encoder leftenc = new Encoder(3, 4, true, Encoder.EncodingType.k4X);
	Encoder rightenc = new Encoder(1, 2, false, Encoder.EncodingType.k4X);
	
	public PIDController gyroPID = new PIDController(kpgyro, kigyro, kdgyro);
	public PIDController accelPID = new PIDController(kpaccel, kiaccel, kdaccel);
	public PIDController drivePID = new PIDController(kpdrive, kidrive, kddrive);
	
//	ADIS16448_IMU gyro = new ADIS16448_IMU();
	ADXRS450_Gyro gyro = new ADXRS450_Gyro();
	//public ADIS16448_IMU imu = new ADIS16448_IMU();
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
    	drive.setInvertedMotor(MotorType.kFrontRight, true);
    	drive.setInvertedMotor(MotorType.kRearRight, true);
    	drive.setInvertedMotor(MotorType.kFrontLeft, true);
    	drive.setInvertedMotor(MotorType.kRearLeft, true);
    	gyro.reset();
    	//imu.reset();
    	anglefix = 0;
    	leftenc.setDistancePerPulse(RobotMap.driveEncoderDistPerTick);
    	rightenc.setDistancePerPulse(RobotMap.driveEncoderDistPerTick);
    	SmartDashboard.putNumber("Distpertick", RobotMap.driveEncoderDistPerTick);
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new startDriving());
    }
    
 
    public void drive (double y, double z){
    	drive.arcadeDrive(y, z);
    }
    public void drive (double y, double z, boolean a)
    {
    	drive.tankDrive(y, z);
    	
    }
    
    public void setdirection()
    {
    	anglefix = gyro.getAngle();
    	//anglefix = imu.getYaw();
    }
    
    public void getGyroPID()
    {
    	kpgyro = SmartDashboard.getNumber("kpval", 0.1);
    	kigyro = SmartDashboard.getNumber("kival", 0.0);
    	kdgyro = SmartDashboard.getNumber("kdval", 0.02);
    	gyroPID.changePIDGains(kpgyro, kigyro, kdgyro);
    }
    public void getAccelPID()
    {
    	kpaccel = SmartDashboard.getNumber("kpacc", 0.07);
    	kiaccel = SmartDashboard.getNumber("kiacc", 0.0);
    	kdaccel = SmartDashboard.getNumber("kdacc", 0.0);
    	accelPID.changePIDGains(kpaccel, kiaccel, kdaccel);
    }
    
    public void driveStraightWithGyro(boolean backwards, double angle)
    {
    	double mod = gyroPID.calcPID(angle, gyro.getAngle(), 0.5);
    	drive.arcadeDrive(-0.6, -mod);
    }
    
    public void driveStraightWithWPIlibGyro() {
    	// TODO: Gyro is reset; change angles in left and right autos.
    	double angle = gyro.getAngle();
    	drive.drive(-0.6, -angle*kPTutorial);
    }
    
    public void driveStraightDist(double distance, double angle)
    {
    	double strength = drivePID.calcPIDDrive(distance, getAvgDist(), 0.5);
    	
    	//double ang = gyroPID.calcPID(angle, gyro.getAngle(), 1);
    	drive.tankDrive(-0.7*strength, -0.7*strength);
    }
    
    public void driveStraight(boolean backwards)
    {
    	//double mod = gyroPID.calcPID(an, gyro.getAngle(), 0.5);
    	if (backwards)
    	{
    		drive.tankDrive(0.6, 0.6);
    	}
    	else
    	{
    		drive.tankDrive(-0.6, -0.6);
    	}
    }
   
    public void updateaccel(double t0, double t1)
    {
    	acc = Math.sqrt(Math.pow(accel.getX(), 2) + Math.pow(accel.getY(), 2))*32.2;
    	//System.out.println(acc);
    	vel += acc*(t1-t0);
    	dist += vel*(t1-t0);
    }
    
    public void stop (){
    	
    	drive.arcadeDrive(0, 0);
    }
    
    public void spin (){
    	
    	drive.arcadeDrive(0, 1);
    }
    public void turnPID(double angle, double speed)
    {
    	double ang = gyroPID.calcPID(angle%360, getGyroYaw()%360, 1);
    	drive.tankDrive(-speed*ang, speed*ang);
    }
    public void resetAccel()
    {
    	acc = 0;
    	vel = 0;
    	dist = 0;
    	anglefix = 0;
    }
    public void resetGyro()
    {
    	gyro.reset();
    	//imu.reset();
    }
    public double getAvgDist()
    {
    	return (getLeftEnc() +getRightEnc())/2;
    }
    
    public double getLeftEnc()
    {
    	return leftenc.getDistance();
    }
    public double getRightEnc()
    {
    	return rightenc.getDistance();
    }
    public void resetEncoders()
    {
    	leftenc.reset();
    	rightenc.reset();
    }
    public double getAccel()
    {
    	return accel.getX();
    }
    public double getGyroYaw()
    {
    	return gyro.getAngle();
    }
    public double getDist()
    {
    	return dist;
    }
}

