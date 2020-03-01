/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveTrain extends SubsystemBase 
{
  private DifferentialDrive m_differentialDrive;
// public AHRS m_gyro;
  public WPI_TalonSRX m_frontLeft;
  public WPI_TalonSRX m_frontRight;
  public WPI_TalonSRX m_backLeft;
  public WPI_TalonSRX m_backRight;
  private SpeedControllerGroup m_leftSideDifferentialGroup;
  private SpeedControllerGroup m_rightSideDifferentialGroup;

  //private Boolean m_driveStraight = false;
  //private double m_angleSetPoint = 0.0; 
  //private static final double c_kPcorrection = 0.05; 
  /**
   * Creates a new DriveTrain.
   */
  public DriveTrain() {
    m_frontLeft = new WPI_TalonSRX(RobotMap.driveTalonFrontLeft);
    m_frontRight = new WPI_TalonSRX(RobotMap.driveTalonFrontRight);
    m_backLeft = new WPI_TalonSRX(RobotMap.driveTalonBackLeft); 
    m_backRight = new WPI_TalonSRX(RobotMap.driveTalonBackRight); 

    m_leftSideDifferentialGroup = new SpeedControllerGroup(m_frontLeft, m_backLeft);
    m_rightSideDifferentialGroup = new SpeedControllerGroup(m_frontRight, m_backRight);
 //   m_gyro = new AHRS(Port.kMXP);

    initTank();
  }
  public void initTank()
  {
    m_differentialDrive = new DifferentialDrive(m_leftSideDifferentialGroup, m_rightSideDifferentialGroup);
    m_differentialDrive.setDeadband(RobotMap.c_deadBand);
  }

  public double getEncPos()
  {
    int frontLeftEnc = -m_frontLeft.getSelectedSensorPosition(0);
    int frontRightEnc = m_frontRight.getSelectedSensorPosition(0);
    int backLeftEnc = -m_backLeft.getSelectedSensorPosition(0);
    int backRightEnc = m_backRight.getSelectedSensorPosition(0);
    SmartDashboard.putNumber("Front Left Enc", frontLeftEnc);
    SmartDashboard.putNumber("Front Right Enc", frontRightEnc);
    SmartDashboard.putNumber("Back Left Enc", backLeftEnc);
    SmartDashboard.putNumber("Back Right Enc", backRightEnc);
    double position = ((frontLeftEnc + frontRightEnc + backLeftEnc + backRightEnc) / 4);

    return position;
  }
/*
  public void enableDriveStraight()
  {
    m_driveStraight = true; 
    m_angleSetPoint = m_gyro.getYaw(); 
  }
  public void disableDriveStraight()
  {
    m_driveStraight = false; 
  }
*/
  public void drive(double x, double y)
  {
    m_differentialDrive.arcadeDrive(y, -x);
  }
  @Override
  public void periodic() 
  {
    // This method will be called once per scheduler run
  }
}
