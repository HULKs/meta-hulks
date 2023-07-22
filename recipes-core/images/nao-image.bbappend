FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SDK_ZSTD_COMPRESSION_LEVEL = "-9"

CORE_IMAGE_EXTRA_INSTALL += " \
                              aliveness \
                              alsa-lib \
                              alsa-state \
                              compilednn \
                              hula \
                              hulk \
                              iproute2 \
                              jq \
                              libxml2-utils \
                              libogg \
                              libopus \
                              nano \
                              network-config \
                              opusfile \
                            "
