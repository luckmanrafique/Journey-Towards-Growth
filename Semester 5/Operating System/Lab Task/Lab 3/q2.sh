echo Enter your marks:
read a 
if [ $a -gt 80 ] ; then
	echo Good, Keep it up!
elif [ $a -gt 60 ] ; then
	echo Try your best!
elif [ $a -ge 40 ] ; then 
	echo Need improvement!
else 
	echo Sorry, Try again!

fi
