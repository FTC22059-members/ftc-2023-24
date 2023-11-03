package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.subsystems.IntakeSub;

import java.util.function.DoubleSupplier;

public class EjectCmd extends CommandBase {

    private final IntakeSub intakeSub;
    double speed;

    public EjectCmd(IntakeSub is, double speed){
        this.intakeSub = is;
        this.speed = speed;
        addRequirements(is);
    }

    public EjectCmd(IntakeSub is){
        this(is, Constants.IntakeConstants.defaultIntakeSpeed);
    }

    @Override
    public void execute(){
        this.intakeSub.intake(-1*this.speed);
    }

    @Override
    public boolean isFinished(){
        return true;
    }
}
