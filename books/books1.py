#Addison Partida, Javin White, Kellen Dorchen
#September 18, 2018
#Reads a csv file and sorts according to command line argument.
import csv
import sys

with open('books.csv', 'r', encoding='utf-8') as csv_file:
    csv_reader = csv.reader(csv_file)


    if sys.argv[1] == "books":
        books= []
        for line in csv_reader:
            books.append(line[0])
        books.sort()
        print (books)

    if sys.argv[1] == "authors":
        authors=[]
        for line in csv_reader:
            authors.append(line[2])
        authors.sort()
        print (authors)
