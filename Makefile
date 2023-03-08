INPUT ?=

run:
	@ gradle run

build:
	@ gradle build --refresh-dependencies

push:
	@ hadoop fs -mkdir /input
	@ hadoop fs -put ./ev_charging.csv /input/ev_charging.csv

clean: 
	@ hadoop fs -rm -r /out

cleanf:
	@ hadoop fs -rm -r /input

hadoop: build
	@ hadoop jar ./$(INPUT)/build/libs/$(INPUT).jar /input/ev_charging.csv /out
