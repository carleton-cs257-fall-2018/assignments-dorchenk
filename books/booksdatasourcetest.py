'''
   primecheckertests.py
   Jeff Ondich, 9 May 2012
   Updated for use in a lab exercise, 4 Nov 2013
'''

import primechecker
import unittest
import booksdatasource

class BooksDataSourceTester(unittest.TestCase):
    def setUp(self):
        self.prime_checker = primechecker.PrimeChecker(100)

    def tearDown(self):
        pass

    def test_ID(self):
        self.assertRaises(self.book, self)

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
