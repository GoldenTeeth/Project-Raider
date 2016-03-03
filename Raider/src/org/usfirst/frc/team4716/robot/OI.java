package org.usfirst.frc.team4716.robot;

import java.util.ArrayList;
import java.util.List;

import org.usfirst.frc.team4716.robot.commands.Bucket.BucketMove;
import org.usfirst.frc.team4716.robot.commands.Bucket.LaunchEjectPiston;
import org.usfirst.frc.team4716.robot.commands.Bucket.ToggleElevator;
import org.usfirst.frc.team4716.robot.commands.Climber.TestLift;
import org.usfirst.frc.team4716.robot.commands.DriveTrain.PositionTest;
import org.usfirst.frc.team4716.robot.commands.DriveTrain.ToggleAllWheels;
import org.usfirst.frc.team4716.robot.subsystems.Bucket.Direction;
import org.usfirst.frc.team4716.robot.subsystems.DriveTrain.PositionStatusCode;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
	Joystick driveStick = new Joystick(0); // Logitech Dual Action
	Joystick operatorStick = new Joystick(1);
	Joystick testStick = new Joystick(5);
	
	List<JoystickButton> driveButtons = new ArrayList<JoystickButton>();
	List<JoystickButton> operatorButtons = new ArrayList<JoystickButton>();
	List<JoystickButton> testButtons = new ArrayList<JoystickButton>();

	public OI() {
		for (int x = 1; x < 12; x++) {
			driveButtons.add(new JoystickButton(driveStick, x));
			operatorButtons.add(new JoystickButton(operatorStick, x));
			testButtons.add(new JoystickButton(testStick, x));
		}

		//--------------Drivetrain testing button set--------------//
		driveButtons.get(0).whenPressed(new ToggleAllWheels());
		
		driveButtons.get(5).whenPressed(new PositionTest(PositionStatusCode.FRONT_LEFT_OUT_REST_IN));
		driveButtons.get(6).whenPressed(new PositionTest(PositionStatusCode.BACK_LEFT_OUT_REST_IN));
		driveButtons.get(10).whenPressed(new PositionTest(PositionStatusCode.FRONT_RIGHT_OUT_REST_IN));
		driveButtons.get(9).whenPressed(new PositionTest(PositionStatusCode.BACK_RIGHT_OUT_REST_IN));
		
		driveButtons.get(3).whenPressed(new PositionTest(PositionStatusCode.LEFT_IN_RIGHT_OUT));
		driveButtons.get(4).whenPressed(new PositionTest(PositionStatusCode.LEFT_OUT_RIGHT_IN));
		
		driveButtons.get(2).whenPressed(new PositionTest(PositionStatusCode.FRONT_IN_BACK_OUT));
		driveButtons.get(1).whenPressed(new PositionTest(PositionStatusCode.FRONT_OUT_BACK_IN));
		
		driveButtons.get(7).whenPressed(new TestLift());
		
		//----------------Bucket testing button set------------------------//
		testButtons.get(0).whenPressed(new LaunchEjectPiston());
		testButtons.get(1).whenPressed(new ToggleElevator());
		//testButtons.get(2).whenPressed(new SetBucketPosition(Direction.UP));
		//testButtons.get(3).whenPressed(new SetBucketPosition(Direction.DOWN));
		
		testButtons.get(5).whenPressed(new BucketMove(Direction.DOWN));
		testButtons.get(6).whenPressed(new BucketMove(Direction.UP));
		
		//-----------------------------------------------------------------//
		
		
//		driveButtons.get(1).whenPressed(new DriveBackUpPosition());
//		driveButtons.get(2).whenPressed(new DriveDownPosition());
//		driveButtons.get(3).whenPressed(new DriveFrontUpPosition());
//		driveButtons.get(4).whenPressed(new DriveUpPosition());
//		driveButtons.get(5).whenPressed(new DriveRightUp());
//		driveButtons.get(6).whenPressed(new DriveLeftUp());
//		driveButtons.get(7).whenPressed(new DrivePunch());
//		driveButtons.get(0).whenPressed(new DriveForwardTime(1.0, 5.0));
//		
//		operatorButtons.get(1).whileHeld(new BucketIntake());
//		operatorButtons.get(1).whenReleased(new BucketIntakeFinished());
	}

	public double getJoyX() {
		if (driveStick.getRawAxis(2) <= 0.1 && driveStick.getRawAxis(2) >= -0.1) {
			return 0;
		}
		else {
			//return driveStick.getRawAxis(2);
			return -driveStick.getX();
		}
	}

	public double getJoyY() {
		if (driveStick.getY() <= 0.1 && driveStick.getY() >= -0.1) {
			return 0;
		}
		else {
			return -driveStick.getY();
		}
	}

}
