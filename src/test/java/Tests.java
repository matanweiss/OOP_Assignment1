import observer.ConcreteMember;
import observer.GroupAdmin;
import observer.Member;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Tests {
    public static final Logger logger = LoggerFactory.getLogger(Tests.class);
    // stub method to check external dependencies compatibility
    @Test
    public void test(){
        String s1 = "Alice";
        String s2 = "Bob";

        logger.info(()->JvmUtilities.objectFootprint(s1));

        logger.info(()->JvmUtilities.objectFootprint(s1,s2));

        logger.info(()->JvmUtilities.objectTotalSize(s1));

        logger.info(() -> JvmUtilities.jvmInfo());
    }

    @Test
    void GroupAdmin(){
        GroupAdmin ga1 = new GroupAdmin();
        assertEquals(ga1.getCurrentData(),"");
        GroupAdmin ga2 = new GroupAdmin("Testing");
        assertEquals(ga2.getCurrentData(),"Testing");
    }
    @Test
    void register() {
        ConcreteMember member1 = new ConcreteMember();
        ConcreteMember member2 = new ConcreteMember();
        ConcreteMember member3 = new ConcreteMember();
        ConcreteMember member4 = new ConcreteMember();
        GroupAdmin ga = new GroupAdmin();
        ga.register(member1);
        ga.register(member1);
        ga.register(member1);
        ga.register(member1);
        ga.register(member1);
        ga.register(member1);
        ga.register(member1);
        ga.register(member1);
        ga.register(member1);
        assertEquals(ga.getMembers().size(),1);
        ga.register(member2);
        ga.register(member3);
        ga.register(member4);
        assertEquals(ga.getMembers().size(),4);
        logger.info(()->"Before actions on UndoableStringBuilder: "+JvmUtilities.objectTotalSize(ga));
        ga.append("Testing");
        ga.append("The");
        ga.append("Register");
        ga.append("Method");
        ga.undo();
        ga.undo();
        ga.undo();
        assertEquals(member1.getCurrentData(),"Testing");
        ga.delete(3,4);
        assertEquals(member2.getCurrentData(),"Tesing");
        ga.insert(3,"t");
        assertEquals(member3.getCurrentData(),"Testing");
        logger.info(()->"After actions on UndoableStringBuilder: "+JvmUtilities.objectTotalSize(ga));
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
        ga.append("Testing");
        assertEquals(member1.getCurrentData(),"Testing");
        logger.info(()->JvmUtilities.objectTotalSize(ga));
        ga.unregister(member1);
        ga.unregister(member2);
        ga.unregister(member3);
        ga.unregister(member4);
        ga.unregister(member5);
        assertEquals(ga.getMembers().size(), 0);
        logger.info(()->JvmUtilities.objectTotalSize(ga));
        assertEquals(member1.getCurrentData(),null);
        assertEquals(member2.getCurrentData(),null);
        assertEquals(member3.getCurrentData(),null);
        assertEquals(member4.getCurrentData(),null);
        assertEquals(member5.getCurrentData(),null);
    }
}
