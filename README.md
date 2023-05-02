# Hadoop

### **You can do pretty much anything using the Makefile 😎**

## Creating new gradle module
```bash
make new PRO=<MODULE_NAME>
```

## Cleaning the output directory
```bash
make clean
```

## ‼️ Use this below command with caution‼️ to clean the input directory
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
```

## 💻 If you really want to see the output of what you have done just
``` bash 
make out 
```

## Makefile template
```bash
INPUT ?=
PRO ?=
ARGS ?=
HADOOP_HOME = /opt/hadoop
HIVE_HOME= /opt/hive
HBASE_HOME= /opt/hase
PIG_HOME= /opt/pig

run:
	@ gradle run

build:
	@ gradle $(INPUT):build --refresh-dependencies

push:
	@ sudo cp ./ev_charging.csv /home/srikanth/college/LAB_ESE/compose/data/ev_charging.csv
	@ docker exec hadoop $(HADOOP_HOME)/bin/hadoop fs -mkdir /input | echo "already exists"
	@ docker exec hadoop $(HADOOP_HOME)/bin/hadoop fs -put /programs/ev_charging.csv /ev_charging.csv

clean: 
	@ gradle clean
	@ docker exec hadoop $(HADOOP_HOME)/bin/hadoop fs -rm -r /out

cleanf:
	@ docker exec hadoop $(HADOOP_HOME)/bin/hadoop fs -rm -r /input

hadoop: build
	@ docker exec hadoop $(HADOOP_HOME)/bin/hadoop fs -rm -r /out || echo "No out directory exists"
	@ sudo mv ./$(INPUT)/build/libs/$(INPUT).jar /home/srikanth/college/LAB_ESE/compose/data/jar/
	@ docker exec hadoop $(HADOOP_HOME)/bin/hadoop jar /programs/jar/$(INPUT).jar /ev_charging.csv /out $(ARGS)

new: 
	@ mkdir $(PRO)
	@ cp -r template/* $(PRO)
	@ echo "Please add your $(PRO) to the settings.gradle"

out:
	@ docker exec hadoop $(HADOOP_HOME)/bin/hadoop fs -cat /out/part-r-00000

hive:
	@ docker exec -i -t hadoop $(HIVE_HOME)/bin/hive
```
