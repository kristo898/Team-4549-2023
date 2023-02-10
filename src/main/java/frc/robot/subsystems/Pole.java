// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Pole extends SubsystemBase {
  /** Pole Motor */
  WPI_VictorSPX poleMotor = new WPI_VictorSPX(8);
  /** Creates a new Pole. */
  public Pole() {
    poleMotor.setInverted(false);
    poleMotor.setNeutralMode(NeutralMode.Brake);
    poleMotor.configFactoryDefault();
    setDefaultCommand(
      runOnce(() -> poleMotor.set(
        ControlMode.PercentOutput, 0)
      ).andThen(() -> {})
    );
  }
  public CommandBase FwdPole(double fwd){
    return run(() ->
      poleMotor.set(ControlMode.PercentOutput, 0.1)).withName("getName()");
  }
  public CommandBase RevPole(double rev){
    return run(() ->
      poleMotor.set(ControlMode.PercentOutput, -0.1)).withName("RevPole");
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
