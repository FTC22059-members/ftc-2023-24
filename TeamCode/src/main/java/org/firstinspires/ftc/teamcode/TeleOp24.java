package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Constants.IntakeConstants;
import org.firstinspires.ftc.teamcode.commands.ArmCmd;
import org.firstinspires.ftc.teamcode.commands.DriveCmd;
import org.firstinspires.ftc.teamcode.commands.IntakeCmd;
import org.firstinspires.ftc.teamcode.commands.MoveLinearSlideCmd;
import org.firstinspires.ftc.teamcode.commands.PixelDropperCmd;

import org.firstinspires.ftc.teamcode.commands.PlaneLaunchCmd;
import org.firstinspires.ftc.teamcode.commands.WristCmd;
import org.firstinspires.ftc.teamcode.subsystems.ArmSub;
import org.firstinspires.ftc.teamcode.subsystems.DrivetrainSub;
import org.firstinspires.ftc.teamcode.subsystems.ImuSub;
import org.firstinspires.ftc.teamcode.subsystems.IntakeSub;
import org.firstinspires.ftc.teamcode.subsystems.LinearSlideSub;
import org.firstinspires.ftc.teamcode.subsystems.PixelDropperSub;
import org.firstinspires.ftc.teamcode.subsystems.WristSub;


@TeleOp(name = "Tele-op 2023-24")
public class TeleOp24 extends CommandOpMode {

    private GamepadEx driverOp;
    private GamepadEx toolOp;
    private DrivetrainSub drive;
    private DriveCmd driveCmd;
    private LinearSlideSub linearSlide;
    private MoveLinearSlideCmd linearSlideCmd;
    private boolean fieldCentric = false;
    private ImuSub robotImu;
    private PlaneLaunchCmd planeLaunchCmd;
    private IntakeSub intake;
    private IntakeCmd intakeOn;
    private IntakeCmd intakeReverse;
    private IntakeCmd intakeOff;
    private ArmSub arm;
    private PixelDropperSub output;
    private PixelDropperCmd outputOn;
    private PixelDropperCmd outputOff;
    private WristSub wrist;
    private WristCmd wristUp;
    private WristCmd wristDown;
    @Override
    public void initialize(){
        robotImu = new ImuSub(hardwareMap, telemetry);

        driverOp = new GamepadEx(gamepad1);
        toolOp = new GamepadEx(gamepad2);
        drive = new DrivetrainSub(hardwareMap, telemetry);
        driveCmd = new DriveCmd(drive, driverOp, robotImu::getAngle, this::getFieldCentric);
        planeLaunchCmd = new PlaneLaunchCmd(hardwareMap, telemetry);
        intake = new IntakeSub(hardwareMap, telemetry);
        intakeOn = new IntakeCmd(intake,IntakeConstants.defaultIntakeSpeed);
        intakeReverse = new IntakeCmd(intake,IntakeConstants.defaultIntakeSpeed*-1);
        intakeOff = new IntakeCmd(intake,0);
        linearSlide = new LinearSlideSub(hardwareMap, telemetry);
        linearSlideCmd = new MoveLinearSlideCmd(linearSlide, toolOp, telemetry);
        arm = new ArmSub(hardwareMap, telemetry);
        output = new PixelDropperSub(hardwareMap, telemetry);
        outputOn = new PixelDropperCmd(output, Constants.PixelDropperConstants.defaultPixelDropperSpeed);
        outputOff = new PixelDropperCmd(output, 0);
        wrist = new WristSub(hardwareMap, telemetry);
        wristUp = new WristCmd(wrist, 1);
        wristDown = new WristCmd(wrist, Constants.WristConstants.defaultWristAngle);

        // Y: Toggle field centric
        driverOp.getGamepadButton(GamepadKeys.Button.Y)
                .whenPressed(new InstantCommand(this::toggleFieldCentric));

        // X: Launch plane
        toolOp.getGamepadButton(GamepadKeys.Button.X)
                .whenPressed(planeLaunchCmd);

        // Intake
        // A (hold): Take in pixel
        // B (hold): Spit out pixel
        toolOp.getGamepadButton(GamepadKeys.Button.A).whenPressed(intakeOn);
        toolOp.getGamepadButton(GamepadKeys.Button.A).whenReleased(intakeOff);

        toolOp.getGamepadButton(GamepadKeys.Button.B).whenPressed(intakeReverse);
        toolOp.getGamepadButton(GamepadKeys.Button.B).whenReleased(intakeOff);

        // Output
        // Y (hold): Output pixel
        toolOp.getGamepadButton(GamepadKeys.Button.Y).whenPressed(outputOn);
        toolOp.getGamepadButton(GamepadKeys.Button.Y).whenReleased(outputOff);

        // Wrist
        // D-Pad (up/down): Move wrist
        toolOp.getGamepadButton(GamepadKeys.Button.DPAD_UP).whenPressed(wristUp);
        toolOp.getGamepadButton(GamepadKeys.Button.DPAD_DOWN).whenPressed(wristDown);

        register(drive);
        register(linearSlide);
        drive.setDefaultCommand(driveCmd);
        linearSlide.setDefaultCommand(linearSlideCmd);
    }

    @Override
    public void run(){
        super.run();
        telemetry.addLine("This is a new op mode");

        // Intake Arm
        // Left Joystick (up/down): Move arm
        arm.setSpeed(toolOp.getLeftY());

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
