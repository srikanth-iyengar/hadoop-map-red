INPUT ?=
PRO ?=
ARGS ?=

run:
	@ gradle run

build:
	@ gradle $(INPUT):build --refresh-dependencies

push:
	@ hadoop fs -mkdir /input
	@ hadoop fs -put ./ev_charging.csv /input/ev_charging.csv

clean: 
	@ gradle clean
	@ hadoop fs -rm -r /out

cleanf:
	@ hadoop fs -rm -r /input

hadoop: build
	@ hadoop fs -rm -r /out || echo "No out directory exists"
	@ hadoop jar ./$(INPUT)/build/libs/$(INPUT).jar /input/ev_charging.csv /out $(ARGS)

new: 
	@ mkdir $(PRO)
	@ cp -r template/* $(PRO)
	@ echo "Please add your $(PRO) to the settings.gradle"

out:
	@ hadoop fs -cat /out/part-r-00000
