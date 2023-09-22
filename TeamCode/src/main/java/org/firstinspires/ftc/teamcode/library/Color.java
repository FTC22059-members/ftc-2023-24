package org.firstinspires.ftc.teamcode.library;

/**
 * Stores a color. Internally, it is an 8-bit RGB color (so don't expect too much precision).
 */
public class Color {
    public int red;
    public int green;
    public int blue;

    /**
     * Create a new Color from RGB values.
     * @param red The amount of red from 0-255.
     * @param green The amount of green from 0-255.
     * @param blue The amount of blue from 0-255.
     */
    public Color(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    /**
     * Create a new Color from RGB values.
     * @param red The amount of red from 0-1.
     * @param green The amount of green from 0-1.
     * @param blue The amount of blue from 0-1.
     */
    public Color(double red, double green, double blue) {
        this.red = (int) Math.floor(red * 255);
        this.green = (int) Math.floor(green * 255);
        this.blue = (int) Math.floor(blue * 255);
    }
}
