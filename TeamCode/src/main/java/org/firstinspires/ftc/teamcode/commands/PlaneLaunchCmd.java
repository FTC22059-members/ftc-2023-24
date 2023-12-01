package org.firstinspires.ftc.teamcode.commands;

import android.media.Image;

import com.arcrobotics.ftclib.command.CommandBase;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.PlaneLauncherSub;

/**
 * This command is dedicated to plane launching
 */

public class PlaneLaunchCmd extends CommandBase {

    PlaneLauncherSub planeLauncherSub;
    private Telemetry telemetry;

    /**
     * Plane launch command.
     *
     * @param hardwareMapParam
     * @param telemetryParam
     */

    public PlaneLaunchCmd(HardwareMap hardwareMapParam,Telemetry telemetryParam) {
        planeLauncherSub = new PlaneLauncherSub(hardwareMapParam,telemetry);
        telemetry = telemetryParam;
    }

    @Override
    public void execute() {
        planeLauncherSub.launch();
    }

    @Override
    public boolean isFinished() {
        return true;
    }

}