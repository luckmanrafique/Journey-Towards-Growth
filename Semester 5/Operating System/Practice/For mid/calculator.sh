echo "===== Calculator ====="
echo "1. Addition "
echo "2. Subtraction " 
echo "3. Multiplication "
echo "4. Division " 
echo "====================" 
read -p "Enter your choice: " ch
read -p "Enter two numbers: " a b
echo "===== RESULT ====="
case $ch in 
	1) echo $((a+b)) ;;
	2) echo $((a-b)) ;;
	3) echo $((a*b)) ;;
	4) 
		if [ $b -ne 0 ] ; then
			echo $((a/b))
		else
			echo Can not devide by zero
		fi
		;;
	*) echo "Invalid Choice" ;; 
esac
