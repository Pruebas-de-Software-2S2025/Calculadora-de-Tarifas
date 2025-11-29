JAVAC = javac
JAVA = java
SRC_JAVA = src/java
SRC_TEST = src/test
CLASS_DIR = .

# Configuración de JUnit
JUNIT_URL = https://repo1.maven.org/maven2/org/junit/platform/junit-platform-console-standalone/1.10.0/junit-platform-console-standalone-1.10.0.jar
LIB_DIR = lib
JUNIT_JAR = $(LIB_DIR)/junit-platform-console-standalone-1.10.0.jar

.PHONY: all compile run clean test setup help

all: compile run

compile:
	$(JAVAC) $(SRC_JAVA)/Main.java $(SRC_JAVA)/Vehiculo.java

run: compile
	$(JAVA) -cp $(SRC_JAVA) Main

clean:
	rm -f $(SRC_JAVA)/*.class
	rm -f $(SRC_TEST)/*.class


setup:
	@mkdir -p $(LIB_DIR)
	@if [ ! -f "$(JUNIT_JAR)" ]; then \
		echo "No se encontró JUnit. Descargando automáticamente..."; \
		curl -L -o "$(JUNIT_JAR)" "$(JUNIT_URL)"; \
		echo "Descarga completada."; \
	fi

test: setup
	@echo "Compilando y Ejecutando Tests..."
	$(JAVAC) $(SRC_JAVA)/Vehiculo.java
	$(JAVAC) -cp "$(JUNIT_JAR):$(SRC_JAVA)" $(SRC_TEST)/VehiculoTest.java
	$(JAVA) -jar "$(JUNIT_JAR)" execute --class-path "$(SRC_JAVA):$(SRC_TEST)" --scan-classpath

help:
	@echo "Comandos:"
	@echo "  make run    - Ejecuta el programa"
	@echo "  make test   - Ejecuta las pruebas"
	@echo "  make clean  - Limpia todo"