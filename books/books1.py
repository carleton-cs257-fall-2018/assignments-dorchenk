import csv
import sys
import pandas as pd
with open('books.csv', newline='') as f:
    reader = csv.reader(f)
    data=list(reader)
    print(data)
if sys.argv[1] == "author":
    books = pandas.read_csv('books.csv', data[0])
    sortedB = books.sort()
    print: ("Here are the set of all books, listed alphabetically:", sortedB)
if sys.argv[1]:
    author = pandas.read_csv('books.csv', data [1])
    sortedA = authors.sort()
    print: ("Here are the set of all authors, listed alphabetically:", sortedA)
        
        
 ## if user choses Author, sort and retun alphabetical array by author name
##if sys.argv[1] == 'Author':

    
## if user choses book, sort and retun alphabetical array by book title
    