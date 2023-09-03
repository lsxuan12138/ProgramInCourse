cmake_minimum_required(VERSION 3.25)
project(compress)

set(JPEG_DIR ${CMAKE_SOURCE_DIR}/libjpeg-turbo)
set(SDL2_DIR ${CMAKE_SOURCE_DIR}/SDL2)
set(SDL2_IMAGE_DIR ${CMAKE_SOURCE_DIR}/SDL2_image)

add_executable(compress compress.cpp compress.hpp)

target_include_directories(
        compress
        PRIVATE
        ${JPEG_DIR}/include
        ${SDL2_DIR}/include
        ${SDL2_IMAGE_DIR}/include
)
target_link_directories(
        compress
        PRIVATE
        ${JPEG_DIR}/lib
        ${SDL2_DIR}/lib/x64
        ${SDL2_IMAGE_DIR}/lib/x64
)
# 添加链接库
target_link_libraries(
        compress
        PRIVATE
        jpeg
        SDL2
        SDL2_image
)