package application;

import borrow.BorrowBook;
import model.ListPerson;
import model.Person;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Library {
    public static void main(String[] args) {
        ListPerson listPerson = new ListPerson();
        ExecutorService pool = Executors.newCachedThreadPool();
        for(int i=0;i<1000;i++){
            Person person = listPerson.getList().get(i);
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
