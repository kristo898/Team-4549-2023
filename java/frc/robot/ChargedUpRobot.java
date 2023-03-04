// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.Pole;
import frc.robot.subsystems.Shooter;
//import edu.wpi.first.wpilibj.PowerDistribution;
//import edu.wpi.first.wpilibj.PowerDistribution.ModuleType;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the 
 * {@link Drive} or {@link Shooter} or {@link Pole}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class ChargedUpRobot {
  
  // The robot's subsystems and commands are defined here...
  /** Drive Subsystem */
  private final Drive m_drive = new Drive();
  /** Shooter Subsystem */
  //private final Shooter m_shooter = new Shooter();
  /** Pole Subsystem */
  //private final Pole m_pole = new Pole();
  //private final PowerDistribution m_pdh = new PowerDistribution(1, ModuleType.kRev);
  /** Allows you to choose an auto before the match starts */
  SendableChooser<Command> m_Chooser = new SendableChooser<>();
  
  // Replace with CommandPS4Controller or CommandJoystick if needed
  /** Driver Controller */
  private final CommandXboxController m_driverController =
      new CommandXboxController(OperatorConstants.kDriverControllerPort);

  /** The container for the Charged Up robot. Contains subsystems, OI devices, and commands. */
  public ChargedUpRobot() {
    m_drive.setDefaultCommand(
      m_drive.DriveCommand(
        () -> m_driverController.getLeftY(), 
        () -> m_driverController.getLeftX())
        .withName("drive")
      );
    // Configure the trigger bindings
    configureBindings();
      /*m_Chooser.addOption("BlueLeft", m_drive.LeftBlue().withName("LeftBlue"));
      m_Chooser.addOption("BlueRight", m_drive.RightBlue().withName("RightBlue"));
      m_Chooser.addOption("RedLeft", m_drive.LeftRed().withName("LeftRed"));
      m_Chooser.addOption("RedRight", m_drive.RightRed().withName("RightRed"));
      m_Chooser.addOption("Middle", m_drive.Middle().withName("Middle"));
      SmartDashboard.putData(m_Chooser);
      double current14 = m_pdh.getCurrent(14);
      SmartDashboard.putNumber("Current Channel 14", current14);
      double current13 = m_pdh.getCurrent(13);
      SmartDashboard.putNumber("Current Channel 13", current13);
      double current12 = m_pdh.getCurrent(12);
      SmartDashboard.putNumber("Current Channel 12", current12);
      double current5 = m_pdh.getCurrent(5);
      SmartDashboard.putNumber("Current Channel 5", current5);
      double current6 = m_pdh.getCurrent(6);
      SmartDashboard.putNumber("Current Channel 6", current6);
      double current7 = m_pdh.getCurrent(7);
      SmartDashboard.putNumber("Current Channel 7", current7);*/
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
    // Schedule `FwdHalfShoot` when the Xbox controller's A button is pressed,
    // cancelling on release.
    // Schedule `FwdFullShoot` when the Xbox controller's Y button is pressed,
    // cancelling on release.
    // Schedule `RevShoot` when the Xbox controller's B button is pressed,
    // cancelling on release.
    // Schedule `FwdShoot` when the Xbox controller's X button is pressed,
    // cancelling on release.
    // Schedule `FwdPole` when the Xbox controller's Back button is pressed,
    // cancelling on release.
    // Schedule `RevPole` when the Xbox controller's Start button is pressed,
    // cancelling on release.
    /*m_driverController.x().whileTrue(m_shooter.FwdShoot(-1).withName("FwdShoot"));
    m_driverController.b().whileTrue(m_shooter.RevShoot(-1).withName("RevShoot"));
    m_driverController.a().whileTrue(m_shooter.FwdHalfShoot(-1).withName("FwdHalfShoot"));
    m_driverController.y().whileTrue(m_shooter.FwdFullShoot(-1).withName("FwdFullShoot"));
    m_driverController.back().whileTrue(m_pole.FwdPole(0.1).withName("FwdPole"));
    m_driverController.start().whileTrue(m_pole.RevPole(0.1).withName("RevPole"));*/
    
  }

  /**
   * Use this to pass the autonomous command to the main {@link Drive} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return m_Chooser.getSelected();
  }
  
}
