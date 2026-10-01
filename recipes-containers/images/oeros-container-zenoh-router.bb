# Copyright (c) 2026 Wind River Systems, Inc.

SUMMARY = "OEROS container image running the zenoh router"
DESCRIPTION = "A standalone zenohd.  rmw_zenoh needs a router to bridge \
sessions that cannot discover each other directly, which is exactly the case \
once ROS 2 nodes are split across containers or hosts.  Layered on the OS \
base rather than on ros-core: zenohd is not a ROS node and needs none of it."

require oeros-container-image.inc

OCI_BASE_IMAGE = "oeros-container-base"

IMAGE_INSTALL = " \
    packagegroup-oeros-container-base \
    ${CONTAINER_SHELL} \
    zenoh \
"

# zenohd's default listener.  Change with -e or a mounted config rather than
# rebuilding.
OCI_IMAGE_PORTS = "7447/tcp 7447/udp 8000/tcp"

# Run the router directly: there is no ROS environment to source here, so the
# shared entrypoint would only add a failed test.
OCI_IMAGE_ENTRYPOINT = "/usr/bin/zenohd"

# This should be empty, so that the container runs plain "zenohd".  An empty
# OCI_IMAGE_CMD does not clear the command, though: image-oci only issues
# "umoci config --config.cmd" for a non-empty value, so the image inherits
# oeros-container-base's /bin/sh and runs "zenohd /bin/sh", which exits with
# "unexpected argument '/bin/sh'".
#
# Until image-oci can clear it, give the image an explicit command that does
# what zenohd does with no arguments.  Running zenohd bare and with this
# option gives the same result: it listens on TCP port 7447 on [::], is
# reachable at the same addresses, and scouts on 224.0.0.224:7446.  Arguments
# given to "docker run <image>" replace this one, and zenohd then falls back
# to that same default.
OCI_IMAGE_CMD = "--listen tcp/[::]:7447"
