#Addison Partida, Kellen Dorchen, and Javin White
# September 18, 2018
#
#Reads in information about books and sorts using command line arguments
#
import csv
import sys

if len(sys.argv) <= 3:
	print ("Usage: python3 books1.py input-file action [sort-direction]", file=sys.stderr)

else:
	with open(sys.argv[1], 'r', encoding='utf-8') as csv_file:
		csv_reader = csv.reader(csv_file)

		if sys.argv[2] == "books":
			books = []
			for line in csv_reader:
				books.append(line[0])

				if sys.argv[3] == "forward":
					books.sort()
					for i in books:
						print (i)

				elif sys.argv[3] == "reverse":
					books.sort(reverse = True)
					for i in books:
						print (i)


		elif sys.argv[2] == "authors":
			authors=[]
			for line in csv_reader:
				inputstr = line[2]
				words = inputstr.split()
				secondwords = iter(words)
				next(secondwords)
				if len(words) == 3:
					tempstr = words[1] + " " + words[0]
					authors.append(tempstr)

				elif len(words) > 4:
					tempstr = words[4] + " " + words[5]
					authors.append(tempstr)

				elif len(words) == 4:
					tempstr = words[2] + " " + words[0] + " " + words[1]
					authors.append(tempstr)

			if sys.argv[3] == "forward":
				authors.sort()
				forwardlist = []
				for i in authors:
					inputstr = i
					words = inputstr.split()
					if len(words) == 3:
						tempstr = words[1] + " " + words[2] + " " + words[0]
						forwardlist.append(tempstr)
					else:
						tempstr = words[1] + " " + words[0]
						forwardlist.append(tempstr)
				for j in forwardlist:
					print (j)

			elif sys.argv[3] == "reverse":
				authors.sort(reverse = True)
				reverselist = []
				for i in authors:
					inputstr = i
					words = inputstr.split()
					if len(words) == 3:
						tempstr = words[1] + " " + words[2] + " " + words[0]
						reverselist.append(tempstr)
					else:
						tempstr = words[1] + " " + words[0]
						reverselist.append(tempstr)
				for j in reverselist:
					print (j)

	# 	else:
	# 		print ("Usage: python3 books1.py input-file action [sort-direction]", file=sys.stderr)
