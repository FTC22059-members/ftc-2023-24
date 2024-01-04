package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.commands.EjectCmd;
import org.firstinspires.ftc.teamcode.commands.PixelDropperCmd;

@Autonomous(name = "Red1Stage", group = "Red")

public class AutoRed1Stage extends AutoCommandOpMode {
    @Override
    public void leftLogic() {
        schedule(new SequentialCommandGroup(
                drive(24)
                , turnCCW(75)
                , armDown
                //, drive(-2)
                , new EjectCmd(intake)
                , armNeutral
                //, drive(-2)
                , turnCW(75)
                , drive(24)
                , turnCW(90)
                , drive(60)
                , turnCW(45)
                , drive(12)
                , armUp
                //, new DriveAprilTagCmd(4, aprilTagVisionPortal.getVisionProcessor(), drive, telemetry)
                , drive(24)
                , turnCW(180)
                , new PixelDropperCmd(pixelDropper)
                , new PixelDropperCmd(pixelDropper)
        ));
    }

    @Override
    public void centerLogic() {
        schedule(new SequentialCommandGroup(
                drive(20)
                , armDown
                , drive(6)
                , new EjectCmd(intake)
                , armNeutral
                , drive(-3)
                , turnCCW(90)
                , drive(12)
                //, new DriveAprilTagCmd(8, aprilTagVisionPortal.getVisionProcessor(), drive, telemetry)
                //, new IntakeCmd(intake)
                , turnCW(90)
                , drive(30)
                , turnCW(90)
                , drive(60)
                , turnCW(25)
                , drive(12)
                , armUp
                //, new DriveAprilTagCmd(5, aprilTagVisionPortal.getVisionProcessor(), drive, telemetry)
                , drive(24)
                , turnCW(180)
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
                , drive(-2)
                , new EjectCmd(intake)
                , armNeutral
                , turnCCW(75)
                , drive(24)
                , turnCW(90)
                , drive(60)
                , turnCW(30)
                , drive(12)
                , armUp
                , drive(12)
                //, new DriveAprilTagCmd(6, aprilTagVisionPortal.getVisionProcessor(), drive, telemetry)
                , turnCW(180)
                , new PixelDropperCmd(pixelDropper)
                , new PixelDropperCmd(pixelDropper)
        ));
    }
}