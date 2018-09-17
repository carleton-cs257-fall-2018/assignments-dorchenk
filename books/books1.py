import csv
import sys
with open('books.csv', newline='') as f:
    reader = csv.reader(f)
    #for row in reader:
        #print(row)
    for row in reader:
        print (sys.argv[1])
