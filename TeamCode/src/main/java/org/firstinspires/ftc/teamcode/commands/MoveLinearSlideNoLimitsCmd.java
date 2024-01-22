package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.LinearSlideSub;

public class MoveLinearSlideNoLimitsCmd extends CommandBase {

    private final LinearSlideSub linearSlideSub;

    double speed;
    Telemetry telemetry;

    public MoveLinearSlideNoLimitsCmd(LinearSlideSub lss, Telemetry tm, double s){
        linearSlideSub = lss;
        addRequirements(linearSlideSub);
        telemetry = tm;
        speed = s;
    }

    @Override
    public void execute(){
        if (speed == 0){
            linearSlideSub.moveNoLimit(0);
            linearSlideSub.resetEncoder();
        }else{
            linearSlideSub.moveNoLimit(speed);
        }
    }

    @Override
    public boolean isFinished(){
        return true;
    }

}
