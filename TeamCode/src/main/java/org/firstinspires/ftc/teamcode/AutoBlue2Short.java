package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "Blue2Short", group = "Blue")
public class AutoBlue2Short extends AutoCommandOpMode {
    @Override
    public void leftLogic() {
        schedule(new SequentialCommandGroup(
                drive(12)
                , turnCCW(35)
                , drive(11)
                , drive(-6)
                , armDown
                , armNeutral
        ));
    }

    @Override
    public void centerLogic() {
        schedule(new SequentialCommandGroup(
                drive(30)
                , drive(-15)
                , armDown
                , armNeutral
        ));
    }

    @Override
    public void rightLogic() {
        schedule(new SequentialCommandGroup(
                drive(16)
                , turnCW(40)
                , drive(11)
                , drive(-23)
                , armDown
                , armNeutral
        ));
    }
}