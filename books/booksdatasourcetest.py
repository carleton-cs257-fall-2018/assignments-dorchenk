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

    def test_neg_ID(self):
        self.assertRaises(ValueError, self.data_source.books, author_id = -1)

    def test_authors(self):
        self.assertTrue(self.books)

    def test_authorsForBook(self):
        self.assertTrue()

    def test_authorsForYear(self):
        self.assertFalse()

    def test_authorsforbirth(self):
        self.assertEqual(self.authors(null, null,null,null, (1819)))

if __name__ == '__main__':
    unittest.main()
