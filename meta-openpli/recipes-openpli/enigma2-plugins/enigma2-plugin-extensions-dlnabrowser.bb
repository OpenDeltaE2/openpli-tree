DESCRIPTION = "VU+ DLNA Browser plugin"

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://LICENSE;md5=c9e255efa454e0155c1fd758df7dcaf3"
BRANCH = "vuplus_experimental"
S = "${WORKDIR}/git"
SRC_URI = "git://github.com/vuplus-com/dvbapp.git;protocol=https;branch=${BRANCH} \
	file://enigma2-plugin-systemplugins-dlnabrowser_20130723.patch;striplevel=1;apply=yes \
	file://port-to-python3.patch \
"

inherit gitpkgv python3native
PV = "git"
PKGV = "git${GITPKGV}"

DEPENDS = "djmount fuse libupnp"
PROVIDES = "enigma2-plugin-systemplugins-dlnabrowser"
RDEPENDS:${PN} = "djmount fuse-utils fuse libupnp"
RRECOMMENDS:${PN} = "kernel-module-fuse"
FILES:${PN} = "${libdir}/enigma2/python/Plugins/Extensions/DLNABrowser/*"
PACKAGES = "${PN}"

do_install() {
	install -d ${D}${libdir}/enigma2/python/Plugins/Extensions/DLNABrowser
	install -m 0644 ${S}/lib/python/Plugins/Extensions/DLNABrowser/*.py ${D}${libdir}/enigma2/python/Plugins/Extensions/DLNABrowser
	python3 -O -m compileall ${D}${libdir}/enigma2/python/Plugins/
}
