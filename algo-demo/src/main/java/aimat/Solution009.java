package aimat;

import java.util.Stack;

/**
 * @author bo.luo
 * @date 2021/1/19 14:50
 */
public class Solution009 {
}

class CQueue {

    private Stack first = new Stack();
    private Stack second = new Stack();

    public CQueue() {

    }

    public void appendTail(int value) {
        second.push(value);
    }

    public int deleteHead() {
        return -1;
    }
}

