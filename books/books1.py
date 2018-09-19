#Addison Partida, Javin White, Kellen Dorchen
#September 18, 2018
#Reads a csv file and sorts according to command line argument.
import csv
import sys

with open(sys.argv[1], 'r', encoding='utf-8') as csv_file:
    csv_reader = csv.reader(csv_file)


    if sys.argv[2] == "books":
        books = []
        for line in csv_reader:
            books.append(line[0])
        books.sort()
        for i in books:
            print(i)

    elif sys.argv[2] == "authors":
        authors = []
        for line in csv_reader:
            authors.append(line[2])
        authors.sort()
        for i in authors:
            print(i)

    else:
        print("Usage: python3 books1.py input-file action [sort direction]", file=sys.stderr)
