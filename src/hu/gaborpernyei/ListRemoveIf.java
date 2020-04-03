package hu.gaborpernyei;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ListRemoveIf {
    public static void main(String[] args) {
        List<Integer> lst = new LinkedList<>(Arrays.asList(1,2,3,4,5,6));
        lst.removeIf( (e) -> (e%3==0) );
        System.out.println(lst);
    }
}
