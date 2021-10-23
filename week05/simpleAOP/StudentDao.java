package java0.homework.week5.simpleAOP;

public class StudentDao implements IStudentDao{
    @Override
    public void study() {
        System.out.println("学习知识");
    }
}
