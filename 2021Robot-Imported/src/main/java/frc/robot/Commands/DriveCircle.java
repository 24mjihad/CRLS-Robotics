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
  private double rightspd;
  private double leftspd;
  private int m_angle;

  public DriveCircle(DriveTrain drive, Gyro gyro, Boolean right, int angle) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_drive = drive;
    m_gyro = gyro;
    m_angle = angle;
    if(right){
      rightspd = .3;
      leftspd = .7;
    }
    else{
      rightspd = .7;
      leftspd = .3;
    }

    addRequirements(m_drive);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    initAngle = m_gyro.getAngle();
    m_drive.tankDrive(rightspd, leftspd);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_drive.tankDrive(rightspd, leftspd);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_drive.tankDrive(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return Math.abs(m_gyro.getAngle() - initAngle) > m_angle;
  }
}
