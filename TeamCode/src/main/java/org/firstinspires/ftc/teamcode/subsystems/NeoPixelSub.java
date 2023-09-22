package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.I2cDeviceSynchImplOnSimple;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.library.Color;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class NeoPixelSub extends SubsystemBase {
    private HardwareMap hardwareMap;
    private I2cDeviceSynchImplOnSimple neoPixelDriver;
    private int stripLength = 12;

    private Color currentColor = Constants.Colors.BLACK;
    private Color previousColor = Constants.Colors.BLACK;
    private int fadePosition = 0;
    private final int fadeCooldown = 10;
    private int fadeCounter = 0;

    /* REGISTERS FOR THE DRIVER (https://www.adafruit.com/product/5766). See https://learn.adafruit.com/adafruit-seesaw-atsamd09-breakout/neopixel for the register info.

    ┌──────────────────┬───────────────┬───────────────┬────────────────┬──────────────────────────────────────────────────────────────────────────────────────────────────┐
    │ Register Address │ Register Name │ Register Size │ Notes          │ Use                                                                                              │
    ├──────────────────┼───────────────┼───────────────┼────────────────┼──────────────────────────────────────────────────────────────────────────────────────────────────┤
    │ 0x01             │ PIN           │ 8 bits        │ Write Only     │ Not Used                                                                                         │
    ├──────────────────┼───────────────┼───────────────┼────────────────┼──────────────────────────────────────────────────────────────────────────────────────────────────┤
    │ 0x02             │ SPEED         │ 8 bits        │ Write Only     │ 400khz or 800khz(default)                                                                        │
    ├──────────────────┼───────────────┼───────────────┼────────────────┼──────────────────────────────────────────────────────────────────────────────────────────────────┤
    │ 0x03             │ BUF_LENGTH    │ 16 bits       │ Write Only, LE │ Number of bytes required for the strip                                                           │
    ├──────────────────┼───────────────┼───────────────┼────────────────┼──────────────────────────────────────────────────────────────────────────────────────────────────┤
    │ 0x04             │ BUF           │ 32 bytes      │ Write Only     │ First 2 bytes are the starting pixel address, then <=30 bytes containing color information (GRB) │
    ├──────────────────┼───────────────┼───────────────┼────────────────┼──────────────────────────────────────────────────────────────────────────────────────────────────┤
    │ 0x05             │ SHOW          │ none          │ Write Only     │ Send to the pixels                                                                               │
    └──────────────────┴───────────────┴───────────────┴────────────────┴──────────────────────────────────────────────────────────────────────────────────────────────────┘
     */

    public NeoPixelSub(HardwareMap hardwareMap) {
        this.hardwareMap = hardwareMap;
        neoPixelDriver = hardwareMap.get(I2cDeviceSynchImplOnSimple.class, "NeoPixelDriver");

        byte[] result = ByteBuffer.allocate(2) // 2 bytes = 16 bits
                .order(ByteOrder.LITTLE_ENDIAN)
                .putInt(stripLength * 3) // 1 byte for red, green, and blue.
                .array();
        neoPixelDriver.write(0x03, result);
    }

    private boolean writePixel(int pixelAddress, Color color) {
        if (pixelAddress >= stripLength) {
            return false;
        }

        ByteBuffer BPixelAddress = ByteBuffer.allocate(2).putInt(pixelAddress);

        ByteBuffer red = ByteBuffer.allocate(1).putInt(color.red);
        ByteBuffer green = ByteBuffer.allocate(1).putInt(color.red);
        ByteBuffer blue = ByteBuffer.allocate(1).putInt(color.blue);

        byte[] result = ByteBuffer.allocate(32)
                .put(BPixelAddress)
                .put(green)
                .put(red)
                .put(blue)
                .array();
        neoPixelDriver.write(0x04, result);
        return true;
    }

    private void showPixels() {
        neoPixelDriver.write(0x05, new byte[]{});
    }

    public void setColor(Color newColor) {
        previousColor = currentColor;
        currentColor = newColor;
        fadePosition = 0;
    }

    @Override
    public void periodic() {
        if (fadePosition < stripLength - 1) {
            if (fadeCounter < fadeCooldown) {
                fadeCounter += 1;
            }
            if (fadeCounter >= fadeCooldown) {
                fadePosition += 1;
                fadeCounter = 0;
            }
        } else {
            return;
        }

        writePixel(fadePosition, currentColor);
        showPixels();
    }
}
