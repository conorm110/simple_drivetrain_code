// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

/** These four lines import libraries needed for the variables CANSparkMax, MotorType, and XBoxController. They were added automatically or with the lightbulb button  */
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/** Creates a new subsystem class Drivetrain. */
public class Drivetrain extends SubsystemBase {
  /** Declare variables for the Drivetrain */
  private CANSparkMax leftMotor;
  private CANSparkMax rightMotor;

  private XboxController controller;

  private DifferentialDrive drivetrain; // For a mecanum drive robot, this would instead be "private MecanumDrive drivatrain;"

  // This is the constructor method. This method is called whenever the Drivetrain class is first used (when an object is created
  // from this Drivetrain class, this is done in RobotContainer.java). If you have experience with Arduinos this is like the 
  // setup function
  public Drivetrain() {
    // Initilize variables of the Drivetrain class 
    leftMotor = new CANSparkMax(2, MotorType.kBrushed);
    rightMotor = new CANSparkMax(3, MotorType.kBrushed);

    controller = new XboxController(0);

    drivetrain = new DifferentialDrive(leftMotor, rightMotor);
  }

  // This is the periodic method. This method is called every 20ms so the RoboRIO is constantly looping through this code. If you
  // have experience with Arduinos this is like the loop function.
  @Override
  public void periodic() {

    double leftMotorSpeed = controller.getLeftY();
    double rightMotorSpeed = controller.getRightY();

    boolean slowMode = controller.getAButton();
    if (slowMode) {
      leftMotorSpeed = leftMotorSpeed / 2.0;
      rightMotorSpeed = rightMotorSpeed / 2.0;
    }

    drivetrain.tankDrive(leftMotorSpeed, rightMotorSpeed);
  }

  /**
   * Example "command" method, these are a bit confusing. Leaving it here as a template for 
   * later programming lessons but we wont be using it today 11/14
   */
  public CommandBase exampleMethodCommand() {
    // Inline construction of command goes here.
    // Subsystem::RunOnce implicitly requires `this` subsystem.
    return run(
        () -> {
          // Command code goes here
        });
  }

  

  /**
   * Example condition, this is used in RobotContainer.java to determine if exampleMethodCommand can be run.
   * Also left here as a template for later programming lessons but we wont be using it today
   */
  public boolean exampleCondition() {
    return false;
  }

  // We will not be using the simulationPeriodic methoc
  @Override
  public void simulationPeriodic() {}
}
