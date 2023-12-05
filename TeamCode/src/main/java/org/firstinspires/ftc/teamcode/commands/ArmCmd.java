package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.subsystems.ArmSub;

/**
 * This command is dedicated to ejecting pixels through the intake
 */

public class ArmCmd extends CommandBase {

    private final ArmSub armSub;
    double speed;

    /**
     * Intake pixel at a certain speed
     *
     * @param armSub Intake sub to import
     * @param speed Speed to eject at
     */


    public ArmCmd(ArmSub armSub, double speed){
        this.armSub = armSub;
        this.speed = speed;
        addRequirements(armSub);
    }

    /**
     * Intake pixel at the default speed. This is an override function
     *
     * @param armSub Intake sub to import
     */

    public ArmCmd(ArmSub armSub){
        this(armSub, Constants.ArmConstants.armSpeedMultiplier);
    }

    public void setSpeed(double newSpeed){
        this.speed = newSpeed;
    }

    @Override
    public void execute(){
        this.armSub.setSpeed(this.speed);
    }

    @Override
    public boolean isFinished(){
        return true;
    }
}