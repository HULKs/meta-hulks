do_install:append(){
  rm ${D}${systemd_unitdir}/system/alfand.service
}


SYSTEMD_SERVICE:${PN}:remove = "alfand.service"