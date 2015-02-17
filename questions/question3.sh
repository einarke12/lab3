#!/bin/bash

q=$(basename "$0" .sh | sed -e 's/^question//')

echo "==========================================================================="

cat <<QUESTION
$q Implement your model and run the code.
Make sure your code is in the arti15/lab3/src/ folder on skel.
(Press Enter to continue.)
QUESTION
read answer

echo
echo "Trying to compile the code ..."
(cd ..; ant build 2>&1 | tee build.log)
(cd ..; ant clean &> /dev/null)
if grep -i -E "(error|failed)" ../build.log > /dev/null ; then
	echo "Error detected! Run 'ant build' to see the error and fix it before handing in."
else
	echo "Code seems to compile ok."
fi
echo "(Press Enter to continue.)"
read answer
