/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj2.command.button.*;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.FunctionalCommand;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.StartEndCommand;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.interfaces.Gyro;

import frc.robot.Commands.*;
import frc.robot.subsystems.*;


/**
 * Add your docs here.
 */
public class RobotContainer {


    public Gyro gyro = new ADXRS450_Gyro();

    //subsystems 

    private final DriveTrain s_drivetrain   = new DriveTrain();
    private final Shooter    s_shooter      = new Shooter();
    private final Indexer    s_index        = new Indexer();
    private final Climber    s_climb        = new Climber();
    private final Gatherer   s_gather       = new Gatherer();

    //define joysticks
    
    private static final Joystick joystick1 = new Joystick(0);
    private static final Joystick joystick2 = new Joystick(1);

    // commands

    private final Driverpid    c_drive  = new Driverpid(joystick1, joystick2,s_drivetrain);
    private final Next_Ball    c_next   = new Next_Ball(s_index);
    //private final Shoot        c_shoot  = new Shoot(s_shooter);
    //private final Stop_shoot   c_sshoot = new Stop_shoot(s_shooter);
    private final Deploy_Shoot c_dshoot = new Deploy_Shoot(s_index, s_shooter);
    private final DriveCircle  c_dcirc  = new DriveCircle(s_drivetrain, gyro, true, 360);
    private final Auto_Race_Barrel c_Auto_Race_Barrel = new Auto_Race_Barrel(s_drivetrain, gyro);

    private final Command m_simpleAuto =
      new FunctionalCommand(
          s_drivetrain::resetEncoders,
          () -> s_drivetrain.tankDrive(.5,.5), 
          interrupt -> s_drivetrain.tankDrive(0, 0),
          () -> Math.abs(s_drivetrain.avgPosition()) >= 12,
          s_drivetrain
      );
    //define buttons on joystick
    private final JoystickButton 
        indexButton     = new JoystickButton(joystick1, RobotMap.b_Index),
        shootButton     = new JoystickButton(joystick1, RobotMap.b_Shooter),
        gatherButton    = new JoystickButton(joystick1, RobotMap.b_Gather),
        reverseButton   = new JoystickButton(joystick1, RobotMap.b_Direction),
        climbupButton   = new JoystickButton(joystick1, RobotMap.b_ClimbUp),
        climbdownButton = new JoystickButton(joystick1, RobotMap.b_ClimbDown),
        WindupButton    = new JoystickButton(joystick1, RobotMap.b_SpeedUp),
        WindownButton   = new JoystickButton(joystick1, RobotMap.b_SpeedDown),
        AutoR1Button    = new JoystickButton(joystick2, RobotMap.bc_Auto_Race1),
        AutoR2Button    = new JoystickButton(joystick2, RobotMap.bc_Auto_Race2),
        AutoR3Button    = new JoystickButton(joystick2, RobotMap.bc_Auto_Race3),
        AutoS1Button    = new JoystickButton(joystick2, RobotMap.bc_Auto_Search1),
        AutoS2Button    = new JoystickButton(joystick2, RobotMap.bc_Auto_Search2),     
        EncoderButton   = new JoystickButton(joystick1, RobotMap.b_9P),
        SortButton      = new JoystickButton(joystick1, RobotMap.b_10P),
        driveModeButton = new JoystickButton(joystick1, RobotMap.b_driveMode)
        ;

    //Add trigger conditionals
    private void configureButtonBindings() {
        indexButton  .whenPressed(      new Next_Ball(s_index));
        shootButton  .whenHeld(         new Deploy_Shoot(s_index, s_shooter))
                     .whenReleased(     new InstantCommand(s_shooter::m_stop, s_shooter));
        gatherButton .toggleWhenPressed(new StartEndCommand(s_gather::start, s_gather::stop, s_gather));
        AutoR1Button .whenPressed(      new Auto_Race_Barrel(s_drivetrain, gyro));
        AutoR2Button .whenPressed(      new Auto_Race_Slalom(s_drivetrain, gyro));
        AutoR3Button .whenPressed(      new Auto_Race_Bounce(s_drivetrain, gyro));
        AutoS1Button .whenPressed(      new Auto_Search_A(s_drivetrain, gyro,s_gather));
        AutoS2Button .whenPressed(      new Auto_Search_B(s_drivetrain, gyro,s_gather));
        reverseButton.whenPressed(      new InstantCommand(s_drivetrain::reverse, s_drivetrain));
        EncoderButton.whenPressed(      new InstantCommand(s_drivetrain::resetEncoders, s_drivetrain));
        SortButton   .whenPressed(      new InstantCommand(s_index::toggleSort, s_index));
        WindupButton .whenPressed(      new InstantCommand(s_shooter::m_distup, s_shooter));
        WindownButton.whenPressed(      new InstantCommand(s_shooter::m_distdown, s_shooter));
        driveModeButton.whenPressed(    new InstantCommand(s_drivetrain::toggleDriveMode, s_drivetrain));

    }

    //Constructor
    public RobotContainer(){
        configureButtonBindings();
        
        s_drivetrain.setDefaultCommand(
            new RunCommand(
                () -> s_drivetrain.Drive(
                    -1 * joystick1.getRawAxis(RobotMap.Yval),
                    -1 * joystick2.getRawAxis(RobotMap.Yval),
                    joystick1.getRawAxis(RobotMap.Xval)
                    ),
                s_drivetrain)
        );

    }


    // functions
    public Command getTeleopCommand() {
        // Returns command for autonomous control
        return c_drive;
    }
    public Command getAutonomousCommand() {
        return c_Auto_Race_Barrel;
    }
    
}
