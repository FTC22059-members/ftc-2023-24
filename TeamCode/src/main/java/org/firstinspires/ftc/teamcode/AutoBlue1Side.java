package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.commands.EjectCmd;
import org.firstinspires.ftc.teamcode.commands.PixelDropperCmd;

@Autonomous(name = "Blue1Side", group = "Blue")

public class AutoBlue1Side extends AutoCommandOpMode {
    @Override
    public void leftLogic() {
        schedule(new SequentialCommandGroup(
                drive(22)
                , turnCCW(75)
                , armDown
                , new EjectCmd(intake)
                , armNeutral
                , turnCW(30)
                , drive(-26)
                , turnCCW(41)
                , drive(65)
                , turnCW(30)
                , drive(18)
                , drive(22)
                //, new DriveAprilTagCmd(4, aprilTagVisionPortal.getVisionProcessor(), drive, telemetry)
                , turnCCW(180)
                , new PixelDropperCmd(pixelDropper)
                , new PixelDropperCmd(pixelDropper)
        ));
    }

    @Override
    public void centerLogic() {
        schedule(new SequentialCommandGroup(
                drive(20)
                , armDown
                , drive(4)
                , new EjectCmd(intake)
                , armNeutral
                , drive(-2)
                , turnCCW(45)
                , drive(-23)
                , turnCCW(36)
                , drive(72)
                , turnCW(20)
                , drive(24)
                //, new DriveAprilTagCmd(5, aprilTagVisionPortal.getVisionProcessor(), drive, telemetry)
                , turnCCW(180)
                , new PixelDropperCmd(pixelDropper)
                , new PixelDropperCmd(pixelDropper)
        ));
    }

    @Override
    public void rightLogic() {
        schedule(new SequentialCommandGroup(
                drive(24)
                , turnCW(75)
                , armDown
                , new EjectCmd(intake)
                , armNeutral
                , drive(-1)
                , turnCCW(75)
                , turnCCW(43)
                , drive(-27)
                , turnCCW(38)
                , drive(65)
                , turnCW(30)
                , drive(18)
                , drive(16)
                //, new DriveAprilTagCmd(6, aprilTagVisionPortal.getVisionProcessor(), drive, telemetry)
                , turnCCW(180)
                , new PixelDropperCmd(pixelDropper)
                , new PixelDropperCmd(pixelDropper)
        ));
    }
}