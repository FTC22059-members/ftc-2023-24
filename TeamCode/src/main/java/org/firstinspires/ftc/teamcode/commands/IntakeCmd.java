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
     * @param intakeSubParam Intake sub to import
     * @param speedParam Speed to eject at
     */


    public IntakeCmd(IntakeSub intakeSubParam, double speedParam){
        this.intakeSub = intakeSubParam;
        this.speed = speedParam;
        addRequirements(intakeSubParam);
    }

    /**
     * Intake pixel at the default speed. This is an override function
     *
     * @param intakeSubParam Intake sub to import
     */

    public IntakeCmd(IntakeSub intakeSubParam){
        this(intakeSubParam, Constants.IntakeConstants.defaultIntakeSpeed);
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
