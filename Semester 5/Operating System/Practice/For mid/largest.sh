read -p "Enter three number: " a b c
if [ $a -gt $b ] && [ $a -gt $c ] ; then
	echo $a is largest
elif [ $b -gt $a ] && [ $b -gt $c ] ; then
	echo $b is largest
else echo $c is lagrgest
fi
