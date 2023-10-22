package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.subsystems.IntakeSub;

import java.util.function.DoubleSupplier;

public class IntakeCmd extends CommandBase {

    private final IntakeSub intakeSub;
    double speed = Constants.IntakeConstants.defaultIntakeSpeed;

    public IntakeCmd(IntakeSub is, double speed){
        this.intakeSub = is;
        this.speed = speed;
        addRequirements(is);
    }

    public IntakeCmd(IntakeSub is){
        this.intakeSub = is;
    }
    @Override
    public void initialize(){
        this.intakeSub.intake(this.speed);
    }

    @Override
    public boolean isFinished(){
        return true;
    }
}
