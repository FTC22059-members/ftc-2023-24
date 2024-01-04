package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.commands.EjectCmd;
import org.firstinspires.ftc.teamcode.commands.PixelDropperCmd;

@Autonomous(name = "Blue2", group = "Blue")

public class AutoBlue2 extends AutoCommandOpMode {
    @Override
    public void leftLogic() {
        schedule(new SequentialCommandGroup(
                drive(29)
                , turnCCW(90)
                , drive(-2)
                , armDown
                , new EjectCmd(intake)
                , armUp
                , drive(3)
                , turnCW(90)
                , drive(-16)
                , turnCW(105)
                , drive(-35)
                , turnCCW(15)
//                    , new DriveAprilTagCmd(6, aprilTagVisionPortal.getVisionProcessor(), drive, telemetry)
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
                , drive(-2)
                , armUp
                , turnCW(90)
                , drive(-34)
//                    , new DriveAprilTagCmd(5, aprilTagVisionPortal.getVisionProcessor(), drive, telemetry)
                , new PixelDropperCmd(pixelDropper)
                , new PixelDropperCmd(pixelDropper)
        ));
    }

    @Override
    public void rightLogic() {
        schedule(new SequentialCommandGroup(
                drive(30)
                , turnCW(90)
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
}