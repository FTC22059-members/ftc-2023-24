package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "Red1Stage", group = "Red")
public class AutoRed1Stage extends AutoCommandOpMode {
    @Override
    public void leftLogic() {
        schedule(new SequentialCommandGroup(
            drive(12)
            , turnCCW(35)
            , drive(15)
            , drive(-10)
            , turnCW(35)
            , drive(35)
            , turnCW(90)
            , armDown
            , armNeutral
            , drive(60)
            , turnCCW(178)
            , drive(-31)
        ));
    }

    @Override
    public void centerLogic() {
        schedule(new SequentialCommandGroup(
            drive(32)
            , drive(-6)
            , turnCW(90)
            , drive(-15)
            , turnCW(90)
            , drive(-24)
            , turnCCW(92)
            , armDown
            , armNeutral
            , drive(63)
            , turnCCW(176)
            , drive(-43)
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
            , drive(39)
            , turnCW(90)
            , armDown
            , armNeutral
            , drive(60)
            , turnCCW(178)
            , drive(-32)
        ));
    }
}