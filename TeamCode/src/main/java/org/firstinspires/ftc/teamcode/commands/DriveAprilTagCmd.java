/* Copyright (c) 2023 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */



package org.firstinspires.ftc.teamcode.commands;

import static java.lang.Thread.sleep;

import com.arcrobotics.ftclib.command.CommandBase;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.DrivetrainSub;
import org.firstinspires.ftc.vision.VisionProcessor;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

import java.util.List;

/**
 * This class is dedicated to driving towards April Tags.
 * It is based off of code from the FIRST default programs.
 */

public class DriveAprilTagCmd extends CommandBase
{
    private DrivetrainSub drivetrainSub;
    private Telemetry telemetry;

    boolean targetFound     = false;    // Set to true when an AprilTag target is detected
    double  drive           = 0;        // Desired forward power/speed (-1 to +1)
    double  strafe          = 0;        // Desired strafe power/speed (-1 to +1)
    double  turn            = 0;        // Desired turning power/speed (-1 to +1)
    double  rangeError      = 0;

    int executeCount = 0;               //A really quickly put together timer

    // Adjust these numbers to suit your robot.
    final double DESIRED_DISTANCE = 8.0; //  this is how close the camera should get to the target (inches)

    //  Set the GAIN constants to control the relationship between the measured position error, and how much power is
    //  applied to the drive motors to correct the error.
    //  Drive = Error * Gain    Make these values smaller for smoother control, or larger for a more aggressive response.
    final double SPEED_GAIN = 0.07;   //  Forward Speed Control "Gain". eg: Ramp up to 50% power at a 25 inch error.   (0.50 / 25.0)
    final double STRAFE_GAIN = 0.015;   //  Strafe Speed Control "Gain".  eg: Ramp up to 25% power at a 25 degree Yaw error.   (0.25 / 25.0)
    final double TURN_GAIN = 0.01;   //  Turn Control "Gain".  eg: Ramp up to 25% power at a 25 degree error. (0.25 / 25.0)

    final double MAX_AUTO_SPEED = 0.5;   //  Clip the approach speed to this max value (adjust for your robot)
    final double MAX_AUTO_STRAFE = 0.5;   //  Clip the approach speed to this max value (adjust for your robot)
    final double MAX_AUTO_TURN = 0.3;   //  Clip the turn speed to this max value (adjust for your robot)
    private int desiredTagID;     // Choose the tag you want to approach
    private AprilTagProcessor aprilTag;              // Used for managing the AprilTag detection process.
    private AprilTagDetection detectedTag = null;     // Used to hold the data for a detected AprilTag

    /**
     * Creates a new DriveAprilTagCmd.
     *
     * @param desiredTagIDImport Desired Tag
     * @param visionProcessorImport April Tag Processor
     * @param drivetrainSubImport  The drive subsystem on which this command will run
     * @param telemetryImport Telemetry
     */
    public DriveAprilTagCmd(int desiredTagIDImport, VisionProcessor visionProcessorImport, DrivetrainSub drivetrainSubImport, Telemetry telemetryImport) {
        desiredTagID = desiredTagIDImport;
        aprilTag = (AprilTagProcessor) visionProcessorImport;
        drivetrainSub = drivetrainSubImport;
        telemetry = telemetryImport;
    }

    @Override
    public void initialize(){
    }

    @Override
    public void execute()
    {
        executeCount++;
        targetFound = false;
        detectedTag = null;

        // Step through the list of detected tags and look for a matching tag
        List<AprilTagDetection> currentDetections = aprilTag.getDetections();
        for (AprilTagDetection detection : currentDetections) {
            if ((detection.metadata != null) && (detection.id == desiredTagID)){
                targetFound = true;
                detectedTag = detection;
                break;  // don't look any further.
            }
        }

        // Tell the driver what we see, and what to do.
        if (targetFound) {
            telemetry.addData("Target", "ID %d (%s)", detectedTag.id, detectedTag.metadata.name);
            telemetry.addData("Range",  "%5.1f inches", detectedTag.ftcPose.range);
            telemetry.addData("Bearing","%3.0f degrees", detectedTag.ftcPose.bearing);
            telemetry.addData("Yaw","%3.0f degrees", detectedTag.ftcPose.yaw);
        } else {
            telemetry.addLine("Tag couldn't be found");
        }

        // If we have found the desired target, Drive to target Automatically .
        if (targetFound) {

            // Determine heading, range and Yaw (tag image rotation) error so we can use them to control the robot automatically.
            rangeError      = (detectedTag.ftcPose.range - DESIRED_DISTANCE);
            double  headingError    = detectedTag.ftcPose.bearing;
            double  yawError        = detectedTag.ftcPose.yaw;

            // Use the speed and turn "gains" to calculate how we want the robot to move.
            drive  = Range.clip(rangeError * SPEED_GAIN, -MAX_AUTO_SPEED, MAX_AUTO_SPEED);
            turn   = Range.clip(headingError * TURN_GAIN, -MAX_AUTO_TURN, MAX_AUTO_TURN) ;
            strafe = Range.clip(-yawError * STRAFE_GAIN, -MAX_AUTO_STRAFE, MAX_AUTO_STRAFE);

            telemetry.addData("Auto","Drive %5.2f, Strafe %5.2f, Turn %5.2f ", drive, strafe, turn);

        }else{
            rangeError = 0;
            drive = 0;
            turn = 0;
            strafe = 0;
        }
        telemetry.update();

        // Apply desired axes motions to the drivetrain
        drivetrainSub.move(drive, turn, strafe);
        try {
            sleep(10);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(
                "Data, "+executeCount +
                ", "+targetFound+
                ", "+rangeError+
                ", "+drive+
                ", "+turn+
                ", "+strafe);
    }

    @Override
    public boolean isFinished() {
        return (executeCount>50)&&rangeError<1;
    }

    @Override
    public void end(boolean interrupted) {
        super.end(interrupted);
    }
}
