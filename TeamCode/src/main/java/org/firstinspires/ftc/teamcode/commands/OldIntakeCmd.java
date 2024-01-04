package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.subsystems.OldIntakeSub;

/**
 * This command is dedicated to ejecting pixels through the intake
 */

public class OldIntakeCmd extends CommandBase {

    private final OldIntakeSub intakeSub;
    double speed;

    /**
     * Intake pixel at a certain speed
     *
     * @param intakeSub Intake sub to import
     * @param speed Speed to eject at
     */


    public OldIntakeCmd(OldIntakeSub intakeSub, double speed){
        this.intakeSub = intakeSub;
        this.speed = speed;
        addRequirements(intakeSub);
    }

    /**
     * Intake pixel at the default speed. This is an override function
     *
     * @param intakeSub Intake sub to import
     */

    public OldIntakeCmd(OldIntakeSub intakeSub){
        this(intakeSub, Constants.IntakeConstants.defaultIntakeSpeed);
    }

    @Override
    public void execute(){
        this.intakeSub.setSpeed(this.speed);
    }

    @Override
    public boolean isFinished(){
        return true;
    }
}