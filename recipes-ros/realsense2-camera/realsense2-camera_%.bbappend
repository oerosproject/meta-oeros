# Copyright (c) 2026 Wind River Systems, Inc.

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"
SRC_URI += "file://cmake-add-ROS-2-Lyrical-to-supported-distros.patch;striplevel=2"

# src/actions.cpp calls ROS_ERROR(error_msg.c_str()) -- passing a runtime
# string as the format argument itself, rather than "%s", error_msg.c_str().
# Harmless here (error_msg is never attacker-controlled), but OE's default
# hardening flags promote -Wformat-security to a hard error. Downgrade just
# that one check back to a warning; leave -Werror active for everything else.
CXXFLAGS:append = " -Wno-error=format-security"
