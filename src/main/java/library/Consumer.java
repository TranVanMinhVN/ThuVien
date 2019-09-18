package library;

import model.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Consumer implements Runnable {

    static boolean[] checkBook = new boolean[5];
    static List<Person> list = new ArrayList<>();
    final Lock lock = new ReentrantLock();

    Random rd = new Random();

    private final BlockingQueue<Person> queue ;

    public Consumer(boolean[] checkBook,  List<Person> list, BlockingQueue<Person> queue) {
        this.checkBook = checkBook;
        this.list = list;
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            Person person;
            while ((person = queue.take() ).getId() != 1200){
                process(person);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void  process(Person person) throws InterruptedException {
        System.out.println(person.getName()+" tra book "+person.getIdBook());
        checkBook[person.getIdBook()]=false;
    }
}
