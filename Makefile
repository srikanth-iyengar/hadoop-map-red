run:
	@ gradle run

build:
	@ gradle build --refresh-dependencies

push:
	@ hadoop fs -put ./survey_results_public.csv /input/survey_results_public.csv

hadoop: build
	@ hadoop jar ./app/build/libs/app.jar /input/survey_results_public.csv /out
