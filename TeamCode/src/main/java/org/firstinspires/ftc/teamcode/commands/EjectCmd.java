package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.subsystems.OldIntakeSub;

/**
 * This command is dedicated to ejecting pixels through the output
 */

public class EjectCmd extends CommandBase {

    private final OldIntakeSub intakeSub;
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

    public EjectCmd(OldIntakeSub intakeSubParam, double speedParam){
        this.intakeSub = intakeSubParam;
        this.speed = speedParam;
        addRequirements(intakeSubParam);

    }

    /**
     * Eject pixel at the default speed. This is an override function
     *
     * @param intakeSubParam Intake sub to import
     */

    public EjectCmd(OldIntakeSub intakeSubParam){
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
        System.out.println(System.currentTimeMillis()+"; "+startTime+"; "+(System.currentTimeMillis()-startTime));
        return System.currentTimeMillis()-startTime>runDuration;
    }

    @Override
    public void end(boolean interrupted){
        this.intakeSub.setSpeed(0);
    }
}
