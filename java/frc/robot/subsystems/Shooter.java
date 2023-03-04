// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants;

public class Shooter extends SubsystemBase {
  /** Creates a new Shooter. */
  //Naming and Constructing the Motor Controller
  /** Shooter Motor,
   * Constants can be found here
   * {@link ShooterConstants}
   * */
  TalonSRX shooterMotor = new TalonSRX(ShooterConstants.ShooterPort);
  //Setting the inversion and neutral mode
  /** Shooter Subsystem*/
  public Shooter() {
    shooterMotor.configFactoryDefault();
    shooterMotor.setInverted(true);
    shooterMotor.setNeutralMode(NeutralMode.Coast);
  }
  //Creating the Command Base and setting up the Command
  /**Shoots the game piece at 50% Speed
   * @param fwd the commanded forward speed
   * @return FwdShoot
  */
  public CommandBase FwdShoot(double fwd){
    return run(
      () -> shooterMotor.set(
        TalonSRXControlMode.PercentOutput, -0.5)
        ).withName("Fwdshooter");
  }
  /** Reverses the shooter
   * @param rev the commanded reverse speed
   * @return RevShoot
   */
  public CommandBase RevShoot(double rev) {
    return run(
      () -> 
        shooterMotor.set(
          TalonSRXControlMode.PercentOutput, 0.10)
        );
  }
  /** Shoots the game piece at 40% speed
   * @param fwdHalf the commanded forward speed
   * @return FwdHalfShoot
   */
  public CommandBase FwdHalfShoot(double fwdHalf) {
    return run(
      () -> 
        shooterMotor.set(
          TalonSRXControlMode.PercentOutput, -0.40)
        );
  }
  /** Shoots the game piece at full speed
   * @param fwdFull the commanded forward speed
   * @return FwdFullShoot
   */
  public CommandBase FwdFullShoot(double fwdFull) {
    return run(
      () -> 
        shooterMotor.set(
          TalonSRXControlMode.PercentOutput, -1)
        );
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
