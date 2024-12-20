DESCRIPTION = "PNG glue for lcd4linux"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/GPL-2.0-only;md5=801f80980d171dd6425610833a22dbe6"

DEPENDS = "swig-native libpng python3"

SRC_URI = "\
	file://png_util.cpp \
	file://png_util.h \
	file://png_util.i \
"

S = "${WORKDIR}/${P}"

inherit setuptools3-base

do_compile() {
	swig -python -c++ ${WORKDIR}/png_util.i
	${CXX} -O2 -c -fPIC ${WORKDIR}/png_util.cpp ${WORKDIR}/png_util_wrap.cxx -I${STAGING_INCDIR}/${PYTHON_DIR}
	${CXX} -shared ${S}/png_util.o ${S}/png_util_wrap.o -o _png_util.so -L${STAGING_LIBDIR} -lpng -fPIC ${LDFLAGS}
	mv ${WORKDIR}/png_util.py ${S}
}

do_install() {
	install -d ${D}/${PYTHON_SITEPACKAGES_DIR}/pngutil
	touch ${D}/${PYTHON_SITEPACKAGES_DIR}/pngutil/__init__.py
	install -m 0755 ${S}/png_util.py ${D}/${PYTHON_SITEPACKAGES_DIR}/pngutil
	install -m 0755 ${S}/_png_util.so ${D}/${PYTHON_SITEPACKAGES_DIR}/pngutil
}

FILES:${PN} = "${PYTHON_SITEPACKAGES_DIR}/pngutil"
