// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  /**Drive Constants*/
  public static class DriveConstants {
    public static final int frontRightPort = 2;
    public static final int middleRightPort = 3;
    public static final int backRightPort = 4;
    public static final int frontLeftPort = 5;
    public static final int middleLeftPort = 6;
    public static final int backLeftPort = 7;
  }
  /**Shooter Constants*/
  public static class ShooterConstants {
    public static final int ShooterPort = 8;
  }
  /**Operator Constants*/
  public static class OperatorConstants {
    public static final int kDriverControllerPort = 0;
  }
  public static class PoleConstants{
    public static final int poleMotorPort = 9;
  }
  public static class AutonomousContants {}
}
