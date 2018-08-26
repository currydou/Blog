package com.example.curryzhang.hyblog.designpattern.observer.second;

/**
 * Created by curry.zhang on 3/22/2017.
 */

public class Test {
    public static void main(String[] args) {
        SubjectFor3d subjectFor3d = new SubjectFor3d();
        SubjectForSSQ subjectForSSQ = new SubjectForSSQ();

        Observer1 observer1 = new Observer1();
        observer1.registerSubject(subjectFor3d);
        observer1.registerSubject(subjectForSSQ);

        subjectFor3d.setMsg("hello 3d : 100");
        subjectForSSQ.setMsg("hello ssq : 101");
    }
}
