package pldo0.api_externa;

import net.md_5.bungee.api.ChatColor;

import java.awt.*;

/**
 * MIT License
 *
 * Copyright (c) 2022 0-764
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 * <h1>ColourEffects is a helper library for <i>md_5's</i> ChatColor class.</h1>
 * <p>This class has methods for applying simple ChatColor effects such as Italic and Bold, as well as more complex functions like Gradients between two colours.</p> <br/>
 * <p>
 * Example uses of the ColourEffects class:
 * <ul>
 * <li>ColourEffects.gradientEffect("Text gradient of two colours", ColourEffects.getColour(32, 255, 3), ColourEffects.getColour(255, 3, 247));</li>
 * <li>ColourEffects.gradientEffect("Text gradient of three colours", ColourEffects.getColour(255, 0, 0), ColourEffects.getColour(0, 255, 0), ColourEffects.getColour(0, 0, 255));</li>
 * <li>ColourEffects.rainbowEffect("Rainbow gradient effect");</li>
 * <li>ColourEffects.stripedEffect("Text striped effect of two colours", ChatColor.RED, ChatColor.GREEN);</li>
 * <li>ColourEffects.stripedEffect("Striped text effect of two three colours", ChatColor.RED, ChatColor.GREEN, ChatColor.BLUE);</li>
 * </ul>
 * <i>Note: This library utilises md_5's ChatColor class rather than org.Bukkit.ChatColor. This means your server must be running Spigot or a fork of Spigot such as PaperMC.</i>
 */
public class ColourEffects {

    /**
     * <b>Apply text with a rainbow colour effect.</b>
     * <p>If the text is longer in characters, the rainbow will encompass a wider range of colours.</p>
     *
     * @param text the text
     * @return rainbow text
     */
    public static String rainbowEffect(String text) {
        StringBuilder newText = new StringBuilder();
        int length = text.length();
        int split = 360 / length;
        for (int i = 0; i < length; i++) {
            int hue = split * i;
            newText.append(ColourEffects.getChatColor((hue / 360F) * 1, 1.0F, 1.0F));
            newText.append(text.charAt(i));
        }
        return newText.toString();
    }

    /**
     * <b>Apply text with a striped ChatColor effect between two colours.</b>
     * <p>The function will apply a ChatColor effect of the first colour to odd characters and to the even characters the second colour.</p>
     * <p><br/>
     * <i>For example: </i>
     * applyStripedEffect("Hello", ChatColor.RED, ChatColor.BLUE) would result in <span style="color: red">H</span><span style="color: blue">E</span><span style="color: red">L</span><span style="color: blue">L</span><span style="color: red">O</span>
     * </p>
     *
     * @param text the text
     * @param c    the first colour
     * @param c1   the second colour
     * @return text with stripe colour effect applied
     */
    public static String stripedEffect(String text, ChatColor c, ChatColor c1) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char chr = text.charAt(i);
            builder.append(i % 2 == 0 ? c : c1);
            builder.append(chr);
        }
        return builder.toString();
    }

    /**
     * <b>Apply text with a striped ChatColor effect with many colours.</b>
     * <p>The function will apply a ChatColor effect for the list of colours in order of characters.</p>
     * <p><br/>
     * <i>For example: </i>
     * applyStripedEffect("Hello", ChatColor.RED, ChatColor.BLUE, ChatColor.GREEN) would result in <span style="color: red">H</span><span style="color: blue">E</span><span style="color: green">L</span><span style="color: red">L</span><span style="color: blue">O</span>
     * </p>
     *
     * @param text the text
     * @param clrs list of colours for effect
     * @return text with stripe colour effect applied
     */
    public static String stripedEffect(String text, ChatColor... clrs) {
        StringBuilder builder = new StringBuilder();
        int count = clrs.length;
        int j = 0;
        for (int i = 0; i < text.length(); i++) {
            char chr = text.charAt(i);
            builder.append(clrs[j]);
            builder.append(chr);
            if ((j + 1) >= clrs.length) {
                j = 0;
            } else j++;
        }
        return builder.toString();
    }

    /**
     * <b>Apply text with a gradient ChatColor effect between two colours.</b>
     * <p>The function will fade a gradient of colour from c to c1.</p>
     *
     * @param text the text
     * @param c    the first colour
     * @param c1   the second colour
     * @return the text with gradient effect applied
     */
    public static String gradientEffect(String text, Color c, Color c1) {
        StringBuilder newText = new StringBuilder();
        int length = text.length();
        for (int i = 0; i < length; i++) {
            char chr = text.charAt(i);
            Color blendedColor = ColourEffects.linearInterpolateColour(c, c1, (float) i / length);
            newText.append(ChatColor.of(blendedColor));
            newText.append(chr);
        }
        return newText.toString();
    }

    /**
     * <b>Apply text with a gradient ChatColor effect by a list of colours.</b>
     * <p>The function will fade a gradient of colour from start to end of a list of colours.</p>
     *
     * @param text the text
     * @param clrs list of colours
     * @return the text with gradient effect applied
     */
    public static String gradientEffect(String text, Color... clrs) {
        StringBuilder newText = new StringBuilder();
        int length = text.length();
        for (int i = 0; i < length; i++) {
            char chr = text.charAt(i);
            Color blendedColor = ColourEffects.interpolateColour((float) i / length, clrs);
            newText.append(ChatColor.of(blendedColor));
            newText.append(chr);
        }
        return newText.toString();
    }

    /**
     * <b>Apply text with a gradient ChatColor effect between two colours.</b>
     * <p>The function will fade a gradient of colour around it's hue from c to c1.</p>
     *
     * @param text the text
     * @param c    the first colour
     * @param c1   the second colour
     * @return the text with gradient effect applied
     */
    public static String hueGradientEffect(String text, Color c, Color c1) {
        StringBuilder newText = new StringBuilder();
        int length = text.length();
        for (int i = 0; i < length; i++) {
            char chr = text.charAt(i);
            Color blendedColor = ColourEffects.hueInterpolateColour(c, c1, (float) i / length);
            newText.append(ChatColor.of(blendedColor));
            newText.append(chr);
        }
        return newText.toString();
    }

    /**
     * <b>Reset all ChatColor effects to text</b>
     * <p>The text may include ChatColor effects such as styling the text Orange or Blue, as well as making the text Italic or Bold.</p>
     * <p><i>This method uses md_5's ChatColor class.</i></p>
     *
     * @param text the text to be stripped of ChatColor effects
     * @return the reset text
     */
    public static String reset(String text) {
        return ColourEffects.chatColor(text, ChatColor.RESET);
    }

    /**
     * <b>Apply a Bold ChatColor effect to text.</b>
     * <p>The text can include other Colour effects.</p>
     * <p>The returned Bold text can be used within Minecraft chat and throughout other MC functions that support ChatColor.</p>
     *
     * @param text the text
     * @return text now bold
     */
    public static String bold(String text) {
        return ColourEffects.chatColor(text, ChatColor.BOLD);
    }

    /**
     * <b>Apply an Underline ChatColor effect to text.</b>
     * <p>This text can include other Colour effects.</p>
     * <p>The returned Underlined text can be used within Minecraft Chat and throughout other MC functions that support ChatColor.</p>
     *
     * @param text the text
     * @return text now underlined
     */
    public static String underline(String text) {
        return ColourEffects.chatColor(text, ChatColor.UNDERLINE);
    }

    /**
     * <b>Apply an Italic ChatColor effect to text.</b>
     * <p>This text can include other Colour effects.</p>
     * <p>The returned Italic text can be used within Minecraft Chat and throughout other MC functions that support ChatColor.</p>
     *
     * @param text the text
     * @return text now with italic
     */
    public static String italic(String text) {
        return ColourEffects.chatColor(text, ChatColor.ITALIC);

    }

    /**
     * <b>Apply a Strikethrough ChatColor effect to text.</b>
     * <p>This text can include other Colour effects.</p>
     * <p>The returned Strikethrough text can be used within Minecraft Chat and throughout other MC functions that support ChatColor.</p>
     *
     * @param text the text
     * @return text now with a strikethrough
     */
    public static String strikethrough(String text) {
        return ColourEffects.chatColor(text, ChatColor.STRIKETHROUGH);
    }

    /**
     * <b>Apply a Magic ChatColor effect to text.</b>
     * <p>This text can include other Colour effects.</p>
     * <p>The returned Strikethrough text can be used within Minecraft Chat and throughout other MC functions that support ChatColor.</p>
     *
     * @param text the text
     * @return text now with magic
     */
    public static String magic(String text) {
        return ColourEffects.chatColor(text, ChatColor.MAGIC);
    }


    /**
     * <b>Apply linear interpolation from colour c to colour c1 (Linear method).</b>
     * <p>Parameter p sets the scale along the interpolation the color result will be</p>
     * <p>This method will perform linear interpolation from one colour to another</p>
     *
     * @param c  the first colour
     * @param c1 the second colour
     * @param p  the percentage along the interpolation scale
     * @return the interpolated colour
     */
    private static Color linearInterpolateColour(Color c, Color c1, float p) {
        float r = c.getRed() + (c1.getRed() - c.getRed()) * p;
        float g = c.getGreen() + (c1.getGreen() - c.getGreen()) * p;
        float b = c.getBlue() + (c1.getBlue() - c.getBlue()) * p;
        return new Color(r / 255, g / 255, b / 255);
    }

    /**
     * <b>Apply linear interpolation from colour c to colour c1 (Hue method).</b>
     * <p>Parameter p sets the scale along the interpolation the color result will be</p>
     *
     * @param c  the first colour
     * @param c1 the second colour
     * @param p  the percentage along the interpolation scale
     * @return the interpolated colour
     */
    private static Color hueInterpolateColour(Color c, Color c1, float p) {
        // [Hue, Saturation, Brightness]
        float[] cHSB = new float[3];
        float[] c1HSB = new float[3];
        // Convert colours 1 & 2 to HSB
        Color.RGBtoHSB(c.getRed(), c.getGreen(), c.getBlue(), cHSB);
        Color.RGBtoHSB(c1.getRed(), c1.getGreen(), c1.getBlue(), c1HSB);
        // Hue interpolation
        float cHue = cHSB[0];
        float c1Hue = c1HSB[0];
        float hue;
        if (cHue < c1Hue) {
            hue = cHue + (c1Hue - cHue) * p;
        } else {
            hue = c1Hue + (cHue - c1Hue) * p;
        }
        return Color.getHSBColor(hue, cHSB[1], cHSB[2]);
    }

    /**
     * <b>Apply interpolation from list of many colours (Bezier method).</b>
     * <p>Parameter p sets the scale along the interpolation the color result will be</p>
     *
     * @param p    the percentage along the interpolation scale
     * @param clrs list of colours
     * @return interpolate colour
     */
    private static Color interpolateColour(float p, Color... clrs) {
        double r = 0;
        double g = 0;
        double b = 0;
        double total = 0;
        double step = 1 / (float) (clrs.length - 1);
        double mu = 0;
        double sigma = 0.035;
        for (Color clr : clrs) {
            total += (Math.exp(-(p - mu) * (p - mu) / (2.0 * sigma)) / Math.sqrt(2.0 * Math.PI * sigma));
            mu += step;
        }
        mu = 0;
        for (Color clr : clrs) {
            double percent = Math.exp(-(p - mu) * (p - mu) / (2.0 * sigma)) / Math.sqrt(2.0 * Math.PI * sigma);
            mu += step;
            r += clr.getRed() * percent / total;
            g += clr.getGreen() * percent / total;
            b += clr.getBlue() * percent / total;
        }
        return ColourEffects.getColour((int) r, (int) g, (int) b);
    }

    /**
     * <b>Return the colour parsed from RGB parameters.</b>
     * <p>Colour RGB parameters are in integer (between 0-255).</p>
     *
     * @param r redness of colour
     * @param g greenness of colour
     * @param b blueness of colour
     * @return the colour from rgb
     */
    public static Color getColour(int r, int g, int b) {
        return ColourEffects.getColour((float) r / 255, (float) g / 255, (float) b / 255);
    }

    /**
     * <b>Return the colour parsed from RGB parameters.</b>
     * <p>Colour RGB parameters are in float (between 0-1).</p>
     *
     * @param r redness of colour
     * @param g greenness of colour
     * @param b blueness of colour
     * @return the colour from rgb
     */
    public static Color getColour(float r, float g, float b) {
        return new Color(r, g, b);
    }

    /**
     * <b>Return the colour parsed from HSB parameters.</b>
     * <p>Colour HSB parameters are in float (between 0-1).</p>
     *
     * @param h hue of colour
     * @param s saturation of colour
     * @param b brightness of colour
     * @return the colour from hsb
     */
    public static Color getColourFromHSB(float h, float s, float b) {
        return Color.getHSBColor(h, s, b);
    }

    /**
     * <b>Return md_5's ChatColor parsed from HSB parameters.</b>
     * <p>Colour HSB parameters are in float (between 0-1).</p>
     *
     * @param h hue of colour
     * @param s saturation of colour
     * @param b brightness of colour
     * @return the md_5 ChatColor from HSB
     */
    public static ChatColor getChatColor(float h, float s, float b) {
        return ChatColor.of(Color.getHSBColor(h, s, b));
    }

    /**
     * <b>Convert text that contains '&' as an identifier for Minecraft Colour codes and apply the colour codes
     * as md_5's ChatColors.</b>
     * <p>For example: '&4Hello' would change to <span style="color: red">Hello</span></p>
     *
     * @param text text to translate
     * @return text with translated colour codes
     */
    public static String translateColourCodes(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    /**
     * <b>Return the color by the hex parameter.</b>
     * <p>Hex must be '#RRGGBB' format.</p>
     *
     * @param hex hex colour code
     * @return colour of hex
     */
    public static Color getColour(String hex) {
        return Color.decode(hex);
    }

    /**
     * <b>Apply md_5 ChatColor to text.</b>
     *
     * @param text the text
     * @param c    ChatColor to apply
     * @return the text with colour
     */
    private static String chatColor(String text, ChatColor c) {
        StringBuilder newText = new StringBuilder();
        newText.append(c);
        newText.append(text);
        return newText.toString();
    }


}