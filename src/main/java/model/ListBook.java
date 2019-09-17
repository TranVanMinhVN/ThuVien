package model;

import java.util.ArrayList;
import java.util.List;

public class ListBook {
    List<Book> list;
    public ListBook(){
        list = new ArrayList<>();
        for (int i=1;i<=2;i++){
            list.add(new Book(i,"book "+i));
        }
    }

    public List<Book> getList() {
        return list;
    }
}
