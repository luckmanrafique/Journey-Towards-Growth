read -p "Enter number of terms: " a
sum=0
for i in $(seq 1 2 $a)
do
	power=1
	for j in $(seq 1 $i )
	do
		power=$((power*i))
	done
	sum=$((sum+power))
done
echo Sum=$sum
