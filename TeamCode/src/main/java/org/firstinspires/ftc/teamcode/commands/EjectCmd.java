package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.subsystems.IntakeSub;

import java.util.function.DoubleSupplier;

/**
 * This command is dedicated to ejecting pixels through the output
 */

public class EjectCmd extends CommandBase {

    private final IntakeSub intakeSub;
    double speed;

    /**
     * Eject pixel at a certain speed
     *
     * @param is Intake sub to import
     * @param speed Speed to eject at
     */

    public EjectCmd(IntakeSub is, double speed){
        this.intakeSub = is;
        this.speed = speed;
        addRequirements(is);
    }

    /**
     * Eject pixel at the default speed. This is an override function
     *
     * @param is Intake sub to import
     */

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
