
class Person {
    String name;
    int id;
    int age;

    Person(String name, int id, int age) {
        this.name = name;
        this.id = id;
        this.age = age;
    }

    void displayInfo() {
        System.out.println("Name: " + name + " ID: " + id + " Age: " + age);
    }
}

class Librarian extends Person {

    Librarian(String name, int id, int age) {
        super(name, id, age);
    }

    void addBook(String book) {
        System.out.println(name + " added book: " + book);
    }

    void removeBook(String book) {
        System.out.println(name + " removed book: " + book);
    }

    void issueBook(String book, String member) {
        System.out.println(book + " issued to " + member);
    }
}

class Member extends Person {

    Member(String name, int id, int age) {
        super(name, id, age);
    }

    void borrowBook(String book) {
        System.out.println(name + " borrowed " + book);
    }

    void returnBook(String book) {
        System.out.println(name + " returned " + book);
    }

    void viewBorrowedBooks() {
        System.out.println(name + " is viewing borrowed books");
    }
}

class Guest extends Person {

    Guest(String name, int id, int age) {
        super(name, id, age);
    }

    void viewCatalog() {
        System.out.println(name + " is viewing the catalog");
    }
}


class Employee {
    String name;
    int employeeID;
    double salary;

    Employee(String name, int employeeID, double salary) {
        this.name = name;
        this.employeeID = employeeID;
        this.salary = salary;
    }

    void displayInfo() {
        System.out.println("Employee: " + name + " ID: " + employeeID + " Salary: " + salary);
    }
}

class Manager extends Employee {

    Manager(String name, int employeeID, double salary) {
        super(name, employeeID, salary);
    }

    void assignTask(String task) {
        System.out.println(name + " assigned task: " + task);
    }

    void approveLeave(String employee) {
        System.out.println(name + " approved leave for " + employee);
    }
}

class Developer extends Employee {

    Developer(String name, int employeeID, double salary) {
        super(name, employeeID, salary);
    }

    void writeCode() {
        System.out.println(name + " is writing code");
    }

    void fixBug() {
        System.out.println(name + " fixed a bug");
    }
}

class Intern extends Employee {

    Intern(String name, int employeeID, double salary) {
        super(name, employeeID, salary);
    }

    void attendTraining() {
        System.out.println(name + " is attending training");
    }

    void submitReport() {
        System.out.println(name + " submitted report");
    }
}


class Product {
    int productID;
    String name;
    double price;

    Product(int productID, String name, double price) {
        this.productID = productID;
        this.name = name;
        this.price = price;
    }

    void displayProductDetails() {
        System.out.println("Product: " + name + " Price: $" + price);
    }
}

class Electronics extends Product {
    String warranty;
    String brand;

    Electronics(int productID, String name, double price, String warranty, String brand) {
        super(productID, name, price);
        this.warranty = warranty;
        this.brand = brand;
    }

    void getWarrantyDetails() {
        System.out.println("Brand: " + brand + " Warranty: " + warranty);
    }
}

class Clothing extends Product {
    String size;
    String color;

    Clothing(int productID, String name, double price, String size, String color) {
        super(productID, name, price);
        this.size = size;
        this.color = color;
    }

    void checkSizeAvailability() {
        System.out.println("Available size: " + size + " Color: " + color);
    }
}

class BookProduct extends Product {
    String author;
    String ISBN;

    BookProduct(int productID, String name, double price, String author, String ISBN) {
        super(productID, name, price);
        this.author = author;
        this.ISBN = ISBN;
    }

    void getAuthorInfo() {
        System.out.println("Author: " + author + " ISBN: " + ISBN);
    }
}


public class Main {

    public static void main(String[] args) {

        // Library System
        Librarian lib = new Librarian("Ali", 1, 35);
        Member mem = new Member("Sara", 2, 21);
        Guest gst = new Guest("John", 3, 30);

        lib.addBook("Java Programming");
        mem.borrowBook("Java Programming");
        gst.viewCatalog();

        System.out.println();

        // Employee System
        Manager manager = new Manager("David", 101, 5000);
        Developer dev = new Developer("Mike", 102, 4000);
        Intern intern = new Intern("Anna", 103, 1000);

        manager.assignTask("Build API");
        dev.writeCode();
        intern.attendTraining();

        System.out.println();

        // Shopping System
        Electronics phone = new Electronics(1, "iPhone", 1200, "1 year", "Apple");
        Clothing shirt = new Clothing(2, "T-Shirt", 25, "M", "Black");
        BookProduct book = new BookProduct(3, "Algorithms", 50, "CLRS", "12345");

        phone.displayProductDetails();
        phone.getWarrantyDetails();

        shirt.displayProductDetails();
        shirt.checkSizeAvailability();

        book.displayProductDetails();
        book.getAuthorInfo();
    }
}
