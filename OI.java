/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj2.command.RunCommand;

/**
 * Add your docs here.
 */
public class OI 
{
  private final DriveTrain driveTrain = new DriveTrain();

  public final PowerDistributionPanel m_pdp = new PowerDistributionPanel(51);
  public static Joystick driveJoystick = new Joystick(1);

  public OI()
  {
   // configureButtonBindings();
    driveTrain.setDefaultCommand(
      new RunCommand(() -> driveTrain
      .drive(driveJoystick.getX(),driveJoystick.getY()),driveTrain)
      );
  }
 /*
  private void configureButtonBindings()
  {

  }
  */
}
