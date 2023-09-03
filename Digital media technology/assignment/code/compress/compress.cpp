#include "SDL.h"
#include "SDL_image.h"
#include "compress.hpp"

#include <iostream>
#include <cmath>
#include <exception>
#include <stdexcept>
#include <random>
#include <chrono>
#include <filesystem>

using namespace std;
void do_compress(string src, string dst,int quality) {
    SDL_Surface *image = IMG_Load(src.c_str());
    FILE *outfile = fopen(dst.c_str(), "wb");

    if (!image) throw runtime_error("Problem opening input file");
    if (!outfile) throw runtime_error("Problem opening output file");

    JpegCompress jc(image);

    auto * buffer = (uint8_t *) image->pixels;

    jc.Compress(buffer, quality, JDCT_FLOAT);

    fwrite(jc.GetBuffer(), jc.GetSize(), 1, outfile);
    fclose(outfile);
}

#undef main
int main(int argc, char **argv) {
//    string src_directory_path = "E:/Project/PycharmProjects/spider/image";
//    string dest_directory_path = "E:/Project/PycharmProjects/spider/compress";
//    std::default_random_engine generator(std::chrono::system_clock::now().time_since_epoch().count());
//    // 创建一个均匀分布，范围为60到80
//    std::uniform_int_distribution<int> distribution(60, 80);
//    // 遍历目录下的所有文件
//    for (const auto& entry : std::filesystem::directory_iterator(src_directory_path)) {
//        if (entry.is_regular_file()) {
//            // 如果是普通文件，输出文件名和大小
//            string filename = "/"+entry.path().filename().string();
//            string src_path = src_directory_path+filename;
//            //std::cout << src_path << std::endl;
//            string dest_path = dest_directory_path+filename;
//            std::cout << dest_path << std::endl;
//            int quality = distribution(generator);
//
//            do_compress(src_path, dest_path,quality);
//            for (size_t i = 0; i < 30; i++) {
//                quality = distribution(generator);
//                do_compress(dest_path, dest_path,quality);
//            }
//        }
//    }
    //if (argc != 3) return -1;
    //string source(argv[1]), target(argv[2]);
    string source("../temp/test.jpg");
    string target("../temp/test_compress.jpg");
    // 使用系统时钟作为随机数种子
    std::default_random_engine generator(std::chrono::system_clock::now().time_since_epoch().count());
    // 创建一个均匀分布，范围为60到80
    std::uniform_int_distribution<int> distribution(60, 80);
    int quality = distribution(generator);
    do_compress(source, target,quality);
    for (size_t i = 0; i < 500; i++) {
        quality = distribution(generator);
        do_compress(target, target,quality);
    }
    return 0;
}