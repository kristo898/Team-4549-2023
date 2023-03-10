// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;



import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
public class BlindMansCane extends SubsystemBase {
  VictorSPX blindmanscane = new VictorSPX(8);
  /** Creates a new BlindMansCane. */
  public BlindMansCane() {
    blindmanscane.setInverted(false);
    blindmanscane.setNeutralMode(NeutralMode.Brake);
    setDefaultCommand(runOnce(()->{
      blindmanscane.set(ControlMode.PercentOutput,0.1);
    }).andThen(run(()->{})));
  }
  /** Blind mans cane Forward
   * @param fwd Brins pole in front of robot
   * @return fwdblindmanscane
   */
  public CommandBase fwdblindmanscane(Double fwd){
    return run(()->blindmanscane.set(ControlMode.PercentOutput,0.1));
  }
  /** Blind mans cane reverse
   * @param rev reteruns pole to up right posishing 
   * @return revblindmanscane
    */
  public CommandBase revblindmanscane(Double rev){
    return run(()->blindmanscane.set(ControlMode.PercentOutput,-0.1));
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
