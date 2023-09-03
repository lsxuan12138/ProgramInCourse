cmake_minimum_required(VERSION 3.25)
project(green)

set(CMAKE_CXX_STANDARD 17)
set(JPEG_DIR ${CMAKE_SOURCE_DIR}/libjpeg-turbo)
set(SDL2_DIR ${CMAKE_SOURCE_DIR}/SDL2)
set(SDL2_IMAGE_DIR ${CMAKE_SOURCE_DIR}/SDL2_image)

add_executable(green src/green.cpp)

target_include_directories(
        green
        PRIVATE
        ${JPEG_DIR}/include
        ${SDL2_DIR}/include
        ${SDL2_IMAGE_DIR}/include
)
target_link_directories(
        green
        PRIVATE
        ${JPEG_DIR}/lib
        ${SDL2_DIR}/lib/x64
        ${SDL2_IMAGE_DIR}/lib/x64
)
# 添加链接库
target_link_libraries(
        green
        PRIVATE
        jpeg
        SDL2
        SDL2_image
)
