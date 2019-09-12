package borrow;

import model.Book;
import model.ListBook;
import model.Person;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class BorrowBook {

    public static BorrowBook play = new BorrowBook();

    static Semaphore se = new Semaphore(50,true);

    public static BorrowBook borbowBook(){
        return play;
    }

    static ListBook listBook = new ListBook();

    static int n = listBook.getList().size();

    static boolean[] checkBook = new boolean[n];
    Random r = new Random();

    public   void borrow(Person person) throws InterruptedException {

        try {
            se.acquire();
            int i = searchBook();
            if(i!=-1){
                Book book = listBook.getList().get(i);
                System.out.println(person.getName()+" muon cuon "+book.getNameBook());
                int time = r.nextInt(1500);
                Thread.sleep(time);
                checkBook[i]=false;
                System.out.println(person.getName()+" tra sach "+book.getNameBook());
            }
            else
                System.out.println(person.getName()+" vui long cho");
        }finally {
            se.release();
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
