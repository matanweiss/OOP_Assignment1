import observer.ConcreteMember;
import observer.GroupAdmin;
import observer.Member;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class MyTests {
    @Test
    void register() {
        ConcreteMember member1 = new ConcreteMember();
        ConcreteMember member2 = new ConcreteMember();
        ConcreteMember member3 = new ConcreteMember();
        ConcreteMember member4 = new ConcreteMember();
        GroupAdmin ga = new GroupAdmin();
        ga.register(member1);
        ga.register(member2);
        ga.register(member3);
        ga.register(member3);
        ga.register(member4);
        ArrayList<Member> list = new ArrayList<Member>();
        list.add(member1);
        list.add(member2);
        list.add(member3);
        list.add(member4);
        for (int i = 0; i < 4; i++) {
            assertEquals(ga.getMembers().get(i), list.get(i));
        }
    }
    @Test
    void unregister() {
        ConcreteMember member1 = new ConcreteMember();
        ConcreteMember member2 = new ConcreteMember();
        ConcreteMember member3 = new ConcreteMember();
        ConcreteMember member4 = new ConcreteMember();
        ConcreteMember member5 = new ConcreteMember();
        GroupAdmin ga = new GroupAdmin();
        ga.register(member1);
        ga.register(member2);
        ga.register(member3);
        ga.register(member4);
        ga.unregister(member1);
        ga.unregister(member2);
        ga.unregister(member3);
        ga.unregister(member4);
        ga.unregister(member5);
        assertEquals(ga.getMembers().size(), 0);
    }
    @Test
    void notifyMembers() {
        GroupAdmin ga = new GroupAdmin();
        ConcreteMember member1 = new ConcreteMember();
        ConcreteMember member2 = new ConcreteMember();
        ConcreteMember member3 = new ConcreteMember();
        ga.register(member1);
        ga.register(member2);
        ga.register(member3);
        ga.append("obj");
        ga.notifyMembers();
        assertEquals(member1.getCurrentData(), ga.getCurrentData());
        ga.undo();
        ga.notifyMembers();
        assertEquals(member2.getCurrentData(), ga.getCurrentData());
    }
}
