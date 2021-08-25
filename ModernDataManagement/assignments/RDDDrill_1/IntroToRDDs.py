# necessary import statements:
import pyspark
from pprint import pprint

# connect to pyspark:
sc = pyspark.SparkContext('local[*]')

# read text file:
txt = sc.textFile('tx_dataset.txt')

# setting these print statements so that there is a distinction between my output and the warning messages thrown:
# print()
# print()
# print()
# print()
# print()

# 1) show that I have ingested input file:
ingested_input_file = txt.collect()
print("1) show that I have ingested input file")
# pprint(ingested_input_file)

# 2) how many lines are in the input file:
line = txt.map(lambda s: 1)
numb_lines = line.reduce(lambda a, b: a + b)
print("2) number of lines:", numb_lines)

# 3) How many unique customers:
customers = txt.map(lambda s: ( s.split("#")[2] , 1) )
unique_customers = customers.reduceByKey(lambda a, b: 1)
print("3) number of unique customers:", unique_customers.count())

# 4) Which customer made the most purchases:
customers_purchase_per_transaction = txt.map(lambda s: ( s.split("#")[2] , 1) )
customers_total_transactions = customers_purchase_per_transaction.reduceByKey(lambda a, b: a+b)
sorted_customers_transactions = customers_total_transactions.sortBy(lambda a: a[1]*-1) # multiply by -1 to reverse sort, since the smallest int possible is 0. Clean data is great :)
print("4) ID of customer with most purchases is:", sorted_customers_transactions.first()[0] )

# 5) How many purchases did the previously found customer make:
print("5) The previous customer made:", sorted_customers_transactions.first()[1], "purchases" )

# 6) Print that set of purchases using the format below:
person_w_most_purchases = int(sorted_customers_transactions.first()[0])
customerToSet = txt.map(lambda s: ( int(s.split("#")[2]) , s.split("#")[0] + ", " + s.split("#")[1] + ", " + s.split("#")[2] + ", " + s.split("#")[3] + ", " + s.split("#")[4] + ", " + s.split("#")[5] + "\n" ) )
grouped_customerToSet = customerToSet.reduceByKey(lambda a, b: a+b)
set_of_person_with_most_purchases = grouped_customerToSet.sortBy(lambda a: abs(a[0]-person_w_most_purchases) ) # subtract by person so that person's key is zero, then take abs value since every value is discrete, so they will all be greater than zero :)/
print("6) set of purchases that customer", person_w_most_purchases, "made:")
print(set_of_person_with_most_purchases.first()[1] ) # muting for now b/c long output, but it is correct.


# # 7) apply a 5% discount to all purchases of item #25, whenever a given purchase involves more than one of these items:
transactions = txt.map(lambda s: ( s.split("#")[2], s.split("#") ) )
discounted_transactions = transactions.map(lambda s: (s[0], [ s[1][0], s[1][1], s[1][2], s[1][3], s[1][4], float(s[1][5])*0.95 ]) if (s[1][3] == '25' and int(s[1][4]) > 1) else (s[0], s) )

# 8) Every customer who bought five or more items with id #81 are given a free item #70. 
#    Each “complimentary tx” corresponds to a new transaction, and the set of these 
#    transactions should be added to the existing set of transactions.
free_items = discounted_transactions.map(lambda s: (int(s[1][1][2]), s) if (s[1][1][3] == '81' and int(s[1][1][4]) >= 5) else (-1, s) )
parsed_free_items = free_items.filter(lambda s: s[0] >= 0)
free_transactions = parsed_free_items.map(lambda s: (s[0], [s[1][0], s[1][1], '70', '1', '0'] ) )
appended_data = discounted_transactions.union(free_transactions)

# 9) How many transactions are now in the data-set?
amount_of_transactions = appended_data.count()
print("9) amount of transactions currently in dataset:", amount_of_transactions)

# 10) Which customer spent the most money, and how much?
customers_purchase_per_transaction = transactions.map(lambda s: (s[0], float(s[1][5]) ) )
customers_total_purchase = customers_purchase_per_transaction.reduceByKey(lambda a, b: a+b)
sorted_customers_total_purchase = customers_total_purchase.sortBy(lambda a: a[1]*-1) # multiply by -1 to reverse sort, since the smallest double possible is 0. Clean data is great :)
print("10a) ID of customer who spent most money:", sorted_customers_total_purchase.first()[0] )
# How much money did they spend:
print("10b) Customer", sorted_customers_total_purchase.first()[0], "spent", sorted_customers_total_purchase.first()[1], "dollars." )


# Old version of 4-6:
# # 4) Which customer made the most purchases (purchased most items):
# customers_purchase_per_transaction = txt.map(lambda s: ( s.split("#")[2] , int(s.split("#")[4]) ) )
# customers_total_purchase = customers_purchase_per_transaction.reduceByKey(lambda a, b: a+b)
# sorted_customers_total_purchase = customers_total_purchase.sortBy(lambda a: a[1]*-1) # multiply by -1 to reverse sort, since the smallest int possible is 0. Clean data is great :)
# print("4) ID of customer with most purchases is:", sorted_customers_total_purchase.first()[0] )
# # 5) How many purchases did the previously found customer make:
# print("5) The previous customer made:", sorted_customers_total_purchase.first()[1], "purchases" )
# # 6) Print that set of purchases using the format below:
# person_w_most_purchases = int( sorted_customers_total_purchase.first()[0])
# customerToSet = txt.map(lambda s: ( int(s.split("#")[2]) , s.split("#")[0] + ", " + s.split("#")[1] + ", " + s.split("#")[2] + ", " + s.split("#")[3] + ", " + s.split("#")[4] + ", " + s.split("#")[5] + "\n" ) )
# grouped_customerToSet = customerToSet.reduceByKey(lambda a, b: a+b)
# set_of_person_with_most_purchases = grouped_customerToSet.sortBy(lambda a: abs(a[0]-person_w_most_purchases) ) # subtract by person so that person's key is zero, then take abs value since every value is discrete, so they will all be greater than zero :)/
# print("6) set of purchases that:", person_w_most_purchases, "made:")
# # print(set_of_person_with_most_purchases.first()[1] ) # muting for now b/c long output, but it is correct.




