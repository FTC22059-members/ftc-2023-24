package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;

import org.firstinspires.ftc.teamcode.subsystems.DrivetrainSub;
import org.firstinspires.ftc.teamcode.subsystems.LinearSlideSub;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

public class MoveLinearSlideCmd extends CommandBase {

    private final LinearSlideSub linearSlideSub;
    private final GamepadEx gamepad;

    public MoveLinearSlideCmd(LinearSlideSub lss, GamepadEx gp2){
        linearSlideSub = lss;
        gamepad = gp2;
        addRequirements(linearSlideSub);
    }

    @Override
    public void execute(){
        linearSlideSub.move(gamepad.getLeftY());
    }
}
