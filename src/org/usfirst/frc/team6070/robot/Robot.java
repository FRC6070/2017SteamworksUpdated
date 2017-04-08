
package org.usfirst.frc.team6070.robot;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoSink;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team6070.robot.gmfilewriter.GMFileWriter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;

import org.opencv.videoio.*;
import org.opencv.imgcodecs.*;

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
	public static PowerDistributionPanel pdp = new PowerDistributionPanel();
	public static Stabilizer gearwindow;
	
	Preferences pref;
	
	//public GMFileWriter fileWriter = new GMFileWriter();

	Command autonomousCommand;
	public SendableChooser chooser = new SendableChooser();
	
	UsbCamera camera1;
	UsbCamera camera2;
	VideoSink server;
	
	boolean frontCameraIsEnabled = false;
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		gearwindow = new Stabilizer();
		climber = new Climber();
		DriveBase = new Chassis();
		gear = new GearBox();
		oi = new OI();
		chooser.addDefault("No Auto", new NoAuto());
		chooser.addObject("Centre auto", new StephenAutonomous());
		chooser.addObject("Right", new K_Autonomous());
		chooser.addObject("EZ 5 points", new EZ5(3));
		chooser.addObject("Left", new StephenKenishaAuto());
		chooser.addObject("Thingy - autoturn to -30", new AutoTurn(-30, 2));
		chooser.addObject("And You thought we were contributing to this alliance...", new Donuts(10));
		chooser.addObject("Deliver Balls", new DeliverBall(1, 0.25));
		chooser.addObject("3-Point Auto", new BallsAuto());
		pref = Preferences.getInstance();
		DriveBase.resetGyro();
		DriveBase.resetAccel();
		camera1 = CameraServer.getInstance().startAutomaticCapture("cam0", 0);
		camera2 = CameraServer.getInstance().startAutomaticCapture("cam1", 1);
		server = CameraServer.getInstance().getServer();
		
		
//		VideoCapture vc = new VideoCapture(0);
//		if (vc.isOpened()) {
////		    vc.set(Imgcodecs.CV_CAP_PROP_FRAME_WIDTH, 640);
////		    vc.set(Imgcodecs., 480);
//		}
		
		// chooser.addObject("My Auto", new MyAutoCommand());
		SmartDashboard.putData("Auto mode", chooser);
		SmartDashboard.putData("DriveStraight for 5:", new AutoDrive(2));
		SmartDashboard.putData("Auto Balls", new DeliverBall(1, 0.25));
		SmartDashboard.putData("Realign", new AutoTurn(90, 2));
		SmartDashboard.putData("Chassis:", DriveBase);
		
		// Right Joystick Throttle Value
		SmartDashboard.putNumber("Joystick Throttle Value", OI.right.getRawAxis(2));
		
//		SmartDashboard.putData("Gearback", new Gearbackwards());
//		SmartDashboard.putData("Gearforwards", new Gearforwards());
		
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {
		DriveBase.resetGyro();
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		updateSmartDashboard();
		
		runCameraSystem();
		
	}

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
		DriveBase.resetGyro();
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
		
		//SmartDashboard.putDouble("IMU", DriveBase.imu.getAngle());
		Scheduler.getInstance().run();
		updateSmartDashboard();
		
		runCameraSystem();
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
	
	public void updateSmartDashboard()
	{
		SmartDashboard.putNumber("Accel", DriveBase.getAccel());
		SmartDashboard.putNumber("Gyro val", DriveBase.getGyroYaw());
		SmartDashboard.putNumber("Dist: ", DriveBase.getDist());
		SmartDashboard.putData("Chassis:", DriveBase);
		
		// Right Joystick Throttle Value
		SmartDashboard.putNumber("Joystick Throttle Value", OI.right.getRawAxis(2));
		
//		SmartDashboard.putNumber(", value)
	}
	
	public void runCameraSystem() {
		if(OI.right.getRawButton(1) && !frontCameraIsEnabled) {
			System.out.print("Setting camera 2\n");
			//NetworkTable.getTable("").putString("CameraChoice", "cam1");
			server.setSource(camera2);
			System.out.print(camera2.getName());
		} else if (!OI.right.getRawButton(1) && frontCameraIsEnabled) {
			System.out.print("Setting camera 1\n");
			//NetworkTable.getTable("").putString("CameraChoice", "cam0");
			server.setSource(camera1);
			System.out.print(camera1.getName());
		}
		frontCameraIsEnabled = OI.right.getRawButton(1);
	}
	
//	public void autonomousRecordingRun() {
//		new Thread(() -> {
//			
//		}).start();
//	}
	public void yell(String phrase) {
		//System.out.
	}
}
