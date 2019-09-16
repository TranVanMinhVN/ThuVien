package library;

import borrow.BorrowBook;
import model.Book;
import model.ListBook;
import model.ListPerson;
import model.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Library {

    List<Person> listPerson;
    List<Book> listBook;

    public Library(){
        ListBook listBook = new ListBook();
        ListPerson listPerson = new ListPerson();
        this.listPerson = listPerson.getList();
        this.listBook = listBook.getList();
    }

    public void run(){
        ExecutorService pool = Executors.newCachedThreadPool();
        for(int i=0;i<listPerson.size();i++){
            Person person = listPerson.get(i);
            pool.submit(new Thread() {
                @Override
                public void run() {
                    try {
                        BorrowBook.borbowBook().borrow(person);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        pool.shutdown();
    }
}

