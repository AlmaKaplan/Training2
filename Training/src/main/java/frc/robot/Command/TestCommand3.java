
package frc.robot.Command;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;

public class TestCommand3 extends SequentialCommandGroup {
  public TestCommand3() {
    addCommands(
      new ParallelCommandGroup(
        new setVoltageCommand(4, 0, false),
        new chekPeriadic()),
      new WaitCommand(4),
      new setVoltageCommand(0, 0, true)
    );
  }
}
