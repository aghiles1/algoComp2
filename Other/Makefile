JAVAC=javac
sources = $(wildcard *.java)
classes = $(sources:.java=.class)

all: myProgram jar

myProgram: $(classes)

%.class: %.java
	$(JAVAC) $<

jar:
	@echo "Manifest-Version: 1.0" > manifest.txt
	@echo "Class-Path: ." >> manifest.txt
	@echo "Main-Class: Algo" >> manifest.txt
	@echo "" >> manifest.txt
	@echo "#!/bin/sh\nMYSELF=\`which \"\$$0\" 2\>/dev/null\`\n[ \$$? -gt 0 -a -f \"\$$0\" ] && MYSELF=\"./\$$0\"\njava=java\nif test -n \"\$$JAVA_HOME\"; then\njava=\"\$$JAVA_HOME/bin/java\"\nfi\nexec \"\$$java\" \$$java_args -jar \$$MYSELF \"\$$@\"\nexit 1" > stub.sh
	jar -cmf manifest.txt JARNAME.jar $(classes)
	rm -f manifest.txt
	bash -c "cat stub.sh JARNAME.jar > ../algo.ex && chmod +x ../algo.ex"
	@echo "Manifest-Version: 1.0" > manifest.txt
	@echo "Class-Path: ." >> manifest.txt
	@echo "Main-Class: Stat" >> manifest.txt
	@echo "" >> manifest.txt
	jar -cmf manifest.txt JARNAME.jar $(classes)
	rm -f manifest.txt
	bash -c "cat stub.sh JARNAME.jar > ../stat.ex && chmod +x ../stat.ex"
	rm -f stub.sh
	rm -f JARNAME.jar
	rm -f *.class
clean:
	rm -f *.class
	rm -f stub.sh
	rm -f manifest.txt
	rm -f JARNAME.jar
