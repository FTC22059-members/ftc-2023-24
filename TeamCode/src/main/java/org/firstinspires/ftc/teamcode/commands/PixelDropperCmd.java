package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.subsystems.PixelDropperSub;

/**
 * This command is dedicated to dropper pixels through the depositor
 */

public class PixelDropperCmd extends CommandBase {

    private final PixelDropperSub pixelDropperSub;
    double speed = Constants.IntakeConstants.defaultIntakeSpeed;

    /**
     * Command to drop the pixel
     *
     * @param pixelDropperSubParam The pixel dropper sub to be imported
     */

    public PixelDropperCmd(PixelDropperSub pixelDropperSubParam){
        this.pixelDropperSub = pixelDropperSubParam;
        addRequirements(pixelDropperSubParam);
    }

    @Override
    public void execute(){
        this.pixelDropperSub.dropPixel();
    }

    @Override
    public boolean isFinished(){
        return true;
    }
}
