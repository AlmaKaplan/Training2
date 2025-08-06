
package frc.robot.Command;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Subsystem.Intake.Intake;
import static edu.wpi.first.wpilibj2.command.Commands.*;


public class TestCommand2 extends SequentialCommandGroup {
  
  public TestCommand2() {
    addRequirements(Intake.getInstance());
    addCommands(
      runOnce(( () -> Intake.getInstance().setVoltage(4))),
      waitUntil(() -> Intake.getInstance().getAvrageVolts() > 3.5),
      runOnce(() -> System.out.println("3.5 Passed")),
      waitUntil(() -> Intake.getInstance().getVelocity() >= 1200),
      new setVoltageCommand(4, 4, true)
      );
  }
}
