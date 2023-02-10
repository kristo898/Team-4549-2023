// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.BeltConstants;

public class Belt extends SubsystemBase {
  /** Creates a new Belt. */
    VictorSPX beltMotor = new VictorSPX(BeltConstants.kBeltMotorPort);
    
    public Belt() {
      beltMotor.configFactoryDefault();
      beltMotor.setNeutralMode(NeutralMode.Coast);
    setDefaultCommand(
      runOnce(
        () -> {
          beltMotor.set(VictorSPXControlMode.PercentOutput, 0);
        }
      ).andThen(run(() -> {}))
    );
    }
    public CommandBase FwdBelt(double fwd) {
      return run(
        () -> 
          beltMotor.set(VictorSPXControlMode.PercentOutput, -0.5));
          
      
    }
    public CommandBase RevBelt(double rev) {
      return run(
        () -> 
          beltMotor.set(VictorSPXControlMode.PercentOutput, 0.10));
          
      
    }
    public CommandBase FwdHalfBelt(double fwd) {
      return run(
        () -> 
          beltMotor.set(VictorSPXControlMode.PercentOutput, -0.40));
          
      
    }
    public CommandBase FwdFullBelt(double fwd) {
      return run(
        () -> 
          beltMotor.set(VictorSPXControlMode.PercentOutput, -1));
    }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  
  }
}
