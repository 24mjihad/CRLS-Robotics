// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Commands;


import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class DriveForward extends CommandBase {
  /** Creates a new DriveForward. */
  private DriveTrain m_drive;
  private double  m_duration;
  //private Encoder m_encoder;
  public DriveForward(DriveTrain drive, double distance) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_drive = drive;
    m_duration = distance;
    //m_encoder = encoder;
    addRequirements(m_drive);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_drive.resetEncoders();
    m_drive.tankDrive(.65, .65);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_drive.tankDrive(.65, .65);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_drive.tankDrive(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return Math.abs(m_drive.avgPosition()) >= m_duration;
  }
}
