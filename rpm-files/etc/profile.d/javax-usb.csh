
if ( ${?CLASSPATH} ) then
  setenv CLASSPATH "/opt/javax-usb/lib/jsr80.jar:${CLASSPATH}"
else
  setenv CLASSPATH "/opt/javax-usb/lib/jsr80.jar"
fi
