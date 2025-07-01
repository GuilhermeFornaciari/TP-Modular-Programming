all:
	javac -d bin Programa/Main.java

run: all
	java -cp bin Programa.Main
