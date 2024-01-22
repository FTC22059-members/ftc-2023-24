package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.subsystems.IntakeSub;

/**
 * This command is dedicated to ejecting pixels through the output
 */

public class EjectCmd extends CommandBase {

    private final IntakeSub intakeSub;
    double speed;
    long startTime;
    int runDuration = 2000;
    boolean isStarted = false;

    /**
     * Eject pixel at a certain speed
     *
     * @param intakeSubParam Intake sub to import
     * @param speedParam Speed to eject at
     */

    public EjectCmd(IntakeSub intakeSubParam, double speedParam){
        this.intakeSub = intakeSubParam;
        this.speed = speedParam;
        addRequirements(intakeSubParam);

    }

    /**
     * Eject pixel at the default speed. This is an override function
     *
     * @param intakeSubParam Intake sub to import
     */

    public EjectCmd(IntakeSub intakeSubParam){
        this(intakeSubParam, Constants.IntakeConstants.defaultIntakeSpeed);
    }

    @Override
    public void execute(){
        if(!isStarted){
            startTime=System.currentTimeMillis();
            isStarted=true;
        }
        this.intakeSub.setSpeed(-1*this.speed);
    }

    @Override
    public boolean isFinished(){
        return System.currentTimeMillis()-startTime>runDuration;
    }

    @Override
    public void end(boolean interrupted){
        this.intakeSub.setSpeed(0);
    }
}
