package chap09.collections.comparator;
import java.util.*;
public class ComparatorTest {
  public static void main(String[] args) {
    Comparator c = new NameComp();
    TreeSet studentSet = new TreeSet(c);
    studentSet.add(new Student("Mike", "Hauffmann",101,4.0));
    studentSet.add(new Student("John", "Lynn",102,2.8 ));
    studentSet.add(new Student("Jim", "Max",103, 3.6));
    studentSet.add(new Student("Kelly", "Grant",104,2.3));
    Object[] studentArray = studentSet.toArray();
    Student s;
    for(Object obj : studentArray){
      s = (Student) obj;
      System.out.printf("Name = %s %s ID = %d GPA = %.1f\n",
        s.firstName(), s.lastName(), s.studentID(), s.GPA());
    }
  }
}