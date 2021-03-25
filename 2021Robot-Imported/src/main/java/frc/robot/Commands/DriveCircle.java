// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Commands;

import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class DriveCircle extends CommandBase {
  /** Creates a new DriveCircle. */
  private Gyro m_gyro;
  private double initAngle;
  private DriveTrain m_drive;
  public DriveCircle(DriveTrain drive, Gyro gyro) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_drive = drive;
    m_gyro = gyro;
    addRequirements(m_drive);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    initAngle = m_gyro.getAngle();
    m_drive.tankDrive(.2, .5);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_drive.tankDrive(.2, .5);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return Math.abs(m_gyro.getAngle() - initAngle) > 340;
  }
}
