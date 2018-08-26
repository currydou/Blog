package com.example.curryzhang.hyblog.designpattern.observer.second;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by curry.zhang on 3/22/2017.
 */

public class Observer1 implements Observer {

    public void registerSubject(Observable observable) {
        observable.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof SubjectFor3d) {
            SubjectFor3d subjectFor3d = (SubjectFor3d) o;
            System.out.println("subjectFor3d msg -- >"+subjectFor3d.getMsg());
        }
         if (o instanceof SubjectForSSQ) {
             SubjectForSSQ subjectForSSQ = (SubjectForSSQ) o;
            System.out.println("SubjectForSSQ msg -- >"+subjectForSSQ.getMsg());
        }


    }
}
