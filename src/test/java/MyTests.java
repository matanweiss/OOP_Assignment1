import observer.ConcreteMember;
import observer.GroupAdmin;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MyTests {
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
