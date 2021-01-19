/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Shooter;

/**
 * Add your docs here.
 */
public class RobotContainer {
    
//subsystems 

    private final DriveTrain m_drivetrain = new DriveTrain();
    private final Shooter m_shooter = new Shooter();
    public static final Joystick joyStick = new Joystick(0);

//commands



//functions
    public Command getAutonomousCommand() {
        // Returns command for autonomous control
        return null;
    }
    
}
