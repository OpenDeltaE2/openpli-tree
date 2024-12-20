DESCRIPTION = "Handle your EPG on enigma2 using opentv and xmltv"
HOMEPAGE = "https://github.com/LraiZer/RadiotimesXmltvEmulator"
LICENSE = "LGPL-2.1-only"
LIC_FILES_CHKSUM = "file://LICENSE;md5=fc178bcd425090939a8b634d1d6a9594"

inherit gitpkgv python3native

PV = "2+git"
PKGV = "2+${GITPKGV}"
PR = "r0"

ALLOW_EMPTY:${PN} = "1"

INSANE_SKIP:${PN} += "already-stripped ldflags"

SRC_URI = "git://github.com/LraiZer/RadiotimesXmltvEmulator.git;branch=gui-plugin;protocol=https"

S = "${WORKDIR}/git"

do_compile() {
    echo ${PV} > ${S}/VERSION
    oe_runmake SWIG="swig"
}

do_install() {
    oe_runmake 'D=${D}' install-plugin
}

pkg_postrm:${PN}() {
rm -fr ${libdir}/enigma2/python/Plugins/SystemPlugins/RadiotimesXmltvEmulator > /dev/null 2>&1
}

# Just a quick hack to "compile" the python parts.
do_compile:append() {
    python3 -O -m compileall ${S}
}

python populate_packages:prepend() {
    enigma2_plugindir = bb.data.expand('${libdir}/enigma2/python/Plugins', d)
    do_split_packages(d, enigma2_plugindir, '^(\w+/\w+)/[a-zA-Z0-9_]+.*$', 'enigma2-plugin-%s', '%s', recursive=True, match_path=True, prepend=True, extra_depends='')
    do_split_packages(d, enigma2_plugindir, '^(\w+/\w+)/.*\.py$', 'enigma2-plugin-%s-src', '%s (source files)', recursive=True, match_path=True, prepend=True)
    do_split_packages(d, enigma2_plugindir, '^(\w+/\w+)/.*\.la$', 'enigma2-plugin-%s-dev', '%s (development)', recursive=True, match_path=True, prepend=True)
    do_split_packages(d, enigma2_plugindir, '^(\w+/\w+)/.*\.a$', 'enigma2-plugin-%s-staticdev', '%s (static development)', recursive=True, match_path=True, prepend=True)
    do_split_packages(d, enigma2_plugindir, '^(\w+/\w+)/(.*/)?\.debug/.*$', 'enigma2-plugin-%s-dbg', '%s (debug)', recursive=True, match_path=True, prepend=True)
    do_split_packages(d, enigma2_plugindir, '^(\w+/\w+)/.*\/.*\.po$', 'enigma2-plugin-%s-po', '%s (translations)', recursive=True, match_path=True, prepend=True)
}

FILES:${PN}:append = " /usr"
