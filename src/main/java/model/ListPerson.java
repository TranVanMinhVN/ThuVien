package model;

import java.util.ArrayList;
import java.util.List;

public class ListPerson {
    List<Person> list;
    public ListPerson(){
        list = new ArrayList<>();
        for(int i=1;i<=1000;i++){
            list.add(new Person(i,"person "+i));
        }
    }

    public List<Person> getList() {
        return list;
    }
}
