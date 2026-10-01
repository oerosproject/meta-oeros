# Copyright (c) 2026 Wind River Systems, Inc.
#
# Same problem as rqt-gui: the helper processes launch_testing's own tests
# run (good_proc.py, terminating_proc.py, exit_code_proc.py) land in
# share/lib/launch_testing instead of lib/launch_testing.
inherit oeros-ament-python-lib
