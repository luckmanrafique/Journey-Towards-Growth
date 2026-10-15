a=(5 2 7 1 3)
echo Before sorting: ${a[@]}
n=${#a[@]}
	for i in $(seq 0 $((n-1)))
	do
		for j in $(seq 0 $((n-2)))
		do
			if [ ${a[j]} -gt ${a[j+1]} ] ; then
				temp=${a[j]}
				a[j]=${a[j+1]}
				a[j+1]=$temp
			fi
		done
	done
	echo After soritng: ${a[@]}
