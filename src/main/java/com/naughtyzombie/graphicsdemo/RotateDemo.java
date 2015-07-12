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
                    int type = image.getType();

                    int[][] imgMatrix = new int[image.getHeight()][image.getWidth()];

                    int rows = imgMatrix.length;
                    int columns = imgMatrix[0].length;

                    for (int i=0; i < rows; i++) {
                        for (int j=0; j < columns; j++) {
                            imgMatrix[i][j] = image.getRGB(j,i);
                        }
                    }

                    imgMatrix = rotateCW(imgMatrix);

                    rows = imgMatrix.length;
                    columns = imgMatrix[0].length;
                    image = new BufferedImage(columns,rows,type);

                    for (int i=0; i < rows; i++) {
                        for (int j=0; j < columns; j++) {
                            image.setRGB(j,i, imgMatrix[i][j]);
                        }
                    }



                    // The required drawing location
                    /*int drawLocationX = 300;
                    int drawLocationY = 300;*/

                    // Rotation information
                    /*double rotationRequired = Math.toRadians(45);
                    double locationX = image.getWidth() / 2;
                    double locationY = image.getHeight() / 2;
                    AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
                    AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);*/

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

    public static void rotate(int[][] matrix) {
        int n = matrix.length;
        if (n <= 1) {
            return; // nothing to do
        }

        /* layers */
        for (int i = 0; i < n / 2; i++) {
            /* elements */
            for (int j = i; j < n - i - 1; j++) {
                int saved = matrix[i][j];
                matrix[i][j] = matrix[n - j - 1][i];
                matrix[n - j - 1][i] = matrix[n - 1 - i][n - 1 - j];
                matrix[n - 1 - i][n - 1 - j] = matrix[j][n - 1 - i];
                matrix[j][n - 1 - i] = saved;
            }
        }
    }

    public static void rotate2(int[][] matrix) {
        transpose(matrix);
        reflect(matrix);
    }

    public static void reflect(int[][] arr) {
        int size = arr.length;
        for (int k = 0; k < size; k++) {
            for (int i = 0; i < Math.floor(size / 2); i++) {
                swap(arr, k, i, k, size - i - 1);
            }
        }
    }

    public static void transpose(int[][] arr) {
        int size = arr.length;
        for (int diag = 0; diag < size; diag++) {
            for (int i = diag + 1; i < size; i++) {
                swap(arr, diag, i, i, diag);
            }
        }
    }

    public static void swap(int[][] arr, int row1, int col1, int row2, int col2) {
        int num1 = arr[row1][col1];
        int num2 = arr[row2][col2];
        arr[row1][col1] = num2;
        arr[row2][col2] = num1;
    }

    public int[][] rotateCW(int[][] mat) {
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
