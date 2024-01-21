package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.subsystems.ArmSub;

/**
 * This command is dedicated to setting the wrist angle
 */

public class TrimWristCmd extends CommandBase {

    private final ArmSub wristSub;
    double angle;

    /**
     * Set wrist to a certain angle
     *
     * @param armSub Wrist sub to import
     * @param angle Angle
     */


    public TrimWristCmd(ArmSub armSub, double angle){
        this.wristSub = armSub;
        this.angle = angle;
        addRequirements(armSub);
    }

    /**
     * Set wrist to default angle. This is an override function
     *
     * @param armSub Wrist sub to import
     */

    public TrimWristCmd(ArmSub armSub){
        this(armSub, Constants.ArmConstants.wristTrimSpeed);
    }

    @Override
    public void execute(){
        this.wristSub.trimWrist(this.angle);
    }

    @Override
    public boolean isFinished(){
        return true;
    }
}
