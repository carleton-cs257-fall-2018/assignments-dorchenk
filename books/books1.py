#Addison Partida, Javin White, Kellen Dorchen
#September 18, 2018
#Reads a csv file and sorts according to command line argument.
import csv
import sys

with open(sys.argv[1], 'rb') as csv_file:
    csv_reader = csv.reader(csv_file)
<<<<<<< HEAD
   
    
    if sys.argv[2] == "books":
=======


    if sys.argv[1] == "books":
>>>>>>> 77eb1de08da762169819c1c0a780a855547d8667
        books= []
        for line in csv_reader:
            books.append(line[0])
        books.sort()
        print (books)
<<<<<<< HEAD
    
    if sys.argv[2] == "authors":
=======

    if sys.argv[1] == "authors":
>>>>>>> 77eb1de08da762169819c1c0a780a855547d8667
        authors=[]
        for line in csv_reader:
            authors.append(line[2])
        authors.sort()
        print (authors)
