/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANEncoder;

import java.lang.Math;

//import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;


import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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
  private static Boolean drivemode = true;
  //pcf translates rotations -> distance travelled
  private final double pcf = 1.7525;//1.789214//1.7525; // 6 in * 3.14 * 10.75 gear ratio / 2

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
    return (En_L.getPosition() + -1 * En_R.getPosition())/2.0;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("EncoderL", En_L.getPosition());
    SmartDashboard.putNumber("EncoderR", -1 * En_R.getPosition());
  }
  
  public void tankDrive(double lspeed, double rspeed){
    m_drive.tankDrive(lspeed, rspeed);
  }

  public void arcadeDrive(double rspeed, double rotation){
    m_drive.arcadeDrive(rspeed, rotation, true);
  }

  public void toggleDriveMode(){
    drivemode = !drivemode;
  }

  public void Drive(double lspeed, double rspeed, double rotation){
    if(drivemode){
      tankDrive(Math.signum(lspeed) * lspeed * lspeed,
                Math.signum(rspeed) * rspeed * rspeed);
    }
    else{
      arcadeDrive(Math.signum(rspeed) * rspeed * rspeed, rotation);
    }
  }
}