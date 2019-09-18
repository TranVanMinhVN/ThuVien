package model;

import java.util.ArrayList;
import java.util.List;

public class ListBook {
    List<Book> list;
    public ListBook(){
        list = new ArrayList<>();
        for (int i=0;i<=4;i++){
            list.add(new Book(i,"book "+i));
        }
    }

    public List<Book> getList() {
        return list;
    }
}
