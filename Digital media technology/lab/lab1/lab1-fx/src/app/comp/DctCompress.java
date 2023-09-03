package app.comp;

import org.opencv.core.*;

import static java.util.Arrays.asList;
import static org.opencv.imgproc.Imgproc.GaussianBlur;

public class DctCompress {
    public static Mat completion(Mat in, int blockSize) {
        Mat img = new Mat();
        in.copyTo(img);
        int col = img.cols(), row = img.rows();
        int rh = row % blockSize;
        int cw = col % blockSize;

        if (cw != 0) {
            int num = blockSize - cw;
            Mat z = Mat.zeros(row, num, img.type());
            Core.hconcat(asList(img, z), img);
        }
        col = img.cols();
        if (rh != 0) {
            int num = blockSize - rh;
            Mat z = Mat.zeros(num, col, img.type());
            Core.vconcat(asList(img, z), img);
        }
        return img;
    }

    public static Mat compress(Mat img) {

        // 加载图像
        //Mat img = Imgcodecs.imread("input.jpg");

        // 转换为灰度图像
        Mat gray = img.clone();
        //Imgproc.cvtColor(img, gray, Imgproc.COLOR_BGR2GRAY);
        gray = completion(gray, 8);

        // 分块
        int blockWidth = 8;
        int blockHeight = 8;
        int numBlocksX = gray.cols() / blockWidth;
        int numBlocksY = gray.rows() / blockHeight;
        Mat[] blocks = new Mat[numBlocksX * numBlocksY];
        for (int i = 0, k = 0; i < numBlocksY; i++) {
            for (int j = 0; j < numBlocksX; j++, k++) {
                Mat block = gray.submat(i * blockHeight, (i + 1) * blockHeight,
                        j * blockWidth, (j + 1) * blockWidth);
                blocks[k] = block.clone();
            }
        }

        // DCT变换
        Mat[] dctBlocks = new Mat[blocks.length];
        for (int i = 0; i < blocks.length; i++) {
            Mat dct = new Mat();
            blocks[i].convertTo(blocks[i], CvType.CV_32F);
            Core.dct(blocks[i], dct);
            dctBlocks[i] = dct.clone();
        }

        // Zigzag排序
        int[] zigzag = {
                0, 1, 5, 6, 14, 15, 27, 28,
                2, 4, 7, 13, 16, 26, 29, 42,
                3, 8, 12, 17, 25, 30, 41, 43,
                9, 11, 18, 24, 31, 40, 44, 53,
                10, 19, 23, 32, 39, 45, 52, 54,
                20, 22, 33, 38, 46, 51, 55, 60,
                21, 34, 37, 47, 50, 56, 59, 61,
                35, 36, 48, 49, 57, 58, 62, 63
        };
        Mat[] zigzagBlocks = new Mat[dctBlocks.length];
        for (int i = 0; i < dctBlocks.length; i++) {
            float[] dctArray = new float[64];
            dctBlocks[i].get(0, 0, dctArray);
            float[] zigzagArray = new float[64];
            for (int j = 0; j < 64; j++) {
                zigzagArray[j] = dctArray[zigzag[j]];
            }
            Mat zigzagMat = new Mat(8, 8, CvType.CV_32F);
            zigzagMat.put(0, 0, zigzagArray);
            zigzagBlocks[i] = zigzagMat.clone();
        }

        // 量化
        int[] quantizationTable = {
                16, 11, 10, 16, 24, 40, 51, 61,
                12, 12, 14, 19, 26, 58, 60, 55,
                14, 13, 16, 24, 40, 57, 69, 56,
                14, 17, 22, 29, 51, 87, 80, 62,
                18, 22, 37, 56, 68, 109, 103, 77,
                24, 35, 55, 64, 81, 104, 113, 92,
                49, 64, 78, 87, 103, 121, 120, 101,
                72, 92, 95, 98, 112, 100, 103, 99
        };
        Mat mat = new Mat(new Size(8, 8), CvType.CV_32S);
        mat.put(0, 0, quantizationTable);
        Mat fq = new Mat();
        mat.convertTo(fq, CvType.CV_32F);
        Mat[] quantizedBlocks = new Mat[zigzagBlocks.length];
        for (int i = 0; i < zigzagBlocks.length; i++) {
            Mat quantized = new Mat();
            Core.divide(zigzagBlocks[i], fq, quantized);
            quantized.convertTo(quantized, CvType.CV_32S);
            quantizedBlocks[i] = quantized.clone();
        }

        // 反量化
        Mat[] dequantizedBlocks = new Mat[quantizedBlocks.length];
        for (int i = 0; i < quantizedBlocks.length; i++) {
            Mat dequantized = new Mat();
            quantizedBlocks[i].convertTo(dequantized, CvType.CV_32F);
            Core.multiply(dequantized, fq, dequantized);
            dequantizedBlocks[i] = dequantized.clone();
        }

        // IDCT变换
        Mat[] idctBlocks = new Mat[dequantizedBlocks.length];
        for (int i = 0; i < dequantizedBlocks.length; i++) {
            Mat idct = new Mat();
            Core.idct(dequantizedBlocks[i], idct);
            idctBlocks[i] = idct.clone();
        }

        // 重建图像
        Mat reconstructed = new Mat(gray.size(), gray.type());
        for (int i = 0, k = 0; i < numBlocksY; i++) {
            for (int j = 0; j < numBlocksX; j++, k++) {
                Mat block = new Mat(reconstructed, new Rect(j * blockWidth, i * blockHeight, blockWidth, blockHeight));
                Mat idct = new Mat();
                idctBlocks[k].convertTo(idct, gray.type());
                idct.copyTo(block);
            }
        }
        return reconstructed;
    }

    public static double getPSNR(Mat image1, Mat image2) {
        Mat diff = new Mat(); // 定义差值图像矩阵
        Core.absdiff(image1, image2, diff); // 计算两张图像的差值

        diff.convertTo(diff, CvType.CV_32F); // 转换成浮点型
        double mse = Core.mean(diff.mul(diff)).val[0]; // 计算均方误差
        double psnr = 10 * Math.log10(Math.pow(255, 2) / mse); // 计算 PSNR 值
        return psnr;
    }

    public static Scalar getSSIM(final Mat i1, final Mat i2) {
        final double C1 = 6.5025, C2 = 58.5225;
        //*************************** INITS **********************************/
        int d = CvType.CV_32F;

        Mat I1 = new Mat(), I2 = new Mat();
        i1.convertTo(I1, d);           // cannot calculate on one byte large values
        i2.convertTo(I2, d);

        Mat I2_2 = I2.mul(I2);        // I2^2
        Mat I1_2 = I1.mul(I1);        // I1^2
        Mat I1_I2 = I1.mul(I2);        // I1 * I2

        //************************* END INITS **********************************/

        Mat mu1 = new Mat(), mu2 = new Mat();   // PRELIMINARY COMPUTING
        GaussianBlur(I1, mu1, new Size(11, 11), 1.5);
        GaussianBlur(I2, mu2, new Size(11, 11), 1.5);

        Mat mu1_2 = mu1.mul(mu1);
        Mat mu2_2 = mu2.mul(mu2);
        Mat mu1_mu2 = mu1.mul(mu2);

        Mat sigma1_2 = new Mat(), sigma2_2 = new Mat(), sigma12 = new Mat();

        GaussianBlur(I1_2, sigma1_2, new Size(11, 11), 1.5);
        Core.subtract(sigma1_2, mu1_2, sigma1_2);

        GaussianBlur(I2_2, sigma2_2, new Size(11, 11), 1.5);
        Core.subtract(sigma2_2, mu2_2, sigma2_2);

        GaussianBlur(I1_I2, sigma12, new Size(11, 11), 1.5);
        Core.subtract(sigma12, mu1_mu2, sigma12);

        ///////////////////////////////// FORMULA ////////////////////////////////
        Mat t1, t2, t3;

        //t1 = 2 * mu1_mu2 + C1;
        t1 = numAdd(numMul(mu1_mu2, 2.0f), (float) C1);
        //t2 = 2 * sigma12 + C2;
        t2 = numAdd(numMul(sigma12, 2.0f), (float) C2);
        t3 = t1.mul(t2);              // t3 = ((2*mu1_mu2 + C1).*(2*sigma12 + C2))

        //t1 = mu1_2 + mu2_2 + C1;
        Core.add(mu1_2, mu2_2, mu2_2);
        t1 = numAdd(mu2_2, (float) C1);
        //t2 = sigma1_2 + sigma2_2 + C2;
        Core.add(sigma1_2, sigma2_2, sigma2_2);
        t2 = numAdd(sigma2_2, (float) C2);
        t1 = t1.mul(t2);               // t1 =((mu1_2 + mu2_2 + C1).*(sigma1_2 + sigma2_2 + C2))

        Mat ssim_map = new Mat();
        Core.divide(t3, t1, ssim_map);      // ssim_map =  t3./t1;

        return Core.mean(ssim_map);
    }

    public static Mat numAdd(Mat mat, float f) {
        Mat re = new Mat(mat.rows(), mat.cols(), mat.type());
        Scalar scalar = new Scalar(f);
        Core.add(mat, scalar, re);
        return re;
    }

    public static Mat numMul(Mat mat, float f) {
        Mat re = new Mat(mat.rows(), mat.cols(), mat.type());
        Scalar scalar = new Scalar(f);
        Core.multiply(mat, scalar, re);
        return re;
    }
}
