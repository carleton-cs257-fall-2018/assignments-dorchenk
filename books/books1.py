import csv 

with open('books.csv', 'r', encoding='utf-8') as csv_file:
    csv_reader = csv.reader(csv_file)
    authors =[]
    for line in csv_reader:
        authors.append(line[0])
       # print(line[0])
   # print (authors)
    authors.sort()
    print (authors)