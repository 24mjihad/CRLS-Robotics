// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotMap;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.PID_Drive;

public class Driverpid extends CommandBase {
  /** Creates a new Driver. */
  Joystick joy1;
  Joystick joy2;
  PID_Drive pid;
  private final DriveTrain m_drivetrain;
  public Driverpid(Joystick jst, Joystick jst2, DriveTrain drive) {
    // Use addRequirements() here to declare subsystem dependencies.
    joy1 = jst;
    joy2 = jst2;
    m_drivetrain = drive;
    addRequirements(m_drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_drivetrain.tankDrive(joy1.getRawAxis(RobotMap.Yval), joy2.getRawAxis(RobotMap.Yval));
  }
  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
