package com.naughtyzombie.graphicsdemo;

import java.awt.*;
import java.awt.image.*;
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
                    int type = image.getType();

                    int[][] imgMatrix = new int[image.getHeight()][image.getWidth()];

                    int rows = imgMatrix.length;
                    int columns = imgMatrix[0].length;

                    for (int i=0; i < rows; i++) {
                        for (int j=0; j < columns; j++) {
                            imgMatrix[i][j] = image.getRGB(j,i);
                        }
                    }

                    imgMatrix = rotate(imgMatrix);

                    rows = imgMatrix.length;
                    columns = imgMatrix[0].length;
                    image = new BufferedImage(columns,rows,type);

                    for (int i=0; i < rows; i++) {
                        for (int j=0; j < columns; j++) {
                            image.setRGB(j,i, imgMatrix[i][j]);
                        }
                    }
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

    public int[][] rotate(int[][] mat) {
        final int M = mat.length;
        final int N = mat[0].length;
        int[][] ret = new int[N][M];
        for (int r = 0; r < M; r++) {
            for (int c = 0; c < N; c++) {
                ret[c][M-1-r] = mat[r][c];
            }
        }
        return ret;
    }
}
