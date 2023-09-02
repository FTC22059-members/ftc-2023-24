package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.commands.DriveCmd;
import org.firstinspires.ftc.teamcode.commands.TurnCmd;
import org.firstinspires.ftc.teamcode.library.Imu;
import org.firstinspires.ftc.teamcode.subsystems.DrivetrainSub;



@TeleOp(name = "Tele-op 2023-24")
public class TeleOp24 extends CommandOpMode {

    private GamepadEx driverOp;
    private GamepadEx toolOp;
    private DrivetrainSub drive;
    private DriveCmd driveCmd;
    private boolean fieldCentric = true;
    private Imu robotImu;
    @Override
    public void initialize(){
        robotImu = new Imu(hardwareMap, telemetry);
        robotImu.init();


        driverOp = new GamepadEx(gamepad1);
        toolOp = new GamepadEx(gamepad2);
        drive = new DrivetrainSub(hardwareMap, telemetry);
        driveCmd = new DriveCmd(drive, driverOp, robotImu::getAngle, this::getFieldCentric);

        driverOp.getGamepadButton(GamepadKeys.Button.Y)
            .whenPressed(new InstantCommand(this::toggleFieldCentric));
        driverOp.getGamepadButton(GamepadKeys.Button.B)
                .whenPressed(new InstantCommand(TurnCmd::T));

        register(drive);
        drive.setDefaultCommand(driveCmd);

        telemetry.addData("Hello", "World");
        System.out.println("Hello World");
    }

    public boolean getFieldCentric(){
        return fieldCentric;
    }

    public void toggleFieldCentric(){
        fieldCentric = !fieldCentric;
        if (fieldCentric){
            robotImu.resetAngle();
        }
    }
}
