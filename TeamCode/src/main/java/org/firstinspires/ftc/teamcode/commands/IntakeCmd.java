package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.subsystems.IntakeSub;

import java.util.function.DoubleSupplier;

/**
 * This command is dedicated to ejecting pixels through the intake
 */

public class IntakeCmd extends CommandBase {

    private final IntakeSub intakeSub;
    double speed;

    /**
     * Intake pixel at a certain speed
     *
     * @param is Intake sub to import
     * @param speed Speed to eject at
     */


    public IntakeCmd(IntakeSub is, double speed){
        this.intakeSub = is;
        this.speed = speed;
        addRequirements(is);
    }

    /**
     * Intake pixel at the default speed. This is an override function
     *
     * @param is Intake sub to import
     */

    public IntakeCmd(IntakeSub is){
        this(is, Constants.IntakeConstants.defaultIntakeSpeed);
    }

    @Override
    public void execute(){
        this.intakeSub.intake(this.speed);
    }

    @Override
    public boolean isFinished(){
        return true;
    }
}
