package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "Blue2", group = "Blue")
public class AutoBlue2 extends AutoCommandOpMode {
    @Override
    public void leftLogic() {
        schedule(new SequentialCommandGroup(
                drive(12)
                , turnCCW(35)
                , drive(11)
                , drive(-6)
                , turnCW(90)
                , drive(-18)
                , turnCW(32)
                , drive(-23)
        ));
    }

    @Override
    public void centerLogic() {
        schedule(new SequentialCommandGroup(
                drive(30)
                , drive(-25)
                , turnCW(86)
                , drive(-42)
        ));
    }

    @Override
    public void rightLogic() {
        schedule(new SequentialCommandGroup(
                drive(16)
                , turnCW(40)
                , drive(11)
                , drive(-23)
                , turnCW(46)
                , drive(-12)
                , turnCCW(5)
                , drive(-20)
        ));
    }
}