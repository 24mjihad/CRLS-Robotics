/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import frc.robot.Commands.Driverpid;
import frc.robot.Commands.Next_Ball;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.PID_Drive;
import frc.robot.subsystems.Shooter;

/**
 * Add your docs here.
 */
public class RobotContainer {
    
//subsystems 

    private final static DriveTrain m_drivetrain = new DriveTrain();
    private final Shooter m_shooter = new Shooter();
    public static final Joystick joyStick = new Joystick(0);
    public static final Joystick JOYSTICK2 = new Joystick(1);
    public static Gyro gyro;
    public static PID_Drive pid = new PID_Drive(1, 1, 1, gyro);
    public static Indexer index = new Indexer();

    // commands

    public static Driverpid drive = new Driverpid(joyStick, JOYSTICK2,m_drivetrain);
    public static Next_Ball next = new Next_Ball(index);

    //triggers
    public static JoystickButton exampleButton = new JoystickButton(joyStick, RobotMap.Indexer);
    // functions
    public static Driverpid getAutonomousCommand() {
        // Returns command for autonomous control
        return drive;
    }
    
}
