package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "Blue1Stage", group = "Blue")
public class AutoBlue1Stage extends AutoCommandOpMode {
    @Override
    public void leftLogic() {
        schedule(new SequentialCommandGroup(
                drive(16)
                , turnCCW(37)
                , drive(11)
                , drive(-16)
                , turnCW(37)
                , drive(39)
                , turnCCW(88)
                , armDown
                , armNeutral
                , drive(60)
                , turnCCW(169)
                , drive(-30)
        ));
    }

    @Override
    public void centerLogic() {
        schedule(new SequentialCommandGroup(
                drive(30)
                , drive(-5)
                , turnCW(90)
                , drive(14)
                , turnCCW(88)
                , drive(28)
                , turnCCW(90)
                , armDown
                , armNeutral
                , drive(73)
                , turnCCW(172)
                , drive(-30)
        ));
    }

    @Override
    public void rightLogic() {
        schedule(new SequentialCommandGroup(
                drive(12)
                , turnCW(35)
                , drive(14)
                , drive(-10)
                , turnCCW(35)
                , drive(35)
                , turnCCW(86)
                , armDown
                , armNeutral
                , drive(60)
                , turnCCW(168)
                , drive(-28)
        ));
    }
}

