DESCRIPTION = "plugin to connect to internet via any modems"
LICENSE = "PD"
LIC_FILES_CHKSUM = "file://README;md5=00f286ed22b8ad579d0715884c7639a9"

PLUGINNAME = "enigma2-plugin-extensions-xmodem"
PLUGIN_PATH = "Extensions/xModem"

require dima-plugins.inc

RDEPENDS:${PN} = " \
	usb-modeswitch \
	usb-modeswitch-data \
	picocom \
	ppp \
	"
