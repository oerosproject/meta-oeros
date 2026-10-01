# Copyright (c) 2026 Wind River Systems, Inc.
#
# Install an ament_python package's "lib/<pkg>" data files under
# ${ros_libdir} rather than ${ros_datadir}/lib.
#
# meta-ros's ros_ament_python.bbclass runs setup.py with
# --install-data=${ros_datadir}, so every data_files entry is installed
# relative to share/.  Packages that list "lib/<pkg>" there -- the way
# ament_python packages install an executable for "ros2 run" -- end up in
# share/lib/<pkg>, where "ros2 run <pkg> <exe>" never looks.  Colcon and the
# upstream binary packages install data_files relative to the prefix, so
# lib/<pkg>/<exe> is where they belong.
#
# Inherit this in a bbappend for each affected package.

do_install:append() {
    if [ -d ${D}${ros_datadir}/lib ]; then
        install -d ${D}${ros_libdir}
        for d in ${D}${ros_datadir}/lib/*; do
            [ -d "$d" ] || continue
            install -d ${D}${ros_libdir}/$(basename "$d")
            cp -a "$d"/. ${D}${ros_libdir}/$(basename "$d")/
            rm -rf "$d"
        done
        rmdir ${D}${ros_datadir}/lib
    fi
}
