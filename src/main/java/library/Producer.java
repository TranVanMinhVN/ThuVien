package library;

import model.Book;
import model.ListBook;
import model.ListPerson;
import model.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Producer implements Runnable {

    Random rd = new Random();
    static boolean[] checkBook = new boolean[5];
    static List<Person> list = new ArrayList<>();
    final Lock lock = new ReentrantLock();

    private ListPerson listPerson;
    private ListBook listBook;
    private final BlockingQueue<Person> queue;

    public Producer(boolean[] checkBook,List<Person> list , BlockingQueue<Person> queue) {
        listBook = new ListBook();
        listPerson = new ListPerson();
        this.checkBook = checkBook;
        this.list = list;
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            process();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void process() throws InterruptedException {

        for(Person person : listPerson.getList()){
            int idBook = rd.nextInt(5);
            person.setIdBook(idBook);
            Book book = listBook.getList().get(idBook);
            System.out.println(person.getName()+" muốn mượn "+book.getNameBook());
            if(!checkBook[idBook]){
                checkBook[idBook] = true;
                System.out.println(person.getName()+" mượn thành công "+book.getNameBook());
                Thread.sleep(50);
                queue.put(person);
            }
            else {
                list.add(person);
                System.out.println(book.getNameBook()+" đang có người đọc!!!Vui lòng chờ!!!!");
            }
            if (list.size()>0){
                for (Person p : list){
                    if(!checkBook[p.getIdBook()]){
                        checkBook[p.getIdBook()]=true;
                        System.out.println(p.getName()+" mượn thành công book "+p.getIdBook());
                        Thread.sleep(50);
                        System.out.println(p.getName()+" tra book "+p.getIdBook());
                        checkBook[p.getIdBook()]=false;
                        list.remove(p);
                        if(list.size()==0) break;
                    }
                }
            }
        }
        queue.put(new Person(1200,"hetperson"));
    }
}
