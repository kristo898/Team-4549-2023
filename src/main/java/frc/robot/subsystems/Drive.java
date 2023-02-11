// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.util.function.DoubleSupplier;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveConstants;

public class Drive extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */
  /** Shooter Subsystem */
  Shooter m_Shooter = new Shooter();
  //Naming and constructing the Motor Group
  /** Constants can be found in {@link DriveConstants} */
  MotorControllerGroup rightGroup = new MotorControllerGroup(
      new CANSparkMax(DriveConstants.frontRightPort, MotorType.kBrushless),
      new CANSparkMax(DriveConstants.middleRightPort, MotorType.kBrushless),
      new CANSparkMax(DriveConstants.backRightPort, MotorType.kBrushless)
      );
    MotorControllerGroup leftGroup = new MotorControllerGroup(
      new CANSparkMax(DriveConstants.frontLeftPort, MotorType.kBrushless),
      new CANSparkMax(DriveConstants.middleLeftPort, MotorType.kBrushless),
      new CANSparkMax(DriveConstants.backLeftPort, MotorType.kBrushless)
      );
  /** Naming and Constructing the Timer for auto */
  Timer autoTimer = new Timer();
  /**Naming and constructing the drive train*/
  DifferentialDrive drive = new DifferentialDrive(rightGroup, leftGroup);
  /** Drive Train Subsystem */
  public Drive() {
    //Setting Motor Inversion
    rightGroup.setInverted(false);
    leftGroup.setInverted(true);
  }

  /**
   * Example command factory method.
   *
   * @return a command
   */
  /** Drives the Robot */
  public CommandBase DriveCommand(DoubleSupplier fwd, DoubleSupplier rot) {
    // Inline construction of command goes here.
    
   return run(() -> drive.arcadeDrive(fwd.getAsDouble(), rot.getAsDouble())).withName("arcadeDrive"); 
  }
  /**Runs the Auto on the Blue Alliance Station 1 */
  public CommandBase LeftBlue(){
    return runOnce(() -> autoTimer.reset()).withName("autoStart")
      .andThen(
        run(() -> m_Shooter.Fwdshoot())
      ).withName("autoShoot")
      .andThen(
        run(() -> autoTimer.start()), 
        run(() -> drive.arcadeDrive(0.5, 0)), 
        run(() -> autoTimer.hasElapsed(1)),
        run(() -> autoTimer.stop())).withName("autoStep1")
      .andThen(
        run(() -> autoTimer.start()),
        run(() -> drive.arcadeDrive(0, 0.5)),
        run(() -> autoTimer.hasElapsed(0.5)),
        run(() -> autoTimer.stop())).withName("autoStep2")
      .andThen(
        run(() -> autoTimer.start()),
        run(() -> drive.arcadeDrive(0.5, 0)),
        run(() -> autoTimer.hasElapsed(3)),
        run(() -> autoTimer.stop())).withName("autoStep3")
      .andThen(
        run(() -> autoTimer.start()),
        run(() -> drive.arcadeDrive(0, -0.5)),
        run(() -> autoTimer.hasElapsed(0.5)),
        run(() -> autoTimer.stop())).withName("autoStep4")
      .andThen(
        run(() -> autoTimer.start()),
        run(() -> drive.arcadeDrive(0.5, 0)),
        run(() -> autoTimer.hasElapsed(2)),
        run(() -> autoTimer.stop())).withName("autoStep4")
      .andThen(
        run(() -> autoTimer.start()),
        run(() -> drive.arcadeDrive(0, 0.5)),
        run(() -> autoTimer.hasElapsed(0.5)),
        run(() -> autoTimer.stop())
      ).withName("autoStep5").withName("autoEnd");
  }
  /**Runs auto in front Blue Station 3*/
  public CommandBase RightBlue(){
    return runOnce(() -> autoTimer.reset())
      .withName("autoStart")
    .andThen(() -> m_Shooter.Fwdshoot())
      .withName("autoShoot")
    .andThen(
      run(() -> autoTimer.start()),
      run(() -> drive.arcadeDrive(0, 0)),
      run(() -> autoTimer.hasElapsed(1)),
      run(() -> autoTimer.stop())).withName("autoStep1")
    .andThen(
      run(() -> autoTimer.start()),
      run(() -> drive.arcadeDrive(0, 0)),
      run(() -> autoTimer.hasElapsed(1)),
      run(() -> autoTimer.stop()).withName("autoStep2")
    .andThen(
      run(() -> autoTimer.start())
      )
      );
  }
  /**Runs auto in front Red 1*/
  public CommandBase LeftRed(){
    return runOnce(() -> autoTimer.reset())
      .withName("autoStart")
    .andThen(() -> m_Shooter.Fwdshoot())
      .withName("autoShoot");
  }
  /**Runs auto in front Red 3*/
  public CommandBase RightRed(){
    return runOnce(() -> autoTimer.reset())
      .withName("autoStart")
    .andThen(() -> m_Shooter.Fwdshoot())
      .withName("autoShoot");
  }
  /**Runs auto in front Red/Blue 2*/
  public CommandBase Middle(){
    return runOnce(() -> autoTimer.reset())
      .withName("autoStart")
    .andThen(() -> m_Shooter.Fwdshoot())
      .withName("autoShoot");
  }
  /**
   * An example method querying a boolean state of the subsystem (for example, a digital sensor).
   *
   * @return value of some boolean subsystem state, such as a digital sensor.
   */
  public boolean exampleCondition() {
    // Query some boolean state, such as a digital sensor.
    return false;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
