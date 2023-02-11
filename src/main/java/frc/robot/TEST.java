// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;


import frc.robot.Constants.OIConstants;
import frc.robot.subsystems.Belt;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.Pole;
//import frc.robot.subsystems.Feeder;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class TEST {
  // The robot's subsystems and commands are defined here...
  private final Drive m_drive = new Drive();
  private final Belt m_belt = new Belt();
  private final Pole m_pole = new Pole();
  private final CommandXboxController m_XboxController = new CommandXboxController(OIConstants.kDriverControllerPort);
  
  
  
  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public TEST() {
    m_drive.setDefaultCommand(
      m_drive.arcadeDriveCommand(() -> -m_XboxController.getLeftY(), () -> 0.75 * -m_XboxController.getLeftX())
    );

    
    // Configure the button bindings
    configureButtonBindings();
      
  
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    m_XboxController.x().whileTrue(m_belt.FwdBelt(-1).withName("FwdBelt"));
    m_XboxController.b().whileTrue(m_belt.RevBelt(-1).withName("RevBelt"));
    m_XboxController.a().whileTrue(m_belt.FwdHalfBelt(-1).withName("FwdHalfBelt"));
    m_XboxController.y().whileTrue(m_belt.FwdFullBelt(-1).withName("FwdFullBelt"));
    m_XboxController.back().whileTrue(m_pole.FwdPole(0.1).withName("FwdPole"));
    m_XboxController.start().whileTrue(m_pole.RevPole(0.1).withName("RevPole"));
    
  }
    

  

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_drive.LeftBlue();
  }
}
