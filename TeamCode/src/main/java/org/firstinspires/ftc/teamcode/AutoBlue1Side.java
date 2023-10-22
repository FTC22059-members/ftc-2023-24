package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.commands.DriveAprilTagCmd;
import org.firstinspires.ftc.teamcode.commands.DriveDistanceCmd;
import org.firstinspires.ftc.teamcode.commands.PixelDropperCmd;
import org.firstinspires.ftc.teamcode.commands.TurnCmd;
import org.firstinspires.ftc.teamcode.subsystems.DrivetrainSub;
import org.firstinspires.ftc.teamcode.subsystems.ImuSub;
import org.firstinspires.ftc.teamcode.subsystems.PixelDropperSub;

@Autonomous(name = "Autonomous Blue 1 Side")
public class AutoBlue1Side extends CommandOpMode
{
    private double turnSpeed = 0.4;
    private double driveSpeed = 0.5;

    private DrivetrainSub drive;
    private ImuSub imu;
    private PixelDropperSub pixelDropper;

    private boolean fieldCentric = true;
    @Override
    public void initialize() {
        //Initalizing Hardware
        drive = new DrivetrainSub(hardwareMap, telemetry);
        imu = new ImuSub(hardwareMap, telemetry);
        pixelDropper = new PixelDropperSub(hardwareMap, telemetry);

        //Find the position of team goal
        String branch = "N";

        while(branch == "N"){
            if (gamepad1.x){
                branch = "L";
            }else if(gamepad1.y){
                branch = "C";
            }else if(gamepad1.b){
                branch = "R";
            }
        }

        waitForStart();
        if (branch == "L") {
            schedule(new SequentialCommandGroup(
                    drive(24)
                    , turnCCW(75)
                    , turnCW(165)
                    , new DriveAprilTagCmd(10, hardwareMap.get(WebcamName.class, "Webcam 1"), drive, telemetry)
                    , turnCW(88)
                    , drive(18)
                    , turnCW(91)
                    , drive(60)
                    , turnCW(20)
                    , drive(12)
                    , new DriveAprilTagCmd(1, hardwareMap.get(WebcamName.class, "Webcam 1"), drive, telemetry)
                    , turnCCW(180)
                    , new PixelDropperCmd(pixelDropper)
            ));

            //  ###  ##### #    # ##### #####
            // #     #     ##   #   #   #
            // #     ##### # #  #

        }else if (branch == "C") {
            schedule(new SequentialCommandGroup(
                    drive(24)
                    , turnCW(88)
                    , new DriveAprilTagCmd(10, hardwareMap.get(WebcamName.class, "Webcam 1"), drive, telemetry)
                    , turnCW(90)
                    , drive(18)
                    , turnCW(91)
                    , drive(66)
                    , turnCW(20)
                    , drive(12)
                    , new DriveAprilTagCmd(2, hardwareMap.get(WebcamName.class, "Webcam 1"), drive, telemetry)
                    , turnCCW(180)
                    , new PixelDropperCmd(pixelDropper)
            ));

            // ###   #  ###  #   # #####
            // #  #  # #     #   #   #
            // ###   # #  ## #####   #
            // #  #  # #   # #   #   #
            // #  #  #  ###  #   #   #

        } else if (branch == "R") {
            schedule(new SequentialCommandGroup(
                    drive(24)

                    , turnCW(75)
                    , turnCCW(73)

                    , drive(23)
                    , turnCW(88)
                    , drive(18)
                    , turnCW(88)
                    , drive(47)
                    , turnCW(91)
                    , drive(65)
                    , turnCW(25)
                    , drive(18)
                    , new DriveAprilTagCmd(3, hardwareMap.get(WebcamName.class, "Webcam 1"), drive, telemetry)
                    , turnCCW(180)
                    , new PixelDropperCmd(pixelDropper)
            ));
        }
    }

    public TurnCmd turnCW(int angle){
        return new TurnCmd(-angle,turnSpeed,drive,imu,telemetry);
    }

    public TurnCmd turnCCW(int angle){
        return new TurnCmd(angle,turnSpeed,drive,imu,telemetry);
    }

    public DriveDistanceCmd drive(int inches){
        return new DriveDistanceCmd(inches, driveSpeed, drive, telemetry);
    }
}