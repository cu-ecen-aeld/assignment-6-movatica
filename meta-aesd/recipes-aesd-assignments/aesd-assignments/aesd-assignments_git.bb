# See https://git.yoctoproject.org/poky/tree/meta/files/common-licenses
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "git://git@github.com/cu-ecen-aeld/assignments-3-and-later-movatica;protocol=ssh;branch=master"

PV = "1.0+git${SRCPV}"
SRCREV = "7948dfd7e93fcc98b4ba53e3d100d506f7828c05"

# This sets your staging directory based on WORKDIR, where WORKDIR is defined at 
# https://docs.yoctoproject.org/ref-manual/variables.html?highlight=workdir#term-WORKDIR
# We reference the "server" directory here to build from the "server" directory
# in your assignments repo
S = "${WORKDIR}/git/server"

FILES:${PN} += "${bindir}/aesdsocket ${bindir}/aesdsocket-start-stop.sh"
TARGET_LDFLAGS += "-pthread -lrt"

do_configure () {
	:
}

do_compile () {
	oe_runmake
}

do_install () {
    install -d ${D}/${bindir}
    install -m 0755 ${S}/aesdsocket ${D}/${bindir}/
    install -m 0755 ${S}/aesdsocket-start-stop.sh ${D}/etc/init.d/S99aesdsocket
}
