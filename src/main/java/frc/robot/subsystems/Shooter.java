// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Shooter extends SubsystemBase {
  VictorSPX shooterMotor = new VictorSPX(12);
  /** Creates a new Shooter. */
  public Shooter() {
    shooterMotor.setInverted(true);
    shooterMotor.setNeutralMode(NeutralMode.Coast);
    setDefaultCommand(runOnce(()->{
      shooterMotor.set(ControlMode.PercentOutput, 0);
    }).andThen(run(()->{})));
  }
  /** Forward Shoot at half speed
   * @param fwd Moves launcher forward 
   * @return FwdShoot
  */
  public CommandBase FwdShoot(double fwd){
    return run(()->shooterMotor.set(ControlMode.PercentOutput, -0.5));
  }
  /** Reverse Shoot at 10% speed
   * @param rev Moves launcher backe in to place
   * @return RevShoot
  */
  public CommandBase RevShoot(double rev){
    return run(()->shooterMotor.set(ControlMode.PercentOutput, .1));
  }
  /** Forward Shoot at 40% speed
   * @param fwd Moves launcher forward 
   * @return FwdHalfShoot
  */
  public CommandBase FwdHalfShoot(double fwd){
    return run(()->shooterMotor.set(ControlMode.PercentOutput, -0.4));
  }
  /** Forward Shoot at 100% speed
   * @param fwd Moves launcher forward 
   * @return FwdFullShoot
   */
  public CommandBase FwdFullShoot(double fwd){
    return run(()->shooterMotor.set(ControlMode.PercentOutput, -1));
  }


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
