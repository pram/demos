package com.naughtyzombie.graphicsdemo;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;

/**
 * Created by pram on 17/04/2015.
 */

public class RotateDemo {
    public static void main(String[] args) throws Exception {
        new RotateDemo();
    }

    public RotateDemo() throws Exception {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame editorFrame = new JFrame("Image Demo");
                editorFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

                BufferedImage image = null;
                try {
                    image = ImageIO.read(RotateDemo.class.getResource("/london.jpg"));


                    // The required drawing location
                    int drawLocationX = 300;
                    int drawLocationY = 300;

// Rotation information

                    double rotationRequired = Math.toRadians(45);
                    double locationX = image.getWidth() / 2;
                    double locationY = image.getHeight() / 2;
                    AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
                    AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);

// Drawing the rotated image at the required drawing locations
                    //g2d.drawImage(op.filter(image, null), drawLocationX, drawLocationY, null);

                } catch (Exception e) {
                    e.printStackTrace();
                    System.exit(1);
                }
                ImageIcon imageIcon = new ImageIcon(image);
                JLabel jLabel = new JLabel();
                jLabel.setIcon(imageIcon);
                editorFrame.getContentPane().add(jLabel, BorderLayout.CENTER);

                editorFrame.pack();
                editorFrame.setLocationRelativeTo(null);
                editorFrame.setVisible(true);
            }
        });
    }
}
