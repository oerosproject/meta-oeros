# Copyright (c) 2026 Wind River Systems, Inc.

SUMMARY = "OEROS container image for developing and debugging ROS 2 nodes"
DESCRIPTION = "ros-base plus a workspace build environment (compiler, cmake, \
colcon, ament) and gdb, strace, valgrind, tcpdump and the rest of the tools \
you want when you exec into a container to work out why a node is \
misbehaving.  Not intended for deployment."

require oeros-container-image.inc

inherit ros_distro_${ROS_DISTRO}
inherit ${ROS_DISTRO_TYPE}_image

OCI_BASE_IMAGE = "oeros-container-ros-base"

IMAGE_INSTALL = " \
    packagegroup-oeros-container-base \
    ${CONTAINER_SHELL} \
    ros-base \
    packagegroup-oeros-ros-tools \
    packagegroup-oeros-ros-build \
    packagegroup-oeros-ros-dev \
"

# dev-pkgs supplies the headers and cmake config files that
# packagegroup-oeros-ros-build's compiler needs to build a workspace against
# the installed ROS packages.
#
# dbg-pkgs is deliberately not enabled: it adds a -dbg package for everything
# installed, and with dev-pkgs the image had reached 16.6 GB.  Code built in
# this container carries whatever debug info its build type asks for
# (colcon build --cmake-args -DCMAKE_BUILD_TYPE=Debug).  The installed ROS
# packages themselves have no debug symbols here.
IMAGE_FEATURES += "dev-pkgs"

# The workspace to build in.  colcon scans the current directory, so without
# this it runs from / and walks the whole filesystem.
OCI_IMAGE_WORKINGDIR = "/workspace"

ROOTFS_POSTPROCESS_COMMAND += "oeros_container_dev_workspace ; "
oeros_container_dev_workspace () {
    install -m 0755 -d ${IMAGE_ROOTFS}/workspace
}
