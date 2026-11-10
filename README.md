Oeros Distro
============

This is a reference distribution for OpenEmbedded (OE) with the Robot Operating
System (ROS).

Container Images
----------------

`recipes-containers/` builds OCI container images for ROS 2 Lyrical, layered so
that each REP-2001 variant is a separate container layer.  See
[docs/containers.md](docs/containers.md) for the image list, the multiconfigs
they build in, and how to load or push them.

Dependencies
------------

This layer depends on:

* openembedded-core (`core`), branch `wrynose`
* meta-openembedded: `openembedded-layer`, `meta-python`, branch `wrynose`
* meta-virtualization (`virtualization-layer`), branch `wrynose`
* meta-ros: `ros-common-layer`, `ros2-layer`, `ros2-lyrical-layer`, branch `wrynose`

The full set of layers used to build Oeros, with their branches, is in
[conf/registry/configurations/](conf/registry/configurations/).

Maintainer
----------

Rob Woolley <rob.woolley@windriver.com>

Contribution Guidelines
-----------------------

Please read the Oeros contributor guide:
<https://github.com/oerosproject/.github/blob/main/CONTRIBUTING.md>.
It follows the Yocto Project contributor guide
(<https://docs.yoctoproject.org/dev/contributor-guide/>) for commit messages
and patch format.

Where to Send Patches
---------------------

Send changes to this layer as pull requests against
<https://github.com/oerosproject/meta-oeros>.

Every patch file in the layer must carry an `Upstream-Status:` tag. Changes to
the ROS recipes themselves belong upstream in meta-ros
(<https://github.com/ros/meta-ros/>).

Security
--------

See [SECURITY.md](SECURITY.md).
