FILESEXTRAPATHS:prepend := "${THISDIR}/gcc:"
SRC_URI:append = " \
	file://kernel-add-support-for-gcc13.patch \
	"
