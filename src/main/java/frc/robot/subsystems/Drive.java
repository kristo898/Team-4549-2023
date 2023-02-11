// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;



import java.util.function.DoubleSupplier;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveConstants;

public class Drive extends SubsystemBase {
  Belt m_Belt = new Belt();
  
  /** Creates a new ExampleSubsystem. */
    WPI_VictorSPX frontRight = new WPI_VictorSPX(DriveConstants.krightDriveMotorPort);
    WPI_VictorSPX backRight = new WPI_VictorSPX(DriveConstants.krightBackDriveMotorPort);
    WPI_VictorSPX frontLeft = new WPI_VictorSPX(DriveConstants.kleftDriveMotorPort);
    WPI_VictorSPX backLeft = new WPI_VictorSPX(DriveConstants.kleftBackDriveMotorPort);
    MotorControllerGroup rightGroup = 
      new MotorControllerGroup(
        frontRight,
        backRight
        );
    MotorControllerGroup leftGroup = 
      new MotorControllerGroup(
        frontLeft,
        backLeft
      );
    DifferentialDrive drive = new DifferentialDrive(rightGroup, leftGroup);
    Timer autoTimer = new Timer();
    public Drive() {
      backRight.setNeutralMode(NeutralMode.Coast);
      frontRight.setNeutralMode(NeutralMode.Coast);
      backLeft.setNeutralMode(NeutralMode.Coast);
      frontLeft.setNeutralMode(NeutralMode.Coast);
      backRight.configFactoryDefault();
      frontRight.configFactoryDefault();
      backLeft.configFactoryDefault();
      frontLeft.configFactoryDefault();
      backRight.setSafetyEnabled(true);
      frontRight.setSafetyEnabled(true);
      backLeft.setSafetyEnabled(true);
      frontLeft.setSafetyEnabled(true);
      leftGroup.setInverted(true);
      rightGroup.setInverted(false);
  }
  
  /**
   * Drives the robot using arcade controls.
   *
   * @param fwd the commanded forward movement
   * @param rot the commanded rotation
   */
  public CommandBase arcadeDriveCommand(DoubleSupplier fwd, DoubleSupplier rot) {
    return run(() -> drive.arcadeDrive(fwd.getAsDouble(), rot.getAsDouble())).withName("arcadeDrive");
  }
  public CommandBase LeftBlue(){
    return runOnce(() -> autoTimer.reset()).withName("autoStarted")
      .andThen(
        run(() -> m_Belt.FwdBelt(0.1))
      )
      .andThen(
        run(() -> autoTimer.start()), 
        run(() -> drive.arcadeDrive(0.5, 0)), 
        run(() -> autoTimer.hasElapsed(1)),
        run(() -> autoTimer.stop()))
      .andThen(
        run(() -> autoTimer.start()),
        run(() -> drive.arcadeDrive(0, 0.5)),
        run(() -> autoTimer.hasElapsed(0.5)),
        run(() -> autoTimer.stop()))
      .andThen(
        run(() -> autoTimer.start()),
        run(() -> drive.arcadeDrive(0.5, 0)),
        run(() -> autoTimer.hasElapsed(3)),
        run(() -> autoTimer.stop()))
      .andThen(
        run(() -> autoTimer.start()),
        run(() -> drive.arcadeDrive(0, -0.5)),
        run(() -> autoTimer.hasElapsed(0.5)),
        run(() -> autoTimer.stop()))
      .andThen(
        run(() -> autoTimer.start()),
        run(() -> drive.arcadeDrive(0.5, 0)),
        run(() -> autoTimer.hasElapsed(2)),
        run(() -> autoTimer.stop()))
      .andThen(
        run(() -> autoTimer.start()),
        run(() -> drive.arcadeDrive(0, 0.5)),
        run(() -> autoTimer.hasElapsed(0.5)),
        run(() -> autoTimer.stop())
      ).withName("autoEnd");
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
