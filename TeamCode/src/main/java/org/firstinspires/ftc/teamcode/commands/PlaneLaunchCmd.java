package org.firstinspires.ftc.teamcode.commands;

import android.media.Image;

import com.arcrobotics.ftclib.command.CommandBase;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.PlaneLauncherSub;

public class PlaneLaunchCmd extends CommandBase {

    PlaneLauncherSub planeLauncherSub;
    private Telemetry telemetry;

    public PlaneLaunchCmd(HardwareMap hardwareMap,Telemetry telemetryImport) {
        planeLauncherSub = new PlaneLauncherSub(hardwareMap,telemetry);
        telemetry = telemetryImport;
    }

    @Override
    public void initialize() {
        planeLauncherSub.setLauncherPosition(true);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

}