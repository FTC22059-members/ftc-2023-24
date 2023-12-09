package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.subsystems.ArmSub;
import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * This command is dedicated to ejecting pixels through the intake
 */

public class ArmDistanceCmd extends CommandBase {

    private final ArmSub armSub;
    private Telemetry telemetry;
    double speed;
    int ticks;

    /**
     * Intake pixel at a certain speed
     *
     * @param armSub Intake sub to import
     * @param speed Speed to eject at
     */

    public ArmDistanceCmd(ArmSub armSub, Telemetry telemetry, double speed, int ticks){
        this.armSub = armSub;
        this.telemetry = telemetry;
        this.speed = speed;
        addRequirements(armSub);
    }

    @Override
    public void execute(){
        this.armSub.setSpeed(this.speed);
        telemetry.addData("Ticks",this.armSub.getCurrentPosition());
    }

    @Override
    public boolean isFinished(){
        return this.armSub.getCurrentPosition()>ticks;
    }
}