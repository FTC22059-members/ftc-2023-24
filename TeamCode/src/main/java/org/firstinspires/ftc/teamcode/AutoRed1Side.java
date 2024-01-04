package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.commands.EjectCmd;
import org.firstinspires.ftc.teamcode.commands.PixelDropperCmd;

@Autonomous(name = "Red1Side", group = "Red")

public class AutoRed1Side extends AutoCommandOpMode {
    @Override
    public void leftLogic() {
        schedule(new SequentialCommandGroup(
                drive(24)
                , turnCCW(75)
                , armDown
                , new EjectCmd(intake)
                , armNeutral
                , turnCW(75)
                , drive(-23)
                , turnCW(85)
                //, drive(18)
                //, new IntakeCmd(intake)
                //, turnCCW(85)
                //, drive(50)
                //, turnCCW(81)
                , drive(65)
                , turnCCW(30)
                , drive(18)
                //, new DriveAprilTagCmd(4, aprilTagVisionPortal.getVisionProcessor(), drive, telemetry)
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
                , drive(-2)
                , turnCW(45)
                , drive(-24)
                , turnCW(45)
                , drive(72)
                , turnCCW(20)
                , drive(24)
                //, new DriveAprilTagCmd(5, aprilTagVisionPortal.getVisionProcessor(), drive, telemetry)
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
                , new EjectCmd(intake)
                , armNeutral
                , turnCCW(30)
                , drive(-24)
                //, new DriveAprilTagCmd(8, aprilTagVisionPortal.getVisionProcessor(), drive, telemetry)
                //, new IntakeCmd(intake)
                , turnCW(45)
                //, turnCCW(76)
                //, drive(15)
                //, turnCCW(83)
                , drive(72)
                , turnCCW(20)
                , drive(24)
                //, new DriveAprilTagCmd(6, aprilTagVisionPortal.getVisionProcessor(), drive, telemetry)
                , turnCW(180)
                , new PixelDropperCmd(pixelDropper)
                , new PixelDropperCmd(pixelDropper)
        ));
    }
}