
package frc.robot.Command;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Subsystem.Intake.Intake;

public class setVoltageCommand extends Command {

  private double volts;
  private Timer timer;
  private double time;
  private boolean DoIWantTimer;

  public setVoltageCommand(double volts, double time, boolean DoIWantTimer) {
    addRequirements(Intake.getInstance());

    timer = new Timer();

    this.volts = volts;
    this.time = time;
    this.DoIWantTimer = DoIWantTimer;
  }

  @Override
  public void initialize() {
    timer.start();
  }

  @Override
  public void execute() {
    Intake.getInstance().setVoltage(volts);
  }

  @Override
  public void end(boolean interrupted) {
    Intake.getInstance().setVoltage(0);
  }

  @Override
  public boolean isFinished() {
    if (DoIWantTimer) {
      if (timer.get() >= time) {
        return true;
      }
    }
    
    return false;
  }
}
