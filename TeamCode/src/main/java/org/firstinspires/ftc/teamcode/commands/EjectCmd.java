package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.subsystems.IntakeSub;

import java.util.function.DoubleSupplier;

public class EjectCmd extends CommandBase {

    private final IntakeSub intakeSub;
    double speed = Constants.IntakeConstants.defaultIntakeSpeed;

    public EjectCmd(IntakeSub is, double speed){
        this.intakeSub = is;
        this.speed = speed;
        addRequirements(is);
    }

    public EjectCmd(IntakeSub is){
        this.intakeSub = is;
    }

    @Override
    public void initialize(){
        this.intakeSub.intake(-1*this.speed);
        System.out.println("Eject CMD");
    }

    @Override
    public boolean isFinished(){
        return true;
    }
}
