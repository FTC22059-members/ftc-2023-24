package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.commands.EjectCmd;
import org.firstinspires.ftc.teamcode.commands.PixelDropperCmd;

@Autonomous(name = "Red2", group = "Red")

public class AutoRed2 extends AutoCommandOpMode {
    @Override
    public void leftLogic() {
        schedule(new SequentialCommandGroup(
                drive(30)
                , turnCCW(90)
                , armDown
                , drive(-1)
                , new EjectCmd(intake)
                , armUp
                , turnCCW(15)
                , drive(-32)
//                    , new DriveAprilTagCmd(4, aprilTagVisionPortal.getVisionProcessor(), drive, telemetry)
                , new PixelDropperCmd(pixelDropper)
                , new PixelDropperCmd(pixelDropper)
        ));
    }

    @Override
    public void centerLogic() {
        schedule(new SequentialCommandGroup(
                drive(24)
                , armDown
                , drive(6)
                , new EjectCmd(intake)
                , drive(-2)
                , armUp
                , turnCCW(90)
                , drive(-35)
//                    , new DriveAprilTagCmd(5, aprilTagVisionPortal.getVisionProcessor(), drive, telemetry)
                , new PixelDropperCmd(pixelDropper)
                , new PixelDropperCmd(pixelDropper)
        ));
    }

    @Override
    public void rightLogic() {
        schedule(new SequentialCommandGroup(
                drive(29)
                , turnCW(90)
                , drive(-2)
                , armDown
                , new EjectCmd(intake)
                , armUp
                , drive(3)
                , turnCCW(90)
                , drive(-16)
                , turnCCW(105)
                , drive(-35)
                , turnCW(15)
//                    , new DriveAprilTagCmd(6, aprilTagVisionPortal.getVisionProcessor(), drive, telemetry)
                , new PixelDropperCmd(pixelDropper)
                , new PixelDropperCmd(pixelDropper)
        ));
    }
}
