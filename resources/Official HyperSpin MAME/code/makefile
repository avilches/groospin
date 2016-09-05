CC      = g++
C_FLAGS = -g -Wall -std=c++11
L_FLAGS =


O_FILES = create_mame_xml.o

C_FILES = create_mame_xml.cpp

H_FILES =

all:	$(O_FILES)
	rm -f create_mame_xml
	$(CC) -o create_mame_xml $(O_FILES) `sdl-config --libs --cflags` $(L_FLAGS)
	chmod g+w create_mame_xml
	chmod g+w $(O_FILES)

.cpp.o: $(H_FILES)
	$(CC) -c $(C_FLAGS) $<

clean:
	rm -f *.o *~ create_mame_xml
