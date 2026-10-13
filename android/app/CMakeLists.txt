cmake_minimum_required(VERSION 3.18)
project(realsteel_android LANGUAGES CXX)

set(CMAKE_CXX_STANDARD 23)
set(CMAKE_POSITION_INDEPENDENT_CODE ON)

# repo root from android/app
get_filename_component(REPO_ROOT "${CMAKE_CURRENT_LIST_DIR}/../../.." ABSOLUTE)

set(GENERATED_DIR "${REPO_ROOT}/generated/default")
set(SRC_DIR "${REPO_ROOT}/src")

# Generated code + small host sources (stubs, etc.)
file(GLOB GENERATED_SOURCES "${GENERATED_DIR}/*.cpp")
set(REALSTEEL_SOURCES ${GENERATED_SOURCES} "${SRC_DIR}/stubs.cpp")

add_library(realsteel SHARED ${REALSTEEL_SOURCES})

target_include_directories(realsteel PRIVATE
    "${REPO_ROOT}"
    "${GENERATED_DIR}"
    "${SRC_DIR}"
)

# Include the project's generated rexglue helper. This will either use
# REXSDK_DIR (cache) or try to find_package(rexglue).
include("${REPO_ROOT}/generated/rexglue.cmake")

# Apply recomp settings + link runtime (rex::runtime must be available from SDK)
rexglue_apply_recomp_settings(realsteel "${GENERATED_DIR}/realsteel_pch.h")
target_link_libraries(realsteel PRIVATE rex::runtime)
