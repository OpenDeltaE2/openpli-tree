DESCRIPTION = "A template for writing your own GStreamer plug-in"
MAINTAINER = "samsamsam"

DEPENDS = "gstreamer1.0 gstreamer1.0-plugins-base"

require conf/license/openpli-gplv2.inc
inherit gitpkgv autotools pkgconfig

PV = "1.0+git"
PKGV = "1.0+git${GITPKGV}"

SRC_URI = "git://gitlab.com/samsamsam/iptvplayer-bin-components.git;protocol=http;branch=master"

S = "${WORKDIR}/git/gst-ifdsrc/gst-ifdsrc"

FILES:${PN} += "${libdir}/gstreamer-1.0"
