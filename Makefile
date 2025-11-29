JAVAC = javac
JAVA = java
CLASS_DIR = .
SOURCES = Main.java Vehiculo.java
CLASSES = Main.class Vehiculo.class
MAIN_CLASS = Main

# Definimos la URL y dónde guardar el jar
JUNIT_URL = https://repo1.maven.org/maven2/org/junit/platform/junit-platform-console-standalone/1.10.0/junit-platform-console-standalone-1.10.0.jar
LIB_DIR = lib
JUNIT_JAR = $(LIB_DIR)/junit-platform-console-standalone-1.10.0.jar

.PHONY: all compile run clean help test setup

all: compile run

compile: $(CLASSES)

$(CLASSES): $(SOURCES)
	$(JAVAC) $(SOURCES)

run: compile
	$(JAVA) $(MAIN_CLASS)

clean:
	rm -f $(CLASS_DIR)/*.class
	rm -rf $(LIB_DIR)
	@echo "Limpieza completada."

# Descarga JUnit si no lo tienes
setup:
	@mkdir -p $(LIB_DIR)
	@if [ ! -f "$(JUNIT_JAR)" ]; then \
		echo "⚠️ No se encontró JUnit. Descargando automáticamente..."; \
		curl -L -o "$(JUNIT_JAR)" "$(JUNIT_URL)"; \
		echo "✅ Descarga completada."; \
	fi

test: setup
	@echo "Compilando tests..."
	$(JAVAC) -d . -cp "$(JUNIT_JAR)" Vehiculo.java test/VehiculoTest.java
	@echo "Ejecutando JUnit..."
	$(JAVA) -jar "$(JUNIT_JAR)" execute --class-path . --scan-classpath