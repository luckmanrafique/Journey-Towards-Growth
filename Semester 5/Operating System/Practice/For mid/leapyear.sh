read -p "Enter a year: " y
if [ $((y%400)) -eq 0 ] ; then
	echo "LEAP YEAR"
elif [ $((y%4)) -eq 0 ] && [ $((y%100)) -ne 0 ] ; then 
	echo "LEAP YEAR"
else
	echo "NOT LEAP YEAR"
	fi
