# Hadoop

You can do pretty much everything using the Makefile 😎

## Creating new gradle module
```bash
make new PRO=<MODULE_NAME>
```

## Cleaning the output directory
```bash
make clean
```

## ‼️‼️‼️‼️‼️‼️ Use this below command with caution‼️‼️‼️‼️‼️ to clean the input directory
```bash
make cleanf
```

## Push the dataset into the hadoop file system
- Remember to change the location of dataset in the Makefile
```bash
make push
```

## To build the jar file and submit the jar file to hadoop
```bash
make hadoop INPUT=<MODULE_NAME> ARGS=<COMMAND_LINE_ARGUMENTS>


## Makefile template
```bash
INPUT ?=
PRO ?=
ARGS ?=

run:
	@ gradle run

build:
	@ gradle $(INPUT):build --refresh-dependencies

push:
	@ hadoop fs -mkdir /input
	@ hadoop fs -put ./<dataset>.csv /input/<dataset>.csv

clean: 
	@ gradle clean
	@ hadoop fs -rm -r /out

cleanf:
	@ hadoop fs -rm -r /input

hadoop: build
	@ hadoop fs -rm -r /out || echo "No out directory exists"
	@ hadoop jar ./$(INPUT)/build/libs/$(INPUT).jar /input/<dataset>.csv /out $(ARGS)

new: 
	@ mkdir $(PRO)
	@ cp -r template/* $(PRO)
	@ echo "Please add your $(PRO) to the settings.gradle"

out:
	@ hadoop fs -cat /out/part-r-00000
```
