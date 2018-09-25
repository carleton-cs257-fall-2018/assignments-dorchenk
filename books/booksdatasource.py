#!/usr/bin/env python3
''' Written by: Kellen Dorchen, Addison Partida, and Javin White
September 23, 2018
'''
'''
booksdatasource.py
Jeff Ondich, 18 September 2018

For use in some assignments at the beginning of Carleton's
CS 257 Software Design class, Fall 2018.
'''
import csv
import sys

class BooksDataSource:
	'''
	A BooksDataSource object provides access to data about books and authors.
	The particular form in which the books and authors are stored will
	depend on the context (i.e. on the particular assignment you're
	working on at the time).

	Most of this class's methods return Python lists, dictionaries, or
	strings representing books, authors, and related information.

	An author is represented as a dictionary with the keys
	'id', 'last_name', 'first_name', 'birth_year', and 'death_year'.
	For example, Jane Austen would be represented like this
	(assuming her database-internal ID number is 72):

	{'id': 72, 'last_name': 'Austen', 'first_name': 'Jane',
	'birth_year': 1775, 'death_year': 1817}

	For a living author, the death_year is represented in the author's
	Python dictionary as None.

	{'id': 77, 'last_name': 'Murakami', 'first_name': 'Haruki',
	'birth_year': 1949, 'death_year': None}

	Note that this is a simple-minded representation of a person in
	several ways. For example, how do you represent the birth year
	of Sophocles? What is the last name of Gabriel García Márquez?
	Should we refer to the author of "Tom Sawyer" as Samuel Clemens or
	Mark Twain? Are Voltaire and Molière first names or last names? etc.

	A book is represented as a dictionary with the keys 'id', 'title',
	and 'publication_year'. For example, "Pride and Prejudice"
	(assuming an ID of 132) would look like this:

	{'id': 193, 'title': 'A Wild Sheep Chase', 'publication_year': 1982}

	'''

	def __init__(self, books_filename, authors_filename, books_authors_link_filename):
		''' Initializes this data source from the three specified  CSV files, whose
		CSV fields are:

		books: ID,title,publication-year
		e.g. 6,Good Omens,1990
		41,Middlemarch,1871


		authors: ID,last-name,first-name,birth-year,death-year
		e.g. 5,Gaiman,Neil,1960,NULL
		6,Pratchett,Terry,1948,2015
		22,Eliot,George,1819,1880

		link between books and authors: book_id,author_id
		e.g. 41,22
		6,5
		6,6

		[that is, book 41 was written by author 22, while book 6
		was written by both author 5 and author 6]

		Note that NULL is used to represent a non-existent (or rather, future and
		unknown) year in the cases of living authors.

		NOTE TO STUDENTS: I have not specified how you will store the books/authors
		data in a BooksDataSource object. That will be up to you, in Phase 3.
		'''
		#pass

		books_file = books_filename
		authors_file = authors_filename
		books_authors_file = books_authors_link_filename
		
		#making books
		with open(books_file, 'r', encoding='utf-8') as books_file:
			books_file = csv.reader(books_file)
			self.books_list = []
			
			for book in books_file:
				book_dict = {'id': book[0], 'title': book[1], 'publication_year': book[2]}
				self.books_list.append(book_dict)
				
# 			for i in self.books_list:
# 				print(i)
				
		#making authors
		with open(authors_file, 'r', encoding='utf-8') as authors_file:
			authors_file = csv.reader(authors_file)
			self.authors_list = []
			
			for author in authors_file:
				author_dict = {'id': author[0], 'last_name': author[1], 'first_name': author[2], 'birth_year': author[3], 'death_year' : author[4]}
				self.authors_list.append(author_dict)
				
# 			for i in self.authors_list:
# 				print(i)
				
		#making link
		with open(books_authors_file, 'r', encoding='utf-8') as books_authors_file:
			books_authors_file = csv.reader(books_authors_file)

			self.link_dict = {}
			for pair in books_authors_file:
				if pair[0] in self.link_dict:
					self.link_dict[pair[0]].append(pair[1])
				else:
					self.link_dict[pair[0]] = [pair[1]]
#			print(self.link_dict)
# 			for link in self.link_dict:
# 				print(self.link_dict[link])
				
	def book(self, book_id):
		''' Returns the book with the specified ID. (See the BooksDataSource comment
		for a description of how a book is represented.) '''
		return_dict = {}
		book_id_to_search = book_id
		for dict in self.books_list:
			#print(dict)
			if dict['id'] == book_id_to_search:
				#print(book_id_to_search)
				return_dict = {book_id_to_search:dict['title']}
		return return_dict

	def books(self, *, author_id=None, search_text=None, start_year=None, end_year=None, sort_by='title'):
		''' Returns a list of all the books in this data source matching all of
		the specified non-None criteria.

		author_id - only returns books by the specified author
		search_text - only returns books whose titles contain (case-insensitively) the search text
		start_year - only returns books published during or after this year
		end_year - only returns books published during or before this year

		Note that parameters with value None do not affect the list of books returned.
		Thus, for example, calling books() with no parameters will return JSON for
		a list of all the books in the data source.

		The list of books is sorted in an order depending on the sort_by parameter:

		'year' -- sorts by publication_year, breaking ties with (case-insenstive) title
		default -- sorts by (case-insensitive) title, breaking ties with publication_year

		See the BooksDataSource comment for a description of how a book is represented.
		'''
		search_list = []
		link_list = []
		if author_id != None:
			for book_link in self.link_dict:
				if len(self.link_dict[book_link]) > 1:
					for i in self.link_dict[book_link]:
						if i == author_id:
							link_list.append(book_link)
				else:
					for i in self.link_dict[book_link]:
						if i == author_id:
							link_list.append(book_link)
			for book in self.books_list:
				for link in link_list:
					if book['id'] == link:
						search_list.append(book)
		if search_text != None:
			if len(search_list) == 0 and author_id == None:
				search_list = self.books_list
			search_list = [book for book in search_list if search_text in book['title'].lower()]
		if start_year != None:
			if len(search_list) == 0 and author_id == None and search_text == None:
				search_list = self.books_list
			search_list = [book for book in search_list if book['publication_year'] >= start_year]
		if end_year != None:
			if len(search_list) == 0 and author_id == None and search_text == None and start_year == None:
				search_list = self.books_list
			search_list = [book for book in search_list if book['publication_year'] <= end_year]
		if sort_by == 'title':
			if len(search_list) == 0 and author_id == None and search_text == None and start_year == None and end_year == None:
				search_list = self.books_list
			books_title_list = []
			for book in search_list:
				books_title_list.append(book['title'])
			books_title_list.sort()
		elif sort_by == 'year':
			if len(search_list) == 0 and author_id == None and search_text == None and start_year == None and end_year == None:
				search_list = self.books_list
			temp_list = []
			books_title_list = []
			for book in search_list:
				temp_str = book['publication_year'] + "," + book['title']
				temp_list.append(temp_str)
			temp_list.sort()
			for i in temp_list:
				temp = i.split(",")
				books_title_list.append(temp[1])
				
		return books_title_list

	def author(self, author_id):
		''' Returns the author with the specified ID. (See the BooksDataSource comment for a
		description of how an author is represented.) '''
		return_dict = {} 
		author_id_to_search = author_id
		for author in self.authors_list:
			if author['id'] == author_id_to_search:
				return_dict = {author_id_to_search:author['first_name'] + " " + author['last_name']}
		return return_dict

	def authors(self, *, book_id=None, search_text=None, start_year=None, end_year=None, sort_by='birth_year'):
		''' Returns a list of all the authors in this data source matching all of the
		specified non-None criteria.

		book_id - only returns authors of the specified book
		search_text - only returns authors whose first or last names contain
		(case-insensitively) the search text
		start_year - only returns authors who were alive during or after
		the specified year
		end_year - only returns authors who were alive during or before
		the specified year

		Note that parameters with value None do not affect the list of authors returned.
		Thus, for example, calling authors() with no parameters will return JSON for
		a list of all the authors in the data source.

		The list of authors is sorted in an order depending on the sort_by parameter:

		'birth_year' - sorts by birth_year, breaking ties with (case-insenstive) last_name,
		then (case-insensitive) first_name
		any other value - sorts by (case-insensitive) last_name, breaking ties with
		(case-insensitive) first_name, then birth_year

		See the BooksDataSource comment for a description of how an author is represented.
		'''
		search_list = []
		link_list = []
		temp_link_list = []
		if book_id != None:
			for book_link in self.link_dict:
#				print(self.link_dict)
				if book_link == book_id:
					#print(book_link)
					temp_link_list.append(self.link_dict[book_link])
			for link in temp_link_list:
				if len(link) > 1:
					#link.split(',')
					for i in link:
						#search_list = self.author(i)
						link_list.append(i)
				else:
					for i in link:
						#search_list = self.author(i)
						link_list.append(i)
			for author in self.authors_list:
				for link in link_list:
					#print(link_list)
					if author['id'] == link:
						search_list.append(author)
			#print(search_list)
		if search_text != None:
			if len(search_list) == 0 and book_id == None:
				search_list = self.authors_list
			search_list = [author for author in search_list if search_text in author['last_name'].lower() or search_text in author['first_name'].lower()]
		if start_year != None:
			if len(search_list) == 0 and book_id == None and search_text == None:
				search_list = self.authors_list
			search_list = [author for author in search_list if author['birth_year'] >= start_year]
		if end_year != None:
			if len(search_list) == 0 and book_id == None and search_text == None and start_year == None:
				search_list = self.authors_list
			search_list = [author for author in search_list if author['death_year'] <= end_year]
		if sort_by == 'birth_year':
			if len(search_list) == 0 and book_id == None and search_text == None and start_year == None and end_year == None:
				search_list = self.authors_list
			authors_final_list = []
			temp_list = []
			for author in search_list:
				temp_str = author['birth_year'] + "," + author['last_name'] + "," + author['first_name']
				temp_list.append(temp_str)
			temp_list.sort()
			for i in temp_list:
				temp = i.split(",")
				authors_final_list.append(temp[2] + " " + temp[1])
		elif sort_by != 'birth_year':
			if len(search_list) == 0 and book_id == None and search_text == None and start_year == None and end_year == None:
				search_list = self.authors_list
			temp_list = []
			authors_final_list = []
			for author in search_list:
				temp_str = author['last_name'] + "," + author['first_name'] + "," + author['birth_year']
				temp_list.append(temp_str)
			temp_list.sort()
			for i in temp_list:
				temp = i.split(",")
				author_final_list.append(temp[1] + " " + temp[0])

		return authors_final_list


	# Note for my students: The following two methods provide no new functionality beyond
	# what the books(...) and authors(...) methods already provide. But they do represent a
	# category of methods known as "convenience methods". That is, they provide very simple
	# interfaces for a couple very common operations.
	#
	# A question for you: do you think it's worth creating and then maintaining these
	# particular convenience methods? Is books_for_author(17) better than books(author_id=17)?

	def books_for_author(self, author_id):
		''' Returns a list of all the books written by the author with the specified author ID.
		See the BooksDataSource comment for a description of how an book is represented. '''
		return self.books(author_id=author_id)

	def authors_for_book(self, book_id):
		''' Returns a list of all the authors of the book with the specified book ID.
		See the BooksDataSource comment for a description of how an author is represented. '''
		return self.authors(book_id=book_id)

testing = BooksDataSource("books_new.csv", "authors.csv", "books_authors.csv")
#print(testing.book('4'))
#print(testing.author('5'))
#print(testing.books(author_id ='20', start_year='1815',sort_by='year'))
#print(testing.books(sort_by='year'))
print(testing.authors(book_id='31', search_text='h'))

