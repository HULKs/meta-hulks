SUMMARY = "A small replacement for GNU readline() for UNIX"
LICENSE = "CLOSED"

SRC_URI = "git://github.com/troglobit/editline.git;branch=master;protocol=https"
SRCREV = "ecabef273ebf4193c5d6aff196de1c204169bc52"
S = "${WORKDIR}/git"

inherit autotools pkgconfig
