package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.DrivetrainSub;
import org.firstinspires.ftc.teamcode.subsystems.LinearSlideSub;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

public class MoveLinearSlideCmd extends CommandBase {

    private final LinearSlideSub linearSlideSub;
    private final GamepadEx gamepad;

    Telemetry telemetry;

    public MoveLinearSlideCmd(LinearSlideSub lss, GamepadEx gp2, Telemetry tm){
        linearSlideSub = lss;
        gamepad = gp2;
        addRequirements(linearSlideSub);
        telemetry = tm;
    }

    @Override
    public void execute(){
        linearSlideSub.move(-1*gamepad.getRightY());
        telemetry.addData("getRightY", gamepad.getRightY());
    }
}
