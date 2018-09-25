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

    def test_books_return(self):
        self.assertEqual(self.data_source.book(book_id=16), {'title': "Omoo", 'id': '16'})

    def test_neg_ID(self):
        self.assertRaises(ValueError, self.data_source.books, author_id=-1)

    def test_author_ID(self):
        self.raisesEqual(ValueError, self.data_source.author(self, author_id=13))

    def test_authors_for_book(self):
        self.assertTrue(self.data_source.authors_for_book(self, book_id=6), [])

#    def test_authorsForBook(self):
#        self.assertTrue()

#    def test_authorsForYear(self):
#        self.assertFalse()

#    def test_authorsforbirth(self):
#        self.assertEqual(self.data_source.authors((1819)))

if __name__ == '__main__':
    unittest.main()
