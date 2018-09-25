'''
   booksdatasourcetest.py
   Addison Partida
   Javin White
   Kellen Dorchen
   September 21 2018
'''

import unittest
import booksdatasource

class BooksDataSourceTester(unittest.TestCase):
    def setUp(self):
        self.data_source = booksdatasource.BooksDataSource("books_test.csv", "author_test.csv.", "books_authors_test.csv")

    def tearDown(self):
        pass
#------------------------------------------------------
    def test_books_author_id(self):
        self.assertEqual(self.data_source.books(author_id=13), [])

    def test_neg_books_id(self):
        self.assertRaises(ValueError, self.data_source.authors(book_id='-1'))

    def test_books_author_id_returnValue(self):
        self.assertEqual(self.data_source.books(author_id='13'), ["Moby Dick", "Omoo"])

    def test_books_search_text(self):
        self.assertEqual(self.data_source.books(search_text='mo'), ['Moby Dick', 'Omoo'])

    def test_books_start_year(self):
        self.assertEqual(self.data_source.books(start_year='1922'), ['Good Omens', 'Neverwhere'])

    def test_books_end_year(self):
        self.assertEqual(self.data_source.books(end_year='1922'), ['Main Street', 'Moby Dick', 'Omoo'])

    def test_books_test_sort_by_year(self):
        self.assertEqual(self.data_source.books(author_id='13', sort_by='year'), ['Omoo', 'Moby Dick'])
#------------------------------------------------------
    def test_neg_author_id(self):
        self.assertRaises(ValueError, self.data_source.books(author_id='-1'))

    def test_author_id(self):
        self.assertEqual(self.data_source.author(author_id=10), {})

    def test_author_id_returnValue(self):
        self.assertEqual(self.data_source.author('10'), {'10': 'Sinclair Lewis'})
#------------------------------------------------------
    def test_author(self):
        self.assertEqual(self.data_source.authors(book_id=13), [])
#------------------------------------------------------
    def test_authors_for_book(self):
        self.assertEqual(self.data_source.authors_for_book(book_id=10), [])

    def test_books_for_author(self):
        self.assertEqual(self.data_source.books_for_author(author_id=10), [])

    def test_authors_for_book_returnValue(self):
        self.assertEqual(self.data_source.authors_for_book('10'), ["Sinclair Lewis"])

    def test_books_for_author_returnValue(self):
        self.assertEqual(self.data_source.books_for_author('10'), ["Main Street"])
#------------------------------------------------------

if __name__ == '__main__':
    unittest.main()
