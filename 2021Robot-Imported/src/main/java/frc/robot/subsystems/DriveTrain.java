/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANEncoder;

//import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
/**
 * Add your docs here.
 */
public class DriveTrain extends SubsystemBase {
  
  private final static CANSparkMax FL = new CANSparkMax(RobotMap.FL,MotorType.kBrushless);
  private final static CANSparkMax BL = new CANSparkMax(RobotMap.BL,MotorType.kBrushless);
  private final static CANSparkMax FR = new CANSparkMax(RobotMap.FR,MotorType.kBrushless);
  private final static CANSparkMax BR = new CANSparkMax(RobotMap.BR,MotorType.kBrushless);
  private final CANEncoder En_R = FR.getEncoder();
  private final CANEncoder En_L = FL.getEncoder();

  private final double pcf = 1.7525; // 6 in * 3.14 * 10.75 gear ratio

  static SpeedControllerGroup MR = new SpeedControllerGroup(BR, FR);
  static SpeedControllerGroup ML = new SpeedControllerGroup(BL, FL);

  private final static DifferentialDrive m_drive = new DifferentialDrive(ML, MR);

  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public DriveTrain(){
    En_R.setPositionConversionFactor(pcf);
    En_L.setPositionConversionFactor(pcf);
  }
  
  public void resetEncoders(){
    En_R.setPosition(0);
    En_L.setPosition(0);
  }

  public double avgPosition(){
    return (En_L.getPosition() + En_R.getPosition())/2.0;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
  
  public void tankDrive(double lspeed, double rspeed){
    m_drive.tankDrive(lspeed, rspeed);
  }
}