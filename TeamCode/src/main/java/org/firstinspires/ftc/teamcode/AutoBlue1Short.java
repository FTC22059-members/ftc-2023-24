package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "Blue1Short", group = "Blue")
public class AutoBlue1Short extends AutoCommandOpMode {
    @Override
    public void leftLogic() {
        schedule(new SequentialCommandGroup(
                drive(16)
                , turnCCW(37)
                , drive(11)
                , drive(-16)
                , turnCW(37)
                , armDown
                , armNeutral
        ));
    }

    @Override
    public void centerLogic() {
        schedule(new SequentialCommandGroup(
                drive(30)
                , drive(-5)
                , armDown
                , armNeutral
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
                , armDown
                , armNeutral
        ));
    }
}

