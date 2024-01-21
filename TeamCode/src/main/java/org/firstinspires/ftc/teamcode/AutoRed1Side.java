package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "Red1Side", group = "Red")
public class AutoRed1Side extends AutoCommandOpMode {
    @Override
    public void leftLogic() {
        schedule(new SequentialCommandGroup(
                drive(12)
                , turnCCW(35)
                , drive(15)
                , drive(-10)
                , turnCW(35)
                , drive(-12)
                , turnCW(90)
                , armDown
                , armNeutral
                , drive(55)
                , turnCCW(10)
                , drive(11)
                , turnCCW(158)
                , drive(-22)
        ));
    }

    @Override
    public void centerLogic() {
        schedule(new SequentialCommandGroup(
                drive(32)
                , drive(-6)
                , turnCW(45)
                , drive(-27)
                , turnCW(45)
                , armDown
                , armNeutral
                , drive(62)
                , turnCCW(10)
                , drive(11)
                , turnCCW(157)
                , drive(-32)
        ));
    }

    @Override
    public void rightLogic() {
        schedule(new SequentialCommandGroup(
                drive(16)
                , turnCW(37)
                , drive(12)
                , drive(-17)
                , turnCW(8)
                , drive(-14)
                , turnCW(41)
                , armDown
                , armNeutral
                , drive(62)
                , turnCCW(5)
                , drive(11)
                , turnCCW(161)
                , drive(-25)
        ));
    }
}