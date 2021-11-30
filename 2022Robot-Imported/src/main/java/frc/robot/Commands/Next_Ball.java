// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Commands;

import edu.wpi.first.wpilibj.Timer;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Indexer;

public class Next_Ball extends CommandBase{
  /** Creates a new Next_Ball. */
  private static double time = 0;
  private final Indexer m_index;
  public Next_Ball(Indexer indexer) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_index = indexer;
    addRequirements(m_index);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    time = Timer.getFPGATimestamp();
    m_index.turnForward();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_index.stop();
  }
  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return time - Timer.getFPGATimestamp() < -2.4;
  }
}
