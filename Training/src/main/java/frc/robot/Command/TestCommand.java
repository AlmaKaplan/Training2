package frc.robot.Command;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Subsystem.Intake.Intake;

import static edu.wpi.first.wpilibj2.command.Commands.*;

public class TestCommand extends SequentialCommandGroup {
  
  public TestCommand() {
    addRequirements(Intake.getInstance());
    addCommands(
      new WaitCommand(3),
      runOnce(() -> System.out.println("Start")),
      new setVoltageCommand(4, 3),
      runOnce(() -> System.out.println("Stage Two")),
      new setVoltageCommand(2, 2),
      runOnce(() -> System.out.println("end"))
      );
  }
}
