import csv
import sys

with open(sys.argv[1], 'rb') as csv_file:
    csv_reader = csv.reader(csv_file)
   
    
    if sys.argv[2] == "books":
        books= []
        for line in csv_reader:
            books.append(line[0])
        books.sort()
        print (books)
    
    if sys.argv[2] == "authors":
        authors=[]
        for line in csv_reader:
            authors.append(line[2])
        authors.sort()
        print (authors)