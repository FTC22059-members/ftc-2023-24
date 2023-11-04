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
     * @param pds
     */

    public PixelDropperCmd(PixelDropperSub pds){
        this.pixelDropperSub = pds;
        addRequirements(pds);
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
