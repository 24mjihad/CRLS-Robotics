// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;

public class PID_Drive extends PIDSubsystem {
  /** Creates a new PID_Drive. */
  private Gyro pid_gyro;
  private Encoder m_encoder;
  public PID_Drive() {
    super(
        // The PIDController used by the subsystem
        new PIDController(1, 1, .25));
  }

  @Override
  public void useOutput(double output, double setpoint) {
    // Use the output here
  }

  @Override
  public double getMeasurement() {
    // Return the process variable measurement here
    return 0;
  }
}
