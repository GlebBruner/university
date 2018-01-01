package lab1;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class GaussianFilter {

    private static BufferedImage inputImage = null;

    private static double gaussianFunction(int x, int y, double sigma) {
        return (1 / (2 * Math.PI * Math.pow(sigma, 2)))*(Math.exp(  -(Math.pow(x,2) + Math.pow(y, 2)) / (2*Math.pow(sigma,2)) ));
    }

    private static BufferedImage openImage(String path) {

        if (inputImage == null){
            try {
                inputImage = ImageIO.read(new File(path));
            } catch (IOException exception) {
                String workingDir = System.getProperty("user.dir");
                System.out.println("Current directory is " + workingDir);
                exception.getStackTrace();
                System.out.println(exception.getMessage());
            }
             return inputImage;
        } else { return inputImage; }

    }

    private static double[][] buildKernel(int size, double sigma){
        double[][] kernel = new double[size][size];
        double sum = 0.0;
        int modifier = size /2;

        //Cчитаем ненормированную матрицу. Выводим сумму, на которую будем потом делить, чтобы нормировать
        for (int i = -modifier; i <= modifier; i++) {
            for (int j = -modifier; j <=  modifier; j++) {
                kernel[i+modifier][j+modifier] = GaussianFilter.gaussianFunction(i,j,sigma);
                sum+=kernel[i+modifier][j+modifier];
            }
        }

        for (int i = -modifier; i <= modifier; i++) {
            for (int j = -modifier; j <=  modifier; j++) {
                kernel[i+modifier][j+modifier] = kernel[i+modifier][j+modifier] / sum;
            }
        }

        return kernel;
    }

    private static void gaussianBlur(String path, int kernelSize, double sigma) {
        //creating working place
        int[][] workingPlace = new int[kernelSize][kernelSize];

        //modifier. ex: size= 5, mod. = 2 ---- -2;-2....
        int modifier = kernelSize / 2;

        //image loading and getting size
        BufferedImage inputImage = openImage(path);
        int imageWidth = inputImage.getWidth();
        int imageHeight = inputImage.getHeight();

        //create output image
        BufferedImage outputImage = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);

        //getting whole image as 2D array
        int[][] imageInputArray = new int[imageHeight][imageWidth];

        for (int i = 0; i < imageHeight; i++) {
            for (int j = 0; j < imageWidth; j++) {
                imageInputArray[i][j] = inputImage.getRGB(j,i);
            }
        }

        //build kernel
        double[][] kernel = buildKernel(kernelSize, sigma);

        //going though IMAGE ARRAY
        for (int height = 0; height < imageHeight ; height++) {

            for (int width = 0; width < imageWidth; width++) {

                int workingPlaceHeight = 0;
                int workingPlaceWidth = 0;

                //Building Array which will * kernel.

                for (int i = height - modifier; i <=  height + modifier; i++) {

                    for (int j = width - modifier; j <=width + modifier; j++) {

                        if ( (i < 0 || i >= imageHeight) || (j < 0 || j >= imageWidth) ) {
                            workingPlace[workingPlaceHeight][workingPlaceWidth] = 0;
                            workingPlaceWidth++;
                        }
                        else {
                            workingPlace[workingPlaceHeight][workingPlaceWidth] =  imageInputArray[i][j];
                            workingPlaceWidth++;
                        }
                    }
                    workingPlaceWidth = 0;
                    workingPlaceHeight++;
                }

                for (int i = 0; i < kernelSize; i++) {
                    for (int j = 0; j < kernelSize; j++) {
                        if (workingPlace[i][j] == 0) {
                            workingPlace[i][j] = workingPlace[kernelSize - 1 - i][kernelSize - 1 - j];
                        }
                    }
                }

                double  sum_red = 0;
                double  sum_green = 0;
                double  sum_blue = 0;

                //new int pixel
                for (int i = 0; i < kernelSize; i++) {
                    for (int j = 0; j < kernelSize; j++) {
                        sum_red += ((workingPlace[i][j] & 0x00ff0000) >> 16) * kernel[i][j];
                        sum_green += ((workingPlace[i][j] & 0x0000ff00) >> 8 ) * kernel[i][j];
                        sum_blue += ((workingPlace[i][j] & 0x000000ff) ) * kernel[i][j];
                    }
                }
                Long finalRed = Math.round(sum_red);
                Long finalGreen = Math.round(sum_green);
                Long finalBlue = Math.round(sum_blue);
                int newPixel = new Color(finalRed.intValue(), finalGreen.intValue(), finalBlue.intValue()).getRGB();
                imageInputArray[height][width] = newPixel;
            }
        }

        //Image array creating
        for (int i = 0; i < imageHeight; i++) {
            for (int j = 0; j < imageWidth; j++) {
                outputImage.setRGB(j,i, imageInputArray[i][j]);
            }
        }

        //Image creating
        File outputFile = new File("src/lab1/out.jpg");
        try {
            ImageIO.write(outputImage, "jpg", outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void gaussianBlurMultiCore(String path, int kernelSize, double sigma, int threads) {

        //INPUT ARRAY FOR EACH THREAD
        //OUTPUT ARRAY FOR EACH THREAD

        ExecutorService service = Executors.newFixedThreadPool(threads);
        List<Callable<Object>> tasks = new ArrayList<>();

        BufferedImage inputImage = openImage(path);
        int imageWidth = inputImage.getWidth();
        int imageHeight = inputImage.getHeight();

        BufferedImage outputImage = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);

        /*
        int[][] imageInputArray = new int[imageHeight][imageWidth];

        for (int i = 0; i < imageHeight; i++) {
            for (int j = 0; j < imageWidth; j++) {
                imageInputArray[i][j] = inputImage.getRGB(j,i);
            }
        }*/
        for (int i = 0; i < threads; i++) {
            int integerNum = imageHeight / threads;
            int startHeight = integerNum * i;
            int endHeight;

            if ((startHeight + integerNum - 1) > imageHeight) {
                endHeight = imageHeight - 1;
            } else {
                endHeight = startHeight + integerNum - 1;
            }


            tasks.add(new Callable<Object>() {
                @Override
                public Object call() throws Exception {
                    System.out.println("run");
                    int[][] workingPlace = new int[kernelSize][kernelSize];
                    int modifier = kernelSize / 2;
                    double[][] kernel = buildKernel(kernelSize, sigma);


                    int [][] imageInputArray = new int[imageHeight][imageWidth];

                    for (int i = startHeight; i <= endHeight; i++) {
                        for (int j = 0; j < imageWidth; j++) {
                            imageInputArray[i][j] = inputImage.getRGB(j,i);
                        }
                    }

                    GaussianFilter.processImagePart(startHeight, endHeight, imageHeight, imageWidth, imageInputArray,
                            workingPlace, modifier, kernelSize, kernel);


                    for (int i = startHeight; i <= endHeight; i++) {
                        for (int j = 0; j < imageWidth; j++) {
                            outputImage.setRGB(j,i, imageInputArray[i][j]);
                        }
                    }
                    System.out.println("end");
                    return null;
                }
            });
        }

        try {
            List<Future<Object>> futures = service.invokeAll(tasks);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }



        /*for (int i = 0; i < imageHeight; i++) {
            for (int j = 0; j < imageWidth; j++) {
                outputImage.setRGB(j,i, imageInputArray[i][j]);
            }
        }*/

        File outputFile = new File("src/lab1/out.jpg");
        try {
            ImageIO.write(outputImage, "jpg", outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void processImagePart(int startHeight, int endHeight, int imageHeight, int imageWidth,
                                  int[][] imageInputArray, int[][] workingPlace, int modifier, int kernelSize,
                                 double [][] kernel) {

        for (int height = startHeight; height <= endHeight ; height++) { //отдельно

            for (int width = 0; width < imageWidth; width++) {

                int workingPlaceHeight = 0;
                int workingPlaceWidth = 0;

                //Building Array which will * kernel.

                for (int i = height - modifier; i <=  height + modifier; i++) {

                    for (int j = width - modifier; j <=width + modifier; j++) {

                        if ( (i < 0 || i >= imageHeight) || (j < 0 || j >= imageWidth) ) {
                            workingPlace[workingPlaceHeight][workingPlaceWidth] = 0;
                            workingPlaceWidth++;
                        }
                        else {
                            workingPlace[workingPlaceHeight][workingPlaceWidth] =  imageInputArray[i][j];
                            workingPlaceWidth++;
                        }
                    }
                    workingPlaceWidth = 0;
                    workingPlaceHeight++;
                }

                for (int i = 0; i < kernelSize; i++) {
                    for (int j = 0; j < kernelSize; j++) {
                        if (workingPlace[i][j] == 0) {
                            workingPlace[i][j] = workingPlace[kernelSize - 1 - i][kernelSize - 1 - j];
                        }
                    }
                }

                double  sum_red = 0;
                double  sum_green = 0;
                double  sum_blue = 0;

                //new int pixel
                for (int i = 0; i < kernelSize; i++) {
                    for (int j = 0; j < kernelSize; j++) {
                        sum_red += ((workingPlace[i][j] & 0x00ff0000) >> 16) * kernel[i][j];
                        sum_green += ((workingPlace[i][j] & 0x0000ff00) >> 8 ) * kernel[i][j];
                        sum_blue += ((workingPlace[i][j] & 0x000000ff) ) * kernel[i][j];
                    }
                }
                Long finalRed = Math.round(sum_red);
                Long finalGreen = Math.round(sum_green);
                Long finalBlue = Math.round(sum_blue);
                int newPixel = new Color(finalRed.intValue(), finalGreen.intValue(), finalBlue.intValue()).getRGB();
                imageInputArray[height][width] = newPixel;
            }
        }

    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        GaussianFilter.gaussianBlur("src/lab1/pug1.jpg", 21, 50);
        Instant stop = Instant.now();
        //System.out.println(Duration.between(start, stop));
        System.out.println(System.currentTimeMillis() - start);

        //Instant start_multi = Instant.now();
        long start_multi = System.currentTimeMillis();
        GaussianFilter.gaussianBlurMultiCore("src/lab1/mountains1.jpg", 9, 3, 4);
        //Instant stop_multi = Instant.now();
        //System.out.println(Duration.between(start_multi,stop_multi));
        System.out.println(System.currentTimeMillis() - start_multi);
    }

}
