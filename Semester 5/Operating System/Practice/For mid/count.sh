read -p "Enter a string: " str
count=0
for i in $(seq 1 ${#str})
do
	ch=${str:i:1}
	case $ch in
		a|e|i|o|u|A|E|I|O|U)
			count=$((count+1))
			;;
		*)
	esac
done
echo "Number of vowels: $count"
