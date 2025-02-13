package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "Red2", group = "Red")
public class AutoRed2 extends AutoCommandOpMode {
    @Override
    public void leftLogic() {
        schedule(new SequentialCommandGroup(
            drive(16)
            , turnCCW(40)
            , drive(12)
            , drive(-24)
            , turnCCW(45)
            , drive(-10)
            , turnCW(5)
            , drive(-20)
        ));
    }

    @Override
    public void centerLogic() {
        schedule(new SequentialCommandGroup(
            drive(32)
            , drive(-28)
            , turnCCW(86)
            , drive(-42)
        ));
    }

    @Override
    public void rightLogic() {
        schedule(new SequentialCommandGroup(
            drive(12)
            , turnCW(35)
            , drive(11)
            , drive(-6)
            , turnCCW(90)
            , drive(-18)
            , turnCCW(32)
            , drive(-23)
        ));
    }
}
