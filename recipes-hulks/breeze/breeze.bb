SUMMARY = "breeze"
HOMEPAGE = "https://hulks.de"
LICENSE = "GPL-3.0-only"
LIC_FILES_CHKSUM = "file://${WORKDIR}/git/COPYING;md5=5b4473596678d62d9d83096273422c8c"

inherit cargo
SRC_URI += "git://github.com/giomf/hulk.git;branch=breeze;protocol=https"
SRCREV = "066de0bc2a69001e1cc5694062f51e66f44f38b6"
S = "${WORKDIR}/git/tools/breeze"

inherit pkgconfig

DEPENDS += " \
             systemd \
           "
RDEPENDS:${PN} += " \
                    systemd \
                  "

SYSTEMD_SERVICE:${PN} = "breeze.service"
SRC_URI += " \
             file://breeze.service \
           "

inherit systemd

do_install:append() {
  install -d ${D}${systemd_unitdir}/system/
  install -m 0644 ${WORKDIR}/breeze.service ${D}${systemd_unitdir}/system/
}


require breeze-crates.inc
