package org.firstinspires.ftc.teamcode;

// Imports
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;


@TeleOp(name="Simple Move", group="Linear Opmode")
public class Controller extends LinearOpMode
{
    // Dc Motors
    private DcMotor leftMotor = null;
    private DcMotor rightMotor = null;
    private DcMotor rliftMotor= null;
    private DcMotor lliftMotor= null;

    @Override
    public void runOpMode()
    {
        // Add telemetry data
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Initialize DcMotors
        leftMotor = hardwareMap.get(DcMotor.class, "leftMotor");
        rightMotor = hardwareMap.get(DcMotor.class, "rightMotor");
        rliftMotor= hardwareMap.get(DcMotor.class, "rliftMotor");
        lliftMotor= hardwareMap.get(DcMotor.class, "lliftMotor");

        rliftMotor.setDirection(DcMotor.Direction.REVERSE);
        lliftMotor.setDirection(DcMotor.Direction.FORWARD);
        leftMotor.setDirection(DcMotor.Direction.FORWARD);
        rightMotor.setDirection(DcMotor.Direction.REVERSE);


        waitForStart();

        // Main loop
        while (opModeIsActive())
        {
            movTurn(gamepad1.left_stick_x, gamepad1.left_stick_y);
            //LiftUp(gamepad1.a,gamepad1.b);
        }
    }

    // Moving and turning
    private void movTurn(double stickX, double stickY)
    {
        // Tested constants
        final double smallPower = 0.2;
        final double bigPower = 0.65;
        final double powerLimit = 0.8;
        final double movTurnLimit = 0.51;

        // Moving
        if (Math.abs(stickY) >= movTurnLimit)
        {
            // Slow moving
            if (Math.abs(stickY) <= powerLimit)
            {
                leftMotor.setPower(smallPower * (stickY / Math.abs(stickY)));
                rightMotor.setPower(smallPower * (stickY / Math.abs(stickY)));
                //test
                lliftMotor.setPower(smallPower * (stickY / Math.abs(stickY)));
                rliftMotor.setPower(smallPower * (stickY / Math.abs(stickY)));
            }
            // Fast moving
            else
            {
                leftMotor.setPower(bigPower * (stickY / Math.abs(stickY)));
                rightMotor.setPower(bigPower * (stickY / Math.abs(stickY)));
                //test
                lliftMotor.setPower(bigPower * (stickY / Math.abs(stickY)));
                rliftMotor.setPower(bigPower * (stickY / Math.abs(stickY)));
            }
        }

        // Turning
        else if (Math.abs(stickX) >= movTurnLimit)
        {
            // Slow turning
            if (Math.abs(stickX) <= powerLimit)
            {
                leftMotor.setPower(-smallPower * (stickX / Math.abs(stickX)));
                rightMotor.setPower(smallPower * (stickX / Math.abs(stickX)));
                //test
                lliftMotor.setPower(-smallPower * (stickX / Math.abs(stickX)));
                rliftMotor.setPower(smallPower * (stickX / Math.abs(stickX)));
            }
            // Fast turning
            else
            {
                rightMotor.setPower(bigPower * (stickX / Math.abs(stickX)));
                leftMotor.setPower(-bigPower * (stickX / Math.abs(stickX)));
                //test
                rliftMotor.setPower(bigPower * (stickX / Math.abs(stickX)));
                lliftMotor.setPower(-bigPower * (stickX / Math.abs(stickX)));
            }
        }

        // Stop robot
        else
        {
            leftMotor.setPower(0);
            rightMotor.setPower(0);
            //test
            rliftMotor.setPower(0);
            lliftMotor.setPower(0);
        }
    }

    private void LiftUp(boolean key_a,boolean key_b)
    {
        final double p=1;
        if (key_a)
        {
            rliftMotor.setPower(p);
            lliftMotor.setPower(p);
        }else if (key_b)
        {
            rliftMotor.setPower(-p);
            lliftMotor.setPower(-p);
        }
        else
        {
            rliftMotor.setPower(0);
            lliftMotor.setPower(0);
        }
    }

}