package org.usfirst.frc.team6070.robot.subsystems;
import org.usfirst.frc.team6070.robot.RobotMap;

import org.usfirst.frc.team6070.robot.commands.*;

import com.analog.adis16448.frc.ADIS16448_IMU;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import theory6PID.PIDController;


/**
 * This is the main drivetrain.
 */
public class Chassis extends Subsystem {
	
	Victor LF = new Victor (RobotMap.leftFront);
	Victor LB = new Victor (RobotMap.leftBack);
	Victor RF = new Victor (RobotMap.RightFront);
	Victor RB = new Victor (RobotMap.RightBack);
	
	RobotDrive drive = new RobotDrive (LF, LB, RF, RB);
		 
	Accelerometer accel = new BuiltInAccelerometer(Accelerometer.Range.k4G);
	double acc = 0;
	double vel = 0;
	public double dist = 0;
	double anglefix;
	
	double kpgyro = 0.03;
	double kigyro = 0.0;
	double kdgyro = 0.0;
	
	PIDController gyroPID = new PIDController(0.03, 0.0, 0.0);
	
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
    	kpgyro = SmartDashboard.getNumber("kpval", 0.03);
    	kigyro = SmartDashboard.getNumber("kival", 0.0);
    	kdgyro = SmartDashboard.getNumber("kdval", 0.0);
    }
    public void drivestraight(double dist)
    {
    	double speed;
    	boolean done = false;
    	double driveangle = gyro.getAngle();
    	Timer mytimer = new Timer();
    	double prevtime = 0;
    	double timenow = 0;
    	mytimer.reset();
    	mytimer.start();
    	while (!done)
    	{
    		if (dist - this.dist > 5)
        	{
        		speed = 0.8;
        		drive.arcadeDrive(-speed, (anglefix-driveangle)*0.2 *0.7);
        	}
        	else if (dist - this.dist < 0.1)
        	{
        		speed = 0.0;
        		done = true;
        		drive.arcadeDrive(0,0);
        	}
        	else
        	{
        		speed = (dist - this.dist)*0.2;
        		drive.arcadeDrive(-speed, (anglefix-driveangle)*0.2);
        	}
    		
    	}
    	timenow = mytimer.get();
        this.updateaccel(prevtime, timenow);
        prevtime = mytimer.get();
    }
   
    public void updateaccel(double t0, double t1)
    {
    	acc = accel.getX()*32.2;
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
//    	boolean done = false;
//    	while (!done)
//    	{
//    		double ang = (gyro.getAngle() % 360);
//    		//double ang = (imu.getYaw() % 360);
//    		System.out.print("Angle current: ");
//    		System.out.print(ang);
//    		System.out.println();
//    		if (angle-ang < 4)
//    		{
//    			done = true;
//    			drive.arcadeDrive(0, 0);
//    		}
//    		else
//    		{
//    			drive.arcadeDrive(0, ((angle-ang)/(angle-anglefix))*0.5);
//    		}
//    	}
//    	if (done)
//    	{
//    		drive.arcadeDrive(0, 0);
//    	}
    	double ang = gyroPID.calcPID(angle, getGyroYaw(), 1);
    	drive.tankDrive(speed+angle, speed-angle);
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

