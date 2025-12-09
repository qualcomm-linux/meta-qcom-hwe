#Switch to use main branch instead of master
SRC_URI:remove = "git://github.com/gflags/gflags.git;branch=master;protocol=https"
SRC_URI:append = " git://github.com/gflags/gflags.git;branch=main;protocol=https"
