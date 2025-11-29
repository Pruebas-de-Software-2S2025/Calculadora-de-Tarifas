JAVAC = javac
JAVA = java
CLASS_DIR = .
SOURCES = Main.java Vehiculo.java
CLASSES = Main.class Vehiculo.class
MAIN_CLASS = Main


.PHONY: all compile run clean help

all: compile run

compile: $(CLASSES)

$(CLASSES): $(SOURCES)
	$(JAVAC) $(SOURCES)
run: compile
	$(JAVA) $(MAIN_CLASS)
clean:
	rm -f $(CLASS_DIR)/*.class
	@echo "Archivos compilados eliminados"

