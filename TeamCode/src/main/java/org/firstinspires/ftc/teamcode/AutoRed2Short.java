package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "Red2Short", group = "Red")
public class AutoRed2Short extends AutoCommandOpMode {
    @Override
    public void leftLogic() {
        schedule(new SequentialCommandGroup(
            drive(16)
            , turnCCW(40)
            , drive(12)
            , drive(-24)
            , turnCCW(40)
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
            drive(12)
            , turnCW(35)
            , drive(11)
            , drive(-6)
            , turnCCW(35)
            , armDown
            , armNeutral
        ));
    }
}
