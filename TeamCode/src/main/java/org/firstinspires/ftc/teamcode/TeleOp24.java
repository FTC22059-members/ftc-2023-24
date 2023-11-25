package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.teamcode.Constants.IntakeConstants.defaultIntakeSpeed;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.commands.DriveCmd;
import org.firstinspires.ftc.teamcode.commands.IntakeCmd;
import org.firstinspires.ftc.teamcode.commands.PlaneLaunchCmd;
import org.firstinspires.ftc.teamcode.subsystems.ImuSub;
import org.firstinspires.ftc.teamcode.subsystems.DrivetrainSub;
import org.firstinspires.ftc.teamcode.subsystems.IntakeSub;


@TeleOp(name = "Tele-op 2023-24")
public class TeleOp24 extends CommandOpMode {

    private GamepadEx driverOp;
    private GamepadEx toolOp;
    private DrivetrainSub drive;
    private DriveCmd driveCmd;
    private boolean fieldCentric = false;
    private ImuSub robotImu;
    private PlaneLaunchCmd planeLaunchCmd;
    private IntakeSub intake;
    private IntakeCmd intakeOn;
    private IntakeCmd intakeReverse;
    private IntakeCmd intakeOff;
    @Override
    public void initialize(){
        robotImu = new ImuSub(hardwareMap, telemetry);

        driverOp = new GamepadEx(gamepad1);
        toolOp = new GamepadEx(gamepad2);
        drive = new DrivetrainSub(hardwareMap, telemetry);
        driveCmd = new DriveCmd(drive, driverOp, robotImu::getAngle, this::getFieldCentric);
        planeLaunchCmd = new PlaneLaunchCmd(hardwareMap, telemetry);
        intake = new IntakeSub(hardwareMap, telemetry);
        intakeOn = new IntakeCmd(intake,defaultIntakeSpeed);
        intakeReverse = new IntakeCmd(intake,defaultIntakeSpeed*-1);
        intakeOff = new IntakeCmd(intake,0);

        // Y: Toggle field centric
        driverOp.getGamepadButton(GamepadKeys.Button.Y)
                .whenPressed(new InstantCommand(this::toggleFieldCentric));

        // X: Launch plane
        driverOp.getGamepadButton(GamepadKeys.Button.X)
                .whenPressed(planeLaunchCmd);

        // Intake
        // A (hold): Take in pixel
        // B (hold): Spit out pixel
        driverOp.getGamepadButton(GamepadKeys.Button.A).whenPressed(intakeOn);
        driverOp.getGamepadButton(GamepadKeys.Button.A).whenReleased(intakeOff);

        driverOp.getGamepadButton(GamepadKeys.Button.B).whenPressed(intakeReverse);
        driverOp.getGamepadButton(GamepadKeys.Button.B).whenReleased(intakeOff);

        register(drive);
        drive.setDefaultCommand(driveCmd);
    }

    @Override
    public void run(){
        super.run();

        telemetry.addData("Field Centric?", fieldCentric);
        telemetry.update();
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
