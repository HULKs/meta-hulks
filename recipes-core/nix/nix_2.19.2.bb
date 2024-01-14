SUMMARY = "Nix package manager"
LICENSE = "LGPL-2.1"
LIC_FILES_CHKSUM = "file://${WORKDIR}/git/COPYING;md5=fbc093901857fcd118f065f900982c24"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI += "git://github.com/NixOS/nix.git;branch=latest-release;protocol=https \
                file://configure.ac.patch \
                file://markdown.cc.patch \
        "

SRCREV = "50f8f1c8bc019a4c0fd098b9ac674b94cfc6af0d"
S = "${WORKDIR}/git"

inherit autotools-brokensep pkgconfig systemd

DEPENDS += " \
        bison-native \
        clang-native \
        autoconf-archive \
        jq-native \
        boost \
        openssl \
        libarchive \
        curl \
        libsodium \
        brotli \
        libeditline \
        libseccomp \
        bdwgc \
        nlohmann-json \
        libgit2 \
        "
RDEPENDS:${PN} += " \
        boost \
        openssl \
        libarchive \
        curl \
        libsodium \
        brotli \
        libeditline \
        libseccomp \
        bdwgc \
        nlohmann-json \
        libgit2 \
        "

PACKAGES = "${PN} ${PN}-dev ${PN}-dbg"
FILES:${PN} += "${libdir}/tmpfiles.d/*"
FILES:${PN}-dev += "${libdir}/tmpfiles.d/* ${libdir}/*"

INSANE_SKIP:${PN} += "dev-so"
SOLIBS = ".so"


do_install:append(){
  # Remove unneeded things that we don't want on the nao.
  rm -r "${D}/usr/share/"

  # Move service and socket to destination where systemd.bbclass can work with it.
  install -d "${D}${systemd_unitdir}/system/"
  mv "${D}/usr/lib/systemd/system/nix-daemon.service" "${D}/usr/lib/systemd/system/nix-daemon.socket" "${D}${systemd_unitdir}/system/"        
  rm -r "${D}/usr/lib/systemd"
}


SYSTEMD_SERVICE:${PN} = "nix-daemon.service nix-daemon.socket"

EXTRA_OECONF += "--disable-cpuid --disable-tests --disable-doc-gen"
PARALLEL_MAKE = "-j 16"
