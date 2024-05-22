package no.uib.inf101.iterableIterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class StudentClass implements Iterable<Student> {
    
    List<Student> students;
    
    public StudentClass() {
        students = new ArrayList<>();
        students.addAll(getDummyStudents());
    }






    public static List<Student> getDummyStudents() {
        List<Student> dummyStudents = new ArrayList<>();
        dummyStudents.add(new Student("Kari"));
        dummyStudents.add(new Student("Martin"));
        dummyStudents.add(new Student("Bob"));
        dummyStudents.add(new Student("Sondre"));
        dummyStudents.add(new Student("Mons"));
        return dummyStudents;
    }

    @Override
    public Iterator<Student> iterator() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'iterator'");
    }
}
