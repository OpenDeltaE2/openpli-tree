SUMMARY = "Betacentauris couch flashing"
MAINTAINER = "Betacentauri"
HOMEPAGE = "https://github.com/oe-alliance/ofgwrite"
LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://COPYING;md5=0636e73ff0215e8d672dc4c32c317bb3 \
                    file://include/common.h;beginline=1;endline=17;md5=ba05b07912a44ea2bf81ce409380049c"

inherit gitpkgv

PV = "4.x+git"
PKGV = "4.x+git${GITPKGV}"

DEPENDS = "openssl"

SRC_URI = " \
    git://github.com/oe-alliance/ofgwrite.git;protocol=https;branch=master \
    file://0001-makefile-openssl.patch \
    "

S = "${WORKDIR}/git"

do_install() {
    install -d ${D}${bindir}
    install -m 755 ${S}/ofgwrite ${D}${bindir}
    install -m 755 ${S}/ofgwrite_bin ${D}${bindir}
    install -m 755 ${S}/ofgwrite_test ${D}${bindir}
}
