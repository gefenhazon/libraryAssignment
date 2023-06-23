Home Test.
-------------

Before starting with the test, here are some *tips* that you might find useful:

1. Read this document entirely and make sure you understand it well.
2. Read the code, and get familiar with all of the classes and the APIs of those classes.
3. Look at the tests in Main class, it is recommended to run the tests while implementing the methods and not wait to the end. 
4. Try to understand the data relationships.
5. Good luck! 

### Exam
In this exam, you are required to implement a minimized version of book management system for a library. 

The product you are going to implement will help librarians with some basic book management. For example operations like 
lending books to readers, retrieving books from readers, adding new books to storage, and so on. Some basic 
functionalities you'd expect from library. 

#### System Requirements

1) Each book title can have many physical copies. So, for example, Ayn Rand's "Atlas Shrugged" can have 10 different physical
copies. These 10 different copies can be lent to 10 different customer readers. The physical copies are
represented by the `BookPhysicalCopy` class, while the title is represented by the `Book` class.

2) If the book already exists in library, and you want to add new physical copies of it, then you can do it by using
   the function `addNewBookCopiesToCollection`.

3) Each physical copy can be given to a single customer reader. Meaning that two customer readers cannot have the
   same copy at the same time. Each of them can have two distinct copies of the same title.

4) Each user cannot have more than one copy of the same title. For example, Avi, cannot have 3 copies of "Atlas Shrugged".

5) Each user can have at most three copies of different books. For example, if Avi already has "Atlas Srugged",
   "The Fountainhead", and "Nothing To Lose", then Avi cannot borrow another book until he returns one back.

6) You are not allowed to remove book title from library if at least one of it copies is held by customer header.


#### Assignment
Your assignment is to implement the `BooksManagementSystem` class.

Here is the list of the methods on the `BooksManagementSystem` you should implement:

1. `addNewBookToCollection` - Adds new book title to collection and creates physical copies of it. 
Note there are two functions with the same signature you should implement.

2. `addNewBookCopiesToCollection` - Adds new physical copies to existing book.

3. `removeBookFromCollection` - Removes book title from library. Recall, removal is possible if and only if there is no
physical copy that is held currently by a customer reader.

4. `getAllAvailableCopies` - Returns a list of available physical copies for a specific title. If all of the physical 
copies are currently borrowed, then it returns an empty list.

5. `lendBookToReader` - Lends a physical copy of a title to a specific customer reader. Recall, the customer reader cannot 
have more than three one copy of the same title, and more than three copies in total.

6. `returnBookFromReader` - Returns the book back to the library and frees the customer reader.

It is important to note that you are **NOT ALLOWED** to change any methods names, APIs, return values or signatures within this class and other classes on this project.

You are allowed to use the internet, use any data structure you'd like to, add your own classes, methods,
code, and whatever helps you solve the problem.

#### Files Review

1. `Main` - Entry point. Class that contains tests. 
2. `BooksManagementSystem` - The class that you should implement.
3. `Book` - a data class that represents a book title. 
4. `BookPhysicalCopy` - a data class that represents a physical book copy. 
5. `CustomerReader` - a data class that represents a customer who may borrow a book.
6. `Helpers` - a class with some helpers, your general helper utils should be here.
7. `Genre` - an enum for book genres.
8. `Languages` - an enum for book languages.

#### Important notes:
1. `Book` and `BookPhysicalCopy` are not the same. The `Book` class holds general book information like title, author, 
publisher and more. The `BookPhysicalCopy`, on the other hand is the actual book that customer may take home and return.
Make sure that you understand this point.

2. The classes of `Book`, `BookPhysicalCopy`, `CustomerReader`, `Genre`, `Languages` are all implemented. No need to 
touch them at all. The only class you should touch is `BookManagementSystem`, `Helpers` and any class that you choose to
create.

#### Expectations and code requirements

1) Your code should compile.
2) Your code should run, without any runtime errors or exceptions.
3) Your code should answer the software requirements above.
4) Your code should be readable and understandable.
5) Ideally, your code should pass all the tests. 

**IMPORTANT** It is OK if your code does not pass the entire test
   suite. No matter what, still submit the code.
