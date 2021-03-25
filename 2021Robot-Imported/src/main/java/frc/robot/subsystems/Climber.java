/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;

public class Climber extends SubsystemBase {
  /**
   * Creates a new Climber.
   */
  private final static CANSparkMax wind = new CANSparkMax(RobotMap.DepClimb,MotorType.kBrushless);
//  public static WPI_VictorSPX deploy = new WPI_VictorSPX(RobotMap.RetClimb); 
  public Climber() {

  }


  public void deployup(){
//    deploy.set(1);
  }

  public void winddown(){
    wind.set(-1);
  }
  public void deploydown(){
 //   deploy.set(-1);
  }
  public void windup(){
    wind.set(1);
  }
  public void stop(){
    wind.set(0);
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
