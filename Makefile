all:
	javac -Xlint -d bin Programa/Main.java

run: all
	java -cp bin Programa.Main
