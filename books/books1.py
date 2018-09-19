import csv
import sys


with open("books.csv", 'r', encoding='utf-8') as csv_file:
    csv_reader = csv.reader(csv_file)
    
    if sys.argv[1] == "books":
        books= []
        for line in csv_reader:
            books.append(line[0])
        
        if sys.argv[2] == "forward":
            books.sort()
            for i in books:
                print (i)
            
        if sys.argv[2] == "reverse":
            books.sort(reverse = True)
            for i in books:
                print (i)
                
            
    if sys.argv[1] == "authors":
        authors=[]
        for line in csv_reader:
            authors.append(line[2])
        
        if sys.argv[2] == "forward":
            #print (authors)

            for i in authors:
                print (i)
            
        if sys.argv[2] == "reverse":
            authors.sort(reverse = True)
            for i in authors:
                print (i)
        