package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "Red1Short", group = "Red")
public class AutoRed1Short extends AutoCommandOpMode {
    @Override
    public void leftLogic() {
        schedule(new SequentialCommandGroup(
            drive(12)
            , turnCCW(35)
            , drive(15)
            , drive(-10)
            , turnCW(35)
            , armDown
            , armNeutral
        ));
    }

    @Override
    public void centerLogic() {
        schedule(new SequentialCommandGroup(
            drive(32)
            , drive(-6)
            , armDown
            , armNeutral
        ));
    }

    @Override
    public void rightLogic() {
        schedule(new SequentialCommandGroup(
            drive(16)
            , turnCW(35)
            , drive(11)
            , drive(-16)
            , turnCCW(35)
            , armDown
            , armNeutral
        ));
    }
}