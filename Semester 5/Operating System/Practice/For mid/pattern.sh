read -p "Enter number of rows: " n
for ((i=1; i<=n; i++))
do
	for ((space=1; space <=n; space++))
	do
		echo -n ""
	done
	for ((j=1; j<=2 * i - 1; j++))
	do
		echo -n "*"
	done
	echo
done
