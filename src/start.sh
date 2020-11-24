#!/bin/sh
while true
do
/usr/lib/jvm/java-11/bin/java -Xms12G -Xmx12G -jar paper-288.jar
echo "If you want to completely stop the server process now, press Ctrl+C before
the time is up!"
echo "Rebooting in:"
for i in 3 2 1
do
echo "$i..."
sleep 1
done
echo "Rebooting now!"
done
