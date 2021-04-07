// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;


import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;

public class Shooter extends SubsystemBase {
  /** Creates a new Shooter. */
  private static  CANSparkMax tShooter = new CANSparkMax(RobotMap.TopShooter, MotorType.kBrushless);
  private static  CANSparkMax bShooter =  new CANSparkMax(RobotMap.BottomShooter, MotorType.kBrushless);

  //for calculations
  private static final double shooterWheelRadius = 0.051;
  private static final double shooterAngle = 65;
  private static final double shooterHeight = 0.475;
  private static final double shooterAngleRads = Math.toRadians(shooterAngle);
  private static int distance = 3;
  public Shooter() {}

  public static double Calculate(double distance) {
      double speed = 0.0; //Rad/s
      double speedRPM = 0.0; //RPM
      //Angle in Degrees for the Outer Port

      double numerator = -(9.81/2) * Math.pow(distance, 2);
      double denominator = ((2.49 - shooterHeight) - (distance * Math.tan(shooterAngleRads))) * Math.pow(Math.cos(shooterAngleRads), 2);

      speed = ((1/shooterWheelRadius) * Math.sqrt(numerator / denominator)); // Rads/Sec for the wheel
      speedRPM = speed * (30/(Math.PI)); //RPM Conversion
      //System.out.printf("Wheel Speed: %s RPM\nWheel Tangential Velocity is: %s m/s\nMotor Speed: %s RPM\nMotor Tangential Velocity: %s m/s\n", speedRPM, speed*shooterWheelRadius, speedRPM * 3, speed * 3 * shooterWheelRadius);
      return (speedRPM*3)/6000;
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
  public void m_shoot(){
    double shooterSpeed = Calculate(distance);
    tShooter.set(shooterSpeed);
    bShooter.set(shooterSpeed);
  }
  public void m_stop(){
    tShooter.set(0);
    bShooter.set(0);
  }
  public void m_distup(){
    distance = distance + 1;
  }
  public void m_distdown(){
    if(distance>0){
      distance = distance - 1;
    }
  }

}
