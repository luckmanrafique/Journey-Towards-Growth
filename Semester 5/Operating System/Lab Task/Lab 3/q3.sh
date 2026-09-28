echo Enter first number:
read a 
echo Enter second number:
read b
if [ $a -gt $b ] ; then
	echo Summation: $((a+b))
else echo Subtraction: $((b-a))
fi
