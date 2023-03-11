// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;


import java.util.function.DoubleSupplier;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.FaultID;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

//import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
//import frc.robot.Constants.DriveConstants;

public class Drive extends SubsystemBase {
  Shooter m_Shooter = new Shooter();
  CANSparkMax frontRight = new CANSparkMax(2, MotorType.kBrushless);
  CANSparkMax middleRight = new CANSparkMax(3, MotorType.kBrushless);
  CANSparkMax backRight = new CANSparkMax(4, MotorType.kBrushless);
  CANSparkMax frontLeft = new CANSparkMax(5, MotorType.kBrushless);
  CANSparkMax middleLeft = new CANSparkMax(6, MotorType.kBrushless);
  CANSparkMax backLeft = new CANSparkMax(7, MotorType.kBrushless);
  /** Right Drive Group*/
  MotorControllerGroup rightGroup = new MotorControllerGroup(
    frontRight, middleRight, backRight
  );
  /*MotorControllerGroup rightGroup = new MotorControllerGroup(
    new WPI_VictorSPX(DriveConstants.krightDriveMotorPort), 
    new WPI_VictorSPX(DriveConstants.krightBackDriveMotorPort));
  /** Left Drive Group */
  MotorControllerGroup leftGroup = new MotorControllerGroup(frontLeft, middleLeft, backLeft);
  /*MotorControllerGroup leftGroup =
   new MotorControllerGroup(
    new WPI_VictorSPX(DriveConstants.kleftDriveMotorPort), 
    new WPI_VictorSPX(DriveConstants.kleftBackDriveMotorPort));
   /** DriveTrain */
   DifferentialDrive drive = new DifferentialDrive(rightGroup, leftGroup);
   Timer autoTimer = new Timer();
    boolean InvertMyDrive;
   // SlewRateLimiter filter = new SlewRateLimiter(.5, 0.5, 0.5);
    /** Creates a new Drive. */
  public Drive() {
    // restores to defaults
    frontLeft.restoreFactoryDefaults();
    middleLeft.restoreFactoryDefaults();
    backLeft.restoreFactoryDefaults();
    frontRight.restoreFactoryDefaults();
    middleRight.restoreFactoryDefaults();
    backRight.restoreFactoryDefaults();
    // return motor voltage
    frontLeft.getBusVoltage();
    middleLeft.getBusVoltage();
    backLeft.getBusVoltage();
    frontRight.getBusVoltage();
    middleRight.getBusVoltage();
    backRight.getBusVoltage();
    // return motor tempmpre
    frontLeft.getMotorTemperature();
    frontLeft.getMotorTemperature();
    middleLeft.getMotorTemperature();
    backLeft.getMotorTemperature();
    frontRight.getMotorTemperature();
    middleRight.getMotorTemperature();
    backLeft.getMotorTemperature();
    // return motor amp
    frontLeft.getOutputCurrent();
    middleLeft.getOutputCurrent();
    backLeft.getOutputCurrent();
    frontRight.getOutputCurrent();
    middleRight.getOutputCurrent();
    backRight.getOutputCurrent();
    // inversions 
    rightGroup.setInverted(true);
    leftGroup.setInverted(false);
    if(InvertMyDrive = false)
    {
     rightGroup.setInverted(true);
     leftGroup.setInverted(false);
    }
    else
    {
     rightGroup.setInverted(false);
     leftGroup.setInverted(true);
    }
   // filter.calculate(0);
    // setts currentlimit 
    frontLeft.setSmartCurrentLimit(60);
    middleLeft.setSmartCurrentLimit(60);
    backLeft.setSmartCurrentLimit(60);
    frontRight.setSmartCurrentLimit(60);
    middleRight.setSmartCurrentLimit(60);
    backRight.setSmartCurrentLimit(60);
    //burns flash 
    frontLeft.burnFlash();
    middleLeft.burnFlash();
    backLeft.burnFlash();
    frontRight.burnFlash();
    middleRight.burnFlash();
    backRight.burnFlash();
    //checks for faultsa
    frontLeft.getFault(FaultID.kOvercurrent);
    frontLeft.getFaults();
    middleLeft.getFault(FaultID.kOvercurrent);
    middleLeft.getFaults();
    backLeft.getFault(FaultID.kOvercurrent);
    backLeft.getFaults();
    frontRight.getFault(FaultID.kOvercurrent);
    frontRight.getFaults();
    middleRight.getFault(FaultID.kOvercurrent);
    middleRight.getFaults();
    backRight.getFault(FaultID.kOvercurrent);
    backRight.getFaults();

  }
  /** Arcade Drive 
   * @param fwd commanded forward movement
   * @param rot commanded rotation
   * @return arcadeDriveCommand
  */
  public CommandBase arcadeDriveCommand(DoubleSupplier fwd, DoubleSupplier rot) {
    return run(() -> drive.arcadeDrive((fwd.getAsDouble()), (rot.getAsDouble()))).withName("arcadedrive");
  }

  /**  Auto Blue Alliance Station 1 */
  public CommandBase LeftBlue(){
    return  runOnce(() -> autoTimer.reset()).withName("autoStarted")
     .andThen(
      //run(() -> m_Shooter.FwdShoot())
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
  /** Auto Blue Alliance Station 3  */
  public CommandBase rightBlue(){
    return runOnce(()-> autoTimer.reset())
    .andThen(
      //run(()-> m_Shooter.FwdShoot())
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
  /** Auto Red Alliance Station 1 */
public CommandBase leftRed(){
  return runOnce(()-> autoTimer.reset())
    .andThen(
      //run(()-> m_Shooter.FwdShoot())
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
/**  Auto Red Alliance Station 3 */
public CommandBase rightRed(){
  return runOnce(()-> autoTimer.reset())
    .andThen(
      //run(()-> m_Shooter.FwdShoot())
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
  //public Object InvertMyDrive(boolean b) {
  //  return null;
 // }
  
}
