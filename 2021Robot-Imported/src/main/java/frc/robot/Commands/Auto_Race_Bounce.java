// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Commands;

import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.DriveTrain;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class Auto_Race_Bounce extends SequentialCommandGroup {
  /** Creates a new Auto_Race_Bounce. */
  public Auto_Race_Bounce(DriveTrain drive, Gyro gyro) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new DriveCircle(drive, gyro, false, 90),
      new DriveForward(drive, 20),
      new DriveSpin(drive, gyro, false, 200),
      new DriveForward(drive, 11.2 * 12),
      new DriveCircle(drive, gyro, false, 70),
      new DriveSpin(drive, gyro, false, 90),
      new DriveForward(drive, 11 * 12),
      new DriveSpin(drive, gyro, false, 180),
      new DriveForward(drive, 11 * 12),
      new DriveSpin(drive, gyro, false, 90),
      new DriveForward(drive, 7.5 * 12),
      new DriveSpin(drive, gyro, false, 90),
      new DriveForward(drive, 11 * 12),
      new DriveSpin(drive, gyro, false, 180),
      new DriveForward(drive, 18),
      new DriveCircle(drive, gyro, false, 90)
    );
  }
}
