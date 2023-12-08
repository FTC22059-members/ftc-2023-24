package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.subsystems.PixelDropperSub;

/**
 * This command is dedicated to dropper pixels through the depositor
 */

public class PixelDropperCmd extends CommandBase {

    private final PixelDropperSub pixelDropperSub;
    double speed;

    /**
     * Command to drop the pixel
     *
     * @param pixelDropperSub The pixel dropper sub to be imported
     */

    public PixelDropperCmd(PixelDropperSub pixelDropperSub, double speed){
        this.pixelDropperSub = pixelDropperSub;
        this.speed = speed*-1;
        addRequirements(pixelDropperSub);
    }

    public PixelDropperCmd(PixelDropperSub pixelDropperSub){
        this(pixelDropperSub, Constants.PixelDropperConstants.defaultPixelDropperSpeed);
    }

    @Override
    public void execute(){
        this.pixelDropperSub.setSpeed(this.speed);
    }

    @Override
    public boolean isFinished(){
        return true;
    }
}
