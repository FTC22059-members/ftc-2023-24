package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.subsystems.IntakeSub;
import org.firstinspires.ftc.teamcode.subsystems.WristSub;

/**
 * This command is dedicated to setting the wrist angle
 */

public class WristCmd extends CommandBase {

    private final WristSub wristSub;
    double angle;

    /**
     * Set wrist to a certain angle
     *
     * @param wristSub Wrist sub to import
     * @param angle Angle
     */


    public WristCmd(WristSub wristSub, double angle){
        this.wristSub = wristSub;
        this.angle = angle;
        addRequirements(wristSub);
    }

    /**
     * Set wrist to default angle. This is an override function
     *
     * @param wristSub Wrist sub to import
     */

    public WristCmd(WristSub wristSub){
        this(wristSub, Constants.WristConstants.defaultWristAngle);
    }

    @Override
    public void execute(){
        this.wristSub.setSpeed(this.angle);
    }

    @Override
    public boolean isFinished(){
        return true;
    }
}