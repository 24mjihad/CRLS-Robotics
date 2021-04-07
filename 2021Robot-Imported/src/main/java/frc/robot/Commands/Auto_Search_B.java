// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Commands;

import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Gatherer;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class Auto_Search_B extends SequentialCommandGroup {
  /** Creates a new Auto_Search_B. */
  public Auto_Search_B(DriveTrain drive, Gyro gyro, Gatherer gather) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new InstantCommand(gather::start, gather),
      new DriveForward(drive, 12*5.5),
      new DriveSpin(drive, gyro, true, 90),
      new DriveForward(drive, 12*5),
      new DriveSpin(drive, gyro, false, 90),
      new DriveForward(drive, 12*10),
      new DriveSpin(drive, gyro, false, 90),
      new DriveForward(drive, 12*5),
      new DriveSpin(drive, gyro, true, 90),
      new DriveForward(drive, 12*5),
      new DriveCircle(drive, gyro, false, 90),
      new InstantCommand(gather::stop, gather)
    );
  }
}
