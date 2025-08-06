
package frc.robot;

import edu.wpi.first.wpilibj.PS5Controller;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Command.TestCommand;
import frc.robot.Command.TestCommand2;
import frc.robot.Command.TestCommand3;

public class RobotContainer {

  private PS5Controller controller;

  public RobotContainer() {
    controller = new PS5Controller(0);
    configureBindings();
  }

  private void configureBindings() {
    new Trigger(() -> controller.getCrossButton()).onTrue(new TestCommand());
    new Trigger(() -> controller.getSquareButton()).onTrue(new TestCommand2());
    new Trigger(() -> controller.getCircleButton()).onTrue(new TestCommand3());
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
