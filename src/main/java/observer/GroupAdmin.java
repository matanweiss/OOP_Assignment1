package observer;

import java.util.HashSet;

public class GroupAdmin implements Sender {
    private HashSet<Member> memberList;
    private UndoableStringBuilder current;

    public GroupAdmin() {
        memberList = new HashSet<Member>();
        current = new UndoableStringBuilder();
    }

    public GroupAdmin(String s) {
        memberList = new HashSet<Member>();
        current = new UndoableStringBuilder(s);
    }

    @Override
    public void register(Member obj) {
        if (!memberList.contains(obj)) {
            memberList.add(obj);
        }
    }

    @Override
    public void unregister(Member obj) {
        obj.update(null);
        memberList.remove(obj);
    }

    @Override
    public void insert(int offset, String obj) {
        current.insert(offset, obj);
        notifyMembers();
    }

    @Override
    public void append(String obj) {
        current.append(obj);
        notifyMembers();
    }

    @Override
    public void delete(int start, int end) {
        current.delete(start, end);
        notifyMembers();
    }

    @Override
    public void undo() {
        current.undo();
        notifyMembers();
    }

    public void notifyMembers() {
        for (Member member : memberList) {
            member.update(current);
        }
    }
    public String getCurrentData(){
        return current.toString();
    }

    public HashSet<Member> getMembers(){
        return memberList;
    }
}