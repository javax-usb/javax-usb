
if test -n "$CLASSPATH" ; then
  CLASSPATH="/opt/javax-usb/lib/jsr80.jar:${CLASSPATH}"
else
  CLASSPATH="/opt/javax-usb/lib/jsr80.jar"
fi

export CLASSPATH
