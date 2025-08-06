
package frc.robot.Command;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Subsystem.Intake.Intake;

public class chekPeriadic extends Command {
  private int left = 0;
  private int right = 0;

  public chekPeriadic() {
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    if(Intake.getInstance().getLeftVelocity() >= 1200 && left < 1) {
      System.out.println("Left1200");
      left++;
    } else if(Intake.getInstance().getRightVelocity() >= 1200 && right < 1) {
      System.out.println("Right1200");
      right ++;
    }
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    if (left + right >= 2) {
      return true;
    }
    return false;
  }
}
