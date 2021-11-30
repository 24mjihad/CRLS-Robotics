// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Commands;

import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class DriveSpin extends CommandBase {
  /** Creates a new DriveSpin. */
  private final DriveTrain m_drive;
  private final Gyro m_gyro;
  private int m_angle;
  private double direction;
  private double initAngle;

  public DriveSpin(DriveTrain drive, Gyro gyro, Boolean right, int angle) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_drive = drive;
    m_gyro = gyro;
    m_angle = angle;
    if(right){
      direction = -.7;
    }
    else{
      direction = .7;
    }
    addRequirements(m_drive);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    initAngle = m_gyro.getAngle();
    m_drive.arcadeDrive(0, direction);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_drive.arcadeDrive(0, direction);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_drive.arcadeDrive(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return Math.abs(m_gyro.getAngle() - initAngle) > m_angle;
  }
}
