
#include "SDL.h"
#include "SDL_image.h"
#include "green.hpp"

#include <iostream>
#include <cmath>
#include <exception>
#include <stdexcept>
#include <filesystem>

// taken from jcolor.c in libjpeg
#if 0   // 16bit - precise but slow
#define CYR     19595   // 0.299
#define CYG     38470   // 0.587
#define CYB      7471   // 0.114

#define CUR    (-11059)   // -0.16874
#define CUG    (-21709)   // -0.33126
#define CUB     32768   // 0.5

#define CVR     32768   // 0.5
#define CVG    (-27439)   // -0.41869
#define CVB     (-5329)   // -0.08131

#define CSHIFT  16
#else      // 8bit - fast, slightly less precise
#define CYR     77    // 0.299
#define CYG     150    // 0.587
#define CYB      29    // 0.114

#define CUR     (-43)    // -0.16874
#define CUG    (-85)    // -0.33126
#define CUB     128    // 0.5

#define CVR      128   // 0.5
#define CVG     (-107)   // -0.41869
#define CVB      (-21)   // -0.08131

#define CSHIFT  8
#endif

using namespace std;

void do_compress(string src, string dst) {
    SDL_Surface *image = IMG_Load(src.c_str());
    FILE *outfile = fopen(dst.c_str(), "wb");

    if (!image) throw runtime_error("Problem opening input file");
    if (!outfile) throw runtime_error("Problem opening output file");

    JpegCompress jc(image);

    auto *in_buffer = new uint8_t[image->w * image->h * 3];
    for (int x = 0; x < image->w; x++)
        for (int y = 0; y < image->h; y++) {
            auto *i = (uint8_t *) image->pixels + y * image->pitch + x * image->format->BytesPerPixel;
            auto *o = &in_buffer[y * image->w * 3 + x * 3];

            int R = i[0], G = i[1], B = i[2];

#if 1 // Shift or float-divide (shift in Skia)
            int Y = (R*CYR + G*CYG + B*CYB) >> CSHIFT;
            int U = (R*CUR + G*CUG + B*CUB) >> CSHIFT;
            int V = (R*CVR + G*CVG + B*CVB) >> CSHIFT;

            o[0] = Y;
            o[1] = U + 128;
            o[2] = V + 128;
#else
            double Y = (R * CYR + G * CYG + B * CYB) / pow(2, CSHIFT);
            double U = (R * CUR + G * CUG + B * CUB) / pow(2, CSHIFT);
            double V = (R * CVR + G * CVG + B * CVB) / pow(2, CSHIFT);

            o[0] = round(Y);
            o[1] = round(U + 128);
            o[2] = round(V + 128);
#endif

        }

    // typedef enum {
    //   JDCT_ISLOW,             /* slow but accurate integer algorithm */
    //   JDCT_IFAST,             /* faster, less accurate integer method */
    //   JDCT_FLOAT              /* floating-point: accurate, fast on fast HW */
    // } J_DCT_METHOD;

    jc.Compress(in_buffer, 80, JDCT_IFAST);

    fwrite(jc.GetBuffer(), jc.GetSize(), 1, outfile);
    fclose(outfile);
}

#undef main
int main(int argc, char **argv) {
//    string src_directory_path = "E:/Project/PycharmProjects/spider/image";
//    string dest_directory_path = "E:/Project/PycharmProjects/spider/green";
//
//    // 遍历目录下的所有文件
//    for (const auto& entry : std::filesystem::directory_iterator(src_directory_path)) {
//        if (entry.is_regular_file()) {
//            // 如果是普通文件，输出文件名和大小
//            string filename = "/"+entry.path().filename().string();
//            string src_path = src_directory_path+filename;
//            //std::cout << src_path << std::endl;
//            string dest_path = dest_directory_path+filename;
//            std::cout << dest_path << std::endl;
//            do_compress(src_path, dest_path);
//            for (size_t i = 0; i < 30; i++) {
//                do_compress(dest_path, dest_path);
//            }
//        }
//    }
    //if (argc != 3) return -1;
    //string source(argv[1]), target(argv[2]);
    string source("../temp/test.jpg");
    string target("../temp/test_green.jpg");
    do_compress(source, target);
    for (size_t i = 0; i < 30; i++) {
        do_compress(target, target);
    }
    return 0;
}
