package borrow;

import library.Library;
import model.Book;
import model.ListBook;
import model.Person;

import java.util.List;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class BorrowBook {

    ListBook listBook = new ListBook();

    public static BorrowBook newBorrowBook = new BorrowBook();

    public static BorrowBook borbowBook(){
        return newBorrowBook;
    }

    static Semaphore se = new Semaphore(50,true);

    int n = listBook.getList().size();
    boolean[] checkBook = new boolean[n];

    Random r = new Random();
    public   void borrow(Person person) throws InterruptedException {
        try {
            se.acquire();
            int i = searchBook();
            Book book = listBook.getList().get(i);
            System.out.println(person.getName() + " muon cuon " + book.getNameBook());
            int time = r.nextInt(1500);
            Thread.sleep(time);
            checkBook[i] = false;
            System.out.println(person.getName() + " tra sach " + book.getNameBook());
            se.release();
        } catch (Exception e) {
            System.out.println("muon that bai");
        }
    }
    public synchronized int searchBook(){
        for(int i=0;i<n;i++){
            if(!checkBook[i]){
                checkBook[i]=true;
                return i;
            }
        }
        return -1;
    }

}
