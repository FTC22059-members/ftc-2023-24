package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.subsystems.ArmSub;

/**
 * This command is dedicated to ejecting pixels through the intake
 */

public class ArmCmd extends CommandBase {

    private final ArmSub armSub;
    double speed;
    GamepadEx gamepad;
    Telemetry telemetry;

    /**
     * Intake pixel at a certain speed
     *
     * @param armSub Intake sub to import
     * @param speed Speed to eject at
     */


    public ArmCmd(ArmSub armSub, double speed, Telemetry tm, GamepadEx gp){
        this.armSub = armSub;
        this.speed = speed;
        gamepad = gp;
        telemetry = tm;
        addRequirements(armSub);
    }

    /**
     * Intake pixel at the default speed. This is an override function
     *
     * @param armSub Intake sub to import
     */

//    public ArmCmd(ArmSub armSub){ this(armSub, Constants.ArmConstants.armSpeedMultiplier);}

    public void setSpeed(double newSpeed){
        this.speed = newSpeed;
    }

    @Override
    public void execute(){

        if (gamepad.getTrigger(GamepadKeys.Trigger.LEFT_TRIGGER)>0 && (gamepad.getButton(GamepadKeys.Button.LEFT_BUMPER))) {
            this.armSub.arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER); // reset encoders to 0
            this.armSub.arm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }

        telemetry.addData("armPos", this.armSub.getCurrentPosition());

        this.armSub.setSpeed(this.speed);



    }

    @Override
    public boolean isFinished(){
        return true;
    }
}