
package org.usfirst.frc.team6070.robot;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team6070.robot.gmfilewriter.GMFileWriter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;

import org.usfirst.frc.team6070.robot.commands.*;
import org.usfirst.frc.team6070.robot.subsystems.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
@SuppressWarnings("unused")
public class Robot extends IterativeRobot {
	
	//public static ArrayList<ArrayList<Double>> arrayOfArrayVals = new ArrayList<ArrayList<Double>>();
	
	public static OI oi;
	public static Chassis DriveBase;
	public static Climber climber;
	public static GearBox gear;
	
	//public GMFileWriter fileWriter = new GMFileWriter();

	Command autonomousCommand;
	public SendableChooser chooser = new SendableChooser();
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		chooser.addDefault("Default - for now autodrive 5 feet", new AutoDrive(5, 2));
		chooser.addObject("Centre auto", new StephenAutonomous());
		chooser.addObject("Red Right/Blue Left", new K_Autonomous());
		chooser.addObject("Red left/Blue right", new StephenKenishaAuto());
		chooser.addObject("Thingy - autoturn to -30", new AutoTurn(-30));
		climber = new Climber();
		DriveBase = new Chassis();
		gear = new GearBox();
		oi = new OI();
		DriveBase.resetGyro();
		DriveBase.resetAccel();
		CameraServer.getInstance().startAutomaticCapture("cam0", 0).setResolution(640, 360);
		
		// chooser.addObject("My Auto", new MyAutoCommand());
		SmartDashboard.putData("Auto mode", chooser);
		SmartDashboard.putData("DriveStraight for 5:", new AutoDrive(5, 2));
		SmartDashboard.putData("Realign", new AutoTurn(90));
		SmartDashboard.putData("Chassis:", DriveBase);
		
//		SmartDashboard.putData("Gearback", new Gearbackwards());
//		SmartDashboard.putData("Gearforwards", new Gearforwards());
		
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
//	@Override
//	public void disabledInit() {
//
//	}
//
//	@Override
//	public void disabledPeriodic() {
//		Scheduler.getInstance().run();
//	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		autonomousCommand = (Command) chooser.getSelected();

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
		if (autonomousCommand != null)
			autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out		
		if (autonomousCommand != null)
			autonomousCommand.cancel();
		
		DriveBase.resetGyro();
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		SmartDashboard.putNumber("Accel", DriveBase.getAccel());
		SmartDashboard.putNumber("Gyro", DriveBase.getGyroYaw());
		SmartDashboard.putNumber("Dist: ", DriveBase.getDist());
		//SmartDashboard.putDouble("IMU", DriveBase.imu.getAngle());
		Scheduler.getInstance().run();
		
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
	
	public void autonomousRecordingRun() {
		new Thread(() -> {
			
		}).start();
	}
}
