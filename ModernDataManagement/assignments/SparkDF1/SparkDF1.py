# necessary import statements:
import pyspark
from pprint import pprint
from pyspark.sql import SparkSession
from pyspark.sql.functions import countDistinct

# 0) initialize the Spark session for your application in “local” mode
spark = SparkSession.builder.config("spark.jars", "postgresql-42.2.20.jar").getOrCreate()

# 1) print the version of Spark on which this application is running.
print('1) version of spark I am running', spark.version)

# 2) read csv file and convert into a dataframe, and display the schema:
print("#2 and #6. Dr. Leff enabled me to make the dataframe and print the schema in same step since I am doing homework in Python/PySpark.")
df = spark.read.csv("2015-summary.csv", header=True) # header=True takes the first row of data and puts it in the header.
df.printSchema()

# 3) print three rows of data:
print("#3")
df.show(3)

# 4) convert to a postgres Sql --> do this l8er
df.write.format("jdbc").option("url", "jdbc:postgresql://localhost:5432/mdmsparkdf") \
    .option("dbtable", "public.flight2015") \
    .option("user", "tombohbot") \
    .option("password", "") \
    .option("driver", "org.postgresql.Driver") \
    .mode("overwrite") \
    .save()

# 5) Number of records in the data-set:
n_records = df.count()
print('5) Number of records in the data-set:', n_records)

#6) create a temp view:
df.createOrReplaceTempView("flight2015")

# 7) Number of unique origin countries:
df7 = df.select(countDistinct('ORIGIN_COUNTRY_NAME') )
print('7) Number of unique origin countries:', df7.collect()[0][0])

# 8) How many rows are associated with the destination country that has the largest number of rows in the data-set?
df8 = df.groupBy('DEST_COUNTRY_NAME').count().sort('count', ascending=False)
print('8) country with most rows associated is', df8.collect()[0][0], 'and appears in', df8.collect()[0][1], 'rows.')

# 9) Which country has the most flights to itself, and what are the number of such flights?
df9 = df.filter(df['DEST_COUNTRY_NAME'] == df['ORIGIN_COUNTRY_NAME'])
print('9) country with most flights to itself is', df9.collect()[0][0], 'and the total number of flights is', df9.collect()[0][2], '.')

# 10) Top-five destination countries (by total number of flights). Report both the destination countries and the total number of flights.
df10 = df.groupBy('DEST_COUNTRY_NAME').agg({'count': 'sum'}).sort('sum(count)', ascending=False)
df10 = df10.withColumnRenamed('DEST_COUNTRY_NAME', 'top_five_destination_countrie').withColumnRenamed('sum(count)', 'total_number_of_flights')
print('10} Top-five destination countries with total number of flights:')
df10.show(5)