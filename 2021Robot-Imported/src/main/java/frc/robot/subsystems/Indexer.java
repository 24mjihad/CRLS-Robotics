// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;

public class Indexer extends SubsystemBase {
  /** Creates a new Indexer. */
  public static WPI_VictorSPX indexer = new WPI_VictorSPX(RobotMap.Indexer);
  public Indexer() {

  }

  public void turnForward(){
    indexer.set(1);
  }

  public void turnBack(){
    indexer.set(-1);
  }
  
  public void stop(){
    indexer.set(0);
  }
  
}
