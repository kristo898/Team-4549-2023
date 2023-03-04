// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.PoleConstants;

public class Pole extends SubsystemBase {
  /** Pole Motor and Constants can be found in {@link PoleConstants} */
  VictorSPX poleMotor = new VictorSPX(PoleConstants.poleMotorPort);
  /** Creates a new Pole. */
  public Pole() {
    poleMotor.setInverted(false);
    poleMotor.configFactoryDefault();
    poleMotor.setNeutralMode(NeutralMode.Brake);
  }
  /** Moves the Pole Forward
   * @param fwdpole the commanded forward speed
   * @return FwdPole
   */
  public CommandBase FwdPole(double fwdpole){
    return run(() -> 
    poleMotor.set(ControlMode.PercentOutput, 0.1));
  }
  /** Moves the Pole Back
   * @param revpole the commanded reverse speed
   * @return RevPole
   */
  public CommandBase RevPole(double revpole){
    return run(() ->
    poleMotor.set(ControlMode.PercentOutput, -0.1));
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
