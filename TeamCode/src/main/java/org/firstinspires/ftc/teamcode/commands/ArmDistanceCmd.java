package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.ArmSub;
import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * This command is dedicated to ejecting pixels through the intake
 */

public class ArmDistanceCmd extends CommandBase {

    private final ArmSub armSub;
    private Telemetry telemetry;
    private double speed;
    private int position;

    /**
     * Intake pixel at a certain speed
     *
     * @param armSub Intake sub to import
     * @param speed Speed to eject at
     */

    public ArmDistanceCmd(ArmSub armSub, Telemetry telemetry, double speed, int position){
        this.armSub = armSub;
        this.telemetry = telemetry;
        this.speed = speed;
        this.position=position;

        addRequirements(armSub);
    }

    @Override
    public void execute(){
        this.armSub.setSpeed(this.speed);
        telemetry.addData("Position",this.armSub.getCurrentPosition());
    }

    @Override
    public boolean isFinished(){
        if(speed<0){
            return this.armSub.getCurrentPosition()>=position;
        }else{
            return this.armSub.getCurrentPosition()<=position;
        }
    }

    @Override
    public void end(boolean interrupted) {
        this.armSub.setSpeed(0);
    }
}