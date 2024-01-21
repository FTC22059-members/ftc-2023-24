package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "Blue1Side", group = "Blue")
public class AutoBlue1Side extends AutoCommandOpMode {
    @Override
    public void leftLogic() {
        schedule(new SequentialCommandGroup(
                drive(16)
                , turnCCW(38)
                , drive(11)
                , drive(-16)
                , turnCCW(8)
                , drive(-13)
                , turnCCW(36)
                , armDown
                , armNeutral
                , drive(62)
                , turnCW(15)
                , drive(13)
                , turnCW(146)
                , drive(-27)
        ));
    }

    @Override
    public void centerLogic() {
        schedule(new SequentialCommandGroup(
                drive(30)
                , drive(-5)
                , turnCCW(45)
                , drive(-24)
                , turnCCW(39)
                , armDown
                , armNeutral
                , drive(62)
                , turnCW(10)
                , drive(12)
                , turnCW(161)
                , drive(-32)
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
                , drive(-12)
                , turnCCW(88)
                , armDown
                , armNeutral
                , drive(55)
                , turnCW(10)
                , drive(13)
                , turnCW(158)
                , drive(-22)
        ));
    }
}