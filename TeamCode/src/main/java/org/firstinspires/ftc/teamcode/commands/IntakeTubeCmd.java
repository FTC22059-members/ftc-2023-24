package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.subsystems.IntakeSub;

public class IntakeTubeCmd extends CommandBase {

    private final IntakeSub newIntakeSub;
    private final GamepadEx gamepad2;

    Telemetry telemetry;

    public IntakeTubeCmd(IntakeSub nis, GamepadEx gp2, Telemetry tm) {
        newIntakeSub = nis;
        gamepad2 = gp2;
        addRequirements(newIntakeSub);
        telemetry = tm;
    }

    @Override
    public void execute() {
        double rightSpeed = Constants.DriveConstants.intakeMaxSpeed *
                gamepad2.getTrigger(GamepadKeys.Trigger.RIGHT_TRIGGER);
        telemetry.addData("Right Trigger", rightSpeed);
        double leftSpeed = Constants.DriveConstants.intakeMaxSpeed *
                gamepad2.getTrigger(GamepadKeys.Trigger.LEFT_TRIGGER);
        telemetry.addData("Left Trigger", leftSpeed);
        newIntakeSub.setSpeed(leftSpeed - rightSpeed);
    }
}
