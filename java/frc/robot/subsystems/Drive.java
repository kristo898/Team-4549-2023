// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.util.function.DoubleSupplier;

import com.revrobotics.CANSparkMax;
//import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveConstants;

public class Drive extends SubsystemBase {
  /** Shooter Subsystem */
  Shooter m_Shooter = new Shooter();
  //Right Motor
  /** Front Right Motor */
  CANSparkMax frontRight = new CANSparkMax(DriveConstants.frontRightPort, MotorType.kBrushless);
  /** Middle Right Motor */
  CANSparkMax middleRight = new CANSparkMax(DriveConstants.middleRightPort, MotorType.kBrushless);
  /** Back Right Motor */
  CANSparkMax backRight = new CANSparkMax(DriveConstants.backRightPort, MotorType.kBrushless);
  //Left Motors
  /** Front Left Motor */
  CANSparkMax frontLeft = new CANSparkMax(DriveConstants.frontLeftPort, MotorType.kBrushless);
  /** Middle Left Motor */
  CANSparkMax middleLeft = new CANSparkMax(DriveConstants.middleLeftPort, MotorType.kBrushless);
  /** Back Left Motor */
  CANSparkMax backLeft = new CANSparkMax(DriveConstants.backLeftPort, MotorType.kBrushless);
  //Naming and constructing the Motor Groups
  /** Right Controller Group,
   * Constants can be found in {@link DriveConstants} */
  MotorControllerGroup rightGroup = new MotorControllerGroup(
    frontRight, middleRight, backRight
  );
  /** Left Controller Group,
   * Constants can be found in {@link DriveConstants} */
    MotorControllerGroup leftGroup = new MotorControllerGroup(
      frontLeft, middleLeft, backLeft
    );
  //Naming and Constructing the Timer for auto
  /** Timer for auto */
  Timer autoTimer = new Timer();
  //Naming and constructing the drive train
  /** Drive Train */
  DifferentialDrive drive = new DifferentialDrive(rightGroup, leftGroup);
  /** Drive Train Subsystem */
  public Drive() {
    //Returns to the driver station Motor Controller Voltages
    frontLeft.getBusVoltage();
    middleLeft.getBusVoltage();
    backLeft.getBusVoltage();
    frontRight.getBusVoltage();
    middleRight.getBusVoltage();
    backRight.getBusVoltage();
    //Returns the Current in Amps
    frontLeft.getOutputCurrent();
    middleLeft.getOutputCurrent();
    backLeft.getOutputCurrent();
    frontRight.getOutputCurrent();
    middleRight.getOutputCurrent();
    backRight.getOutputCurrent();
    //Returns the current Temperature of the motors
    frontLeft.getMotorTemperature();
    middleLeft.getMotorTemperature();
    backLeft.getMotorTemperature();
    frontRight.getMotorTemperature();
    middleRight.getMotorTemperature();
    backRight.getMotorTemperature();
    //Setting the inversions
    frontLeft.setInverted(false);
    middleLeft.setInverted(false);
    backLeft.setInverted(false);
    frontRight.setInverted(true);
    middleRight.setInverted(true);
    backRight.setInverted(true);
    //Settting the Idle Modes
    /*frontLeft.setIdleMode(IdleMode.kBrake);
    middleLeft.setIdleMode(IdleMode.kBrake);
    backLeft.setIdleMode(IdleMode.kBrake);
    frontRight.setIdleMode(IdleMode.kBrake);
    middleRight.setIdleMode(IdleMode.kBrake);
    backRight.setIdleMode(IdleMode.kBrake);*/
  }

  /** Drives the Robot using an arcade drive
   * @param fwd the commanded forward movement
   * @param rot the commanded rotation
   * @return DriveCommand
   */
  public CommandBase DriveCommand(DoubleSupplier fwd, DoubleSupplier rot) {
    // Inline construction of command goes here.
   return run(
      () -> drive.arcadeDrive(
        fwd.getAsDouble(),
        rot.getAsDouble())
        ).withName("arcadeDrive")
        .withName("driveWorks"); 
  }
  /**Runs the Auto on the Blue Alliance Station 1
   @return LeftBlue
   */
  public CommandBase LeftBlue(){
    return runOnce(() -> autoTimer.reset()).withName("autoStart")
      .andThen(
        run(() -> m_Shooter.FwdShoot(1))
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
      ).withName("autoStep5").withName("autoWorks").withName("autoEnd");
  }
  /**Runs auto in front Blue Station 3
   * @return RightBlue
  */
  public CommandBase RightBlue(){
    return runOnce(() -> autoTimer.reset())
      .withName("autoStart")
    .andThen(() -> m_Shooter.FwdShoot(1))
      .withName("autoShoot")
    .andThen(
      run(() -> autoTimer.start()),
      run(() -> drive.arcadeDrive(0, 0)),
      run(() -> autoTimer.hasElapsed(1)),
      run(() -> autoTimer.stop()))
      .withName("autoStep1")
    .andThen(
      run(() -> autoTimer.start()),
      run(() -> drive.arcadeDrive(0, 0)),
      run(() -> autoTimer.hasElapsed(1)),
      run(() -> autoTimer.stop())
      .withName("autoStep2")
    .andThen(
      run(() -> autoTimer.start()),
      run(() -> drive.arcadeDrive(0, 0)),
      run(() -> autoTimer.hasElapsed(1)),
      run(() -> autoTimer.stop())
      .withName("autoStep3")
    .andThen(
      run(() -> autoTimer.start()),
      run(() -> drive.arcadeDrive(0, 0)),
      run(() -> autoTimer.hasElapsed(1)),
      run(() -> autoTimer.stop()).withName("autoStep4")
    ).andThen(
      run(() -> autoTimer.start()),
      run(() -> drive.arcadeDrive(0, 0)),
      run(() -> autoTimer.hasElapsed(0)),
      run(() -> autoTimer.stop())
      .withName("autostep5").withName("autoEnd")
    )));
  }
  /**Runs auto in front Red 1
   * @return LeftRed
  */
  public CommandBase LeftRed(){
    return runOnce(() -> autoTimer.reset())
      .withName("autoStart")
    .andThen(() -> m_Shooter.FwdShoot(1))
      .withName("autoShoot")
    .andThen(
      run(() -> autoTimer.start()),
      run(() -> drive.arcadeDrive(0, 0)),
      run(() -> autoTimer.hasElapsed(1)),
      run(() -> autoTimer.stop()))
      .withName("autoStep1")
    .andThen(
      run(() -> autoTimer.start()),
      run(() -> drive.arcadeDrive(0, 0)),
      run(() -> autoTimer.hasElapsed(1)),
      run(() -> autoTimer.stop())
      .withName("autoStep2")
    .andThen(
      run(() -> autoTimer.start()),
      run(() -> drive.arcadeDrive(0, 0)),
      run(() -> autoTimer.hasElapsed(1)),
      run(() -> autoTimer.stop())
      .withName("autoStep3")
    .andThen(
      run(() -> autoTimer.start()),
      run(() -> drive.arcadeDrive(0, 0)),
      run(() -> autoTimer.hasElapsed(1)),
      run(() -> autoTimer.stop()).withName("autoStep4")
    ).andThen(
      run(() -> autoTimer.start()),
      run(() -> drive.arcadeDrive(0, 0)),
      run(() -> autoTimer.hasElapsed(0)),
      run(() -> autoTimer.stop())
      .withName("autostep5").withName("autoEnd")
    )));
  }
  /**Runs auto in front Red 3
   * @return RightRed
  */
  public CommandBase RightRed(){
    /** Starts Auto Right Red */
    return runOnce(() -> autoTimer.reset())
      .withName("autoStart")
    .andThen(() -> m_Shooter.FwdShoot(1))
      .withName("autoShoot")
    .andThen(
      run(() -> autoTimer.start()),
      run(() -> drive.arcadeDrive(0, 0)),
      run(() -> autoTimer.hasElapsed(1)),
      run(() -> autoTimer.stop()))
      .withName("autoStep1")
    .andThen(
      run(() -> autoTimer.start()),
      run(() -> drive.arcadeDrive(0, 0)),
      run(() -> autoTimer.hasElapsed(1)),
      run(() -> autoTimer.stop())
      .withName("autoStep2")
    .andThen(
      run(() -> autoTimer.start()),
      run(() -> drive.arcadeDrive(0, 0)),
      run(() -> autoTimer.hasElapsed(1)),
      run(() -> autoTimer.stop())
      .withName("autoStep3")
    .andThen(
      run(() -> autoTimer.start()),
      run(() -> drive.arcadeDrive(0, 0)),
      run(() -> autoTimer.hasElapsed(1)),
      run(() -> autoTimer.stop()).withName("autoStep4")
    ).andThen(
      run(() -> autoTimer.start()),
      run(() -> drive.arcadeDrive(0, 0)),
      run(() -> autoTimer.hasElapsed(0)),
      run(() -> autoTimer.stop())
      .withName("autostep5").withName("autoEnd")
    )));
  }
  /**Runs auto in front Red/Blue 2
   * @return Middle
  */
  public CommandBase Middle(){
    return runOnce(() -> autoTimer.reset())
      .withName("autoStart")
    .andThen(() -> m_Shooter.FwdShoot(1))
      .withName("autoShoot")
    .andThen(
      run(() -> autoTimer.start()),
      run(() -> drive.arcadeDrive(0, 0)),
      run(() -> autoTimer.hasElapsed(1)),
      run(() -> autoTimer.stop()))
      .withName("autoStep1")
    .andThen(
      run(() -> autoTimer.start()),
      run(() -> drive.arcadeDrive(0, 0)),
      run(() -> autoTimer.hasElapsed(1)),
      run(() -> autoTimer.stop())
      .withName("autoStep2")
    .andThen(
      run(() -> autoTimer.start()),
      run(() -> drive.arcadeDrive(0, 0)),
      run(() -> autoTimer.hasElapsed(1)),
      run(() -> autoTimer.stop())
      .withName("autoStep3")
    .andThen(
      run(() -> autoTimer.start()),
      run(() -> drive.arcadeDrive(0, 0)),
      run(() -> autoTimer.hasElapsed(1)),
      run(() -> autoTimer.stop()).withName("autoStep4")
    ).andThen(
      run(() -> autoTimer.start()),
      run(() -> drive.arcadeDrive(0, 0)),
      run(() -> autoTimer.hasElapsed(0)),
      run(() -> autoTimer.stop())
      .withName("autostep5").withName("autoEnd")
    )));
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
