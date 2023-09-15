package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;

import org.firstinspires.ftc.teamcode.subsystems.DrivetrainSub;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

public class DriveCmd extends CommandBase {

    private final DrivetrainSub drivetrainSub;
    private final GamepadEx gamepad;
    private final DoubleSupplier angleDegrees;
    private final BooleanSupplier fieldCentric;

    public DriveCmd(DrivetrainSub dts, GamepadEx gp1, DoubleSupplier ad, BooleanSupplier fc){
        drivetrainSub = dts;
        gamepad = gp1;
        angleDegrees = ad;
        fieldCentric = fc;
        addRequirements(drivetrainSub);
    }

    @Override
    public void execute(){
        double brakeMultiplier = 1;
        double rightTrigger=gamepad.getTrigger(GamepadKeys.Trigger.RIGHT_TRIGGER);
        // if precision mode is on (the right trigger is pulled down to some degree)
        if (rightTrigger > 0.05 && rightTrigger < 0.75) {
            brakeMultiplier = 1 - rightTrigger;
            //telemetry.addData("Precise Mode", "On");
            // also if precision mode is on, but it caps brake at 25% after 75% trigger
        } else if (rightTrigger >= 0.75) {
            brakeMultiplier = 0.25;
            //telemetry.addData("Precise Mode", "On");
        }

        if (fieldCentric.getAsBoolean()) {
            // optional fifth parameter for squared inputs
            drivetrainSub.getDrive().driveFieldCentric(
                    gamepad.getLeftX()*brakeMultiplier,
                    gamepad.getLeftY()*brakeMultiplier,
                    gamepad.getRightX()*brakeMultiplier,
                    angleDegrees.getAsDouble(),   // gyro value passed in here must be in degrees
                    false
            ); ;
        } else {
            // optional fourth parameter for squared inputs
            drivetrainSub.getDrive().driveRobotCentric(
                    gamepad.getLeftX()*brakeMultiplier,
                    gamepad.getLeftY()*brakeMultiplier,
                    gamepad.getRightX()*brakeMultiplier,
                    false
            );
        }
    }
}
