package library;

import model.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Library {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Person> queue = new LinkedBlockingQueue<>(5);
        boolean[] checkBook = new boolean[5];
        List<Person> list = new ArrayList<>();
        new Thread(new Producer(checkBook,list,queue)).start();
        new Thread(new Consumer(checkBook,list,queue)).start();
    }
}

