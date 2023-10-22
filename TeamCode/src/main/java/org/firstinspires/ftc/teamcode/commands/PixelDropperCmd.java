package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.subsystems.PixelDropperSub;

public class PixelDropperCmd extends CommandBase {

    private final PixelDropperSub pixelDropperSub;
    double speed = Constants.IntakeConstants.defaultIntakeSpeed;

    public PixelDropperCmd(PixelDropperSub pds){
        this.pixelDropperSub = pds;
        addRequirements(pds);
    }
    @Override
    public void initialize(){
        this.pixelDropperSub.dropPixel();
        System.out.println("PIXEL DROPPER CMD");
    }

    @Override
    public boolean isFinished(){
        return true;
    }
}
