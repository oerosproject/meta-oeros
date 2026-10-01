# Copyright (c) 2026 Wind River Systems, Inc.

SUMMARY = "On-target ROS 2 workspace build environment for OEROS containers"
DESCRIPTION = "What colcon build needs inside a container: a native compiler \
and build tools, cmake, git, colcon, and the ament/rosidl build tooling and \
client library headers.  Enough to ros2 pkg create a package and build it.  \
The ament and rclcpp set is ROS_SDK_TARGET_PACKAGES, the same list the \
ros2-image-sdktest SDK ships, so a workspace builds the same way here as \
against the cross SDK."

inherit packagegroup
inherit ros_distro_${ROS_DISTRO}

# packagegroup-core-buildessential is the on-target toolchain (gcc, g++,
# binutils, make, libstdc++-dev, autoconf, automake, libtool, pkgconfig).
# Headers for everything else come from the image's "dev-pkgs" feature.
#
# coreutils replaces BusyBox's applets: the setup scripts colcon writes into a
# workspace's install/ use options BusyBox lacks (head -c), and print an error
# every time they are sourced.
#
# ros2 pkg create generates packages that use ament_lint_auto, whose CMake hooks
# look for the lint executables while configuring.  ament-lint-common pulls in
# only the ament-cmake-* wrappers, not the tools behind them, so without the
# ament-* packages below "colcon build" of a freshly created package stops with
# "ament_cppcheck() could not find program 'ament_cppcheck'".
RDEPENDS:${PN} = " \
    packagegroup-core-buildessential \
    cmake \
    git \
    coreutils \
    python3-colcon-common-extensions \
    ${ROS_SDK_TARGET_PACKAGES} \
    ament-lint-common \
    ament-copyright \
    ament-cppcheck \
    ament-cpplint \
    ament-flake8 \
    ament-lint-cmake \
    ament-pep257 \
    ament-uncrustify \
    ament-xmllint \
"
