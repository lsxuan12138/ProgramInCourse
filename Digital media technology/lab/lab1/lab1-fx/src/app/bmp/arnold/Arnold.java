package app.bmp.arnold;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.imgcodecs.Imgcodecs;

import static java.util.Arrays.asList;

public class Arnold {
    private final Mat img;
    private final int x;
    private final int y;

    private final int width;
    private final int height;
    private final int count;
    private final int a;
    private final int b;

    public Arnold(Mat img, int height, int width, int count, int a, int b) {
        this.img = img;
        this.x = img.height();
        this.y = img.width();
        this.height = height;
        this.width = width;
        this.count = count;
        this.a = a;
        this.b = b;
    }

    public Mat completion() {
        int row = img.rows(), col = img.cols();
        int rh = row % this.height;
        int cw = col % this.width;

        if (rh == 0 && cw == 0) return this.img;
        else {
            if (cw != 0) {
                int num = this.width - cw;
                Mat z = Mat.zeros(row, num, img.type());
                Core.hconcat(asList(img, z), img);
            }
            row = img.rows();
            col = img.cols();
            if (rh != 0) {
                int num = this.height - rh;
                Mat z = Mat.zeros(num, col, img.type());
                Core.vconcat(asList(img, z), img);
            }
            row = img.rows();
            col = img.cols();
            int M = row / height;
            int N = col / width;
            if (M == N) return img;
            else if (M > N) {
                int num = (M - N) * width;
                Mat z = Mat.zeros(row, num, img.type());
                Core.hconcat(asList(img, z), img);
            } else {
                int num = (N - M) * height;
                Mat z = Mat.zeros(num, col, img.type());
                Core.vconcat(asList(img, z), img);
            }
        }
        return this.img;
    }

    public Mat arnold() {
        int count = this.count;
        Mat newImg = Mat.zeros(img.rows(), img.cols(), img.type());
        int row = img.rows(), col = img.cols();
        int M = row / this.height;
        int N = col / this.width;
        while (count > 0) {
            for (int i = 0; i < N; i++) {
                int xx = i * width;
                for (int j = 0; j < M; j++) {
                    int yy = j * height;
                    Mat subArr = img.submat(yy, yy + height, xx, xx + width);
                    int newI = (i + b * j) % N;
                    int newJ = (a * i + (a * b + 1) * j) % M;
                    int newX = newI * width;
                    int newY = newJ * height;
                    Rect area = new Rect(newX, newY, width, height);
                    subArr.copyTo(new Mat(newImg, area));
                }
            }
            count--;
        }
        return newImg;
    }

    public Mat inverseArnold(Mat img, int count) {
        Mat newImg = Mat.zeros(img.rows(), img.cols(), img.type());
        int row = img.rows(), col = img.cols(), n = img.channels();
        int M = row / this.height;
        int N = col / this.width;
        while (count > 0) {
            for (int i = 0; i < N; i++) {
                int xx = i * width;
                for (int j = 0; j < M; j++) {
                    int yy = j * height;
                    Mat subArr = img.submat(yy, yy + height, xx, xx + width);
                    int newI = ((a * b + 1) * i - b * j) % M;
                    if (newI < 0) newI += M;
                    int newJ = (-a * i + j) % N;
                    if (newJ < 0) newJ += N;
                    int newX = newI * width;
                    int newY = newJ * height;
                    Rect area = new Rect(newX, newY, width, height);
                    subArr.copyTo(new Mat(newImg, area));
                }
            }
            count--;
        }
//        Rect rect = new Rect(0,0,this.y,this.x);
//        return new Mat(newImg,rect);
        return newImg;
    }

    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        Mat img = Imgcodecs.imread("src/image/lena.bmp");
        int count = 5, a = 3, b = 4, width = 32, height = 32;
        Arnold arnold = new Arnold(img, height, width, count, a, b);
        arnold.completion();
        Mat newImg = arnold.arnold();
        Imgcodecs.imwrite("src/image/test-1.bmp", newImg);
        Mat re = arnold.inverseArnold(newImg, count);
        Imgcodecs.imwrite("src/image/test-2.bmp", re);
    }
}
