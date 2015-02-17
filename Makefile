.PHONY: answers handin answers/group.txt

answers:
	cd questions; ./questions.sh

handin: answers/group.txt
	@rutool handin -c arti15 -p lab3 answers build.xml dist src
	@rutool check -c arti15 -p lab3

answers/group.txt:
	@ cd questions; ./check_group_file.sh

