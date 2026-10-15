read -p "Enter a number: " n
if [ $n -le 1 ] ; then
	echo $n is not prime
	exit 0
fi
for i in $(seq 2 $((n-1)))
do
	if [ $((n%i)) -eq 0 ] ; then
		echo $n is not prime
		exit 0
	fi
done
echo $a is prime
