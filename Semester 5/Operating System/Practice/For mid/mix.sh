read -p "Enter a number: " a
sum=0
for i in $(seq 1 $a) 
do
	sum=$((sum+$i))
done
echo "Sum from 1 to $n : $sum"
if [ $((sum%2)) -eq 0 ] ; then
	echo "SUM IS EVEN"
else echo "SUM ID ODD"
fi
echo "SUM: $sum" > result.txt
cat result.txt
echo "=== RESULT SAVED IN result.txt ==="
