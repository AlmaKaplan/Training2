
package frc.robot.Subsystem.Intake;

import com.MAutils.DashBoard.DashBoard;
import com.MAutils.DashBoard.DashBoardTab;
import com.MAutils.Logger.MALog;
import com.ctre.phoenix6.BaseStatusSignal;
import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.units.measure.AngularVelocity;
import edu.wpi.first.units.measure.Current;
import edu.wpi.first.units.measure.Voltage;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Intake extends SubsystemBase {
  private static Intake intake;

  private TalonFX rightMotor;
  private TalonFXConfiguration rightConfig;

  private TalonFX leftMotor;
  private TalonFXConfiguration leftConfig;


  private StatusSignal<AngularVelocity> rightVelocity;
  private StatusSignal<Current> rightCurrent;
  private StatusSignal<Voltage> rightVoltage;

  private StatusSignal<AngularVelocity> leftVelocity;
  private StatusSignal<Current> leftCurrent;
  private StatusSignal<Voltage> leftVoltage;

  private DashBoardTab intakeTab;

  private double num;

  private Intake() {
    rightMotor = new TalonFX(IntakeConstants.RIGHT_MOTOR_ID);
    leftMotor = new TalonFX(IntakeConstants.LEFT_MOTOR_ID);

    rightConfig = new TalonFXConfiguration();
    leftConfig = new TalonFXConfiguration();

    
    rightCurrent = rightMotor.getStatorCurrent();
    rightVelocity = rightMotor.getVelocity();
    rightVoltage = rightMotor.getMotorVoltage();

    leftCurrent = rightMotor.getStatorCurrent();
    leftVelocity = rightMotor.getVelocity();
    leftVoltage = rightMotor.getMotorVoltage();

    intakeTab = new DashBoardTab("intake");

    num = 1;

    rightMotorConfig();
    leftMotorConfig();
  }

  private void rightMotorConfig() {
    rightConfig.Feedback.RotorToSensorRatio = IntakeConstants.GEAR;

    rightConfig.MotorOutput.Inverted = InvertedValue.CounterClockwise_Positive;
    rightConfig.MotorOutput.NeutralMode = NeutralModeValue.Brake;

    rightMotor.getConfigurator().apply(rightConfig);
  }

  private void leftMotorConfig() {
    leftConfig.Feedback.RotorToSensorRatio = IntakeConstants.GEAR;

    leftConfig.MotorOutput.Inverted = InvertedValue.Clockwise_Positive;
    leftConfig.MotorOutput.NeutralMode = NeutralModeValue.Coast;

    leftMotor.getConfigurator().apply(leftConfig);
  }

  public void setVoltage(double volt) {
    leftMotor.setVoltage(volt*num);
    rightMotor.setVoltage(volt*num);
  }

  public void stop() {
    leftMotor.setVoltage(0);
    rightMotor.setVoltage(0);
  }

  public double getVelocity() {
    return (leftVelocity.getValueAsDouble()+rightVelocity.getValueAsDouble())/2;
  }

  public double getAvrageCurrent() {
    return (leftCurrent.getValueAsDouble()+rightCurrent.getValueAsDouble())/2;
  }
  
  public double getAvrageVolts() {
    return (rightVoltage.getValueAsDouble()+leftVoltage.getValueAsDouble())/2;
  }

  public double getLeftCurrent() {
    return leftCurrent.getValueAsDouble();
  }

  public double getRightCurrent() {
    return rightCurrent.getValueAsDouble();
  }

  public double getLeftVoltage() {
    return leftVoltage.getValueAsDouble();
  }

  public double getRightVoltage() {
    return rightVoltage.getValueAsDouble();
  }

  public static Intake getInstance() {
    if (intake == null) {
      intake = new Intake();
    }
    return intake;
  }

  @Override
  public void periodic() {
    BaseStatusSignal.refreshAll(rightCurrent, rightVelocity, rightVoltage, leftCurrent, leftVelocity, leftVoltage);

    MALog.log("Subsystem/Intake/avrege Velocity",getVelocity());
    MALog.log("Subsystem/Intake/avrege current",getAvrageCurrent());
    MALog.log("Subsystem/Intake/avrege voltage",getLeftVoltage());

    MALog.log("Subsystem/Intake/avrege Velocity",getVelocity());

    intakeTab.addNum("volt Num", num);
    intakeTab.getNum("volt Num");
  }
}
