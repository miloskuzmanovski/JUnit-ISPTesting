import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.*;
import org.junit.*;

import java.lang.constant.Constable;
import java.util.*;
import static junit.framework.TestCase.assertEquals;

public class ISPTest {

    private List<String> list1;
    private List<String> list2;
    private Iterator<String> itr1;
    private Iterator<String> itr2;

    private Constable commonElems(List<String> list1, List<String> list2) {
        List<String> common = new ArrayList<>(this.list1);
        if (list1==null || list2==null) {
            throw new NullPointerException();
        }
        common.retainAll(this.list2);
        if (common.isEmpty())
            return null;
        else {
            return common.size();
        }
    }

    //3 tests for interface-based approach
    @Test
    public void BothListsAreNotNull() {
        //C1=F, C2=F
        list1 = new ArrayList<String>();
        list2 = new ArrayList<String>();
        list1.add("pet");
        list2.add("cat");
        itr1 = list1.iterator();
        itr2 = list2.iterator();
        assertTrue(itr1.hasNext());
        assertTrue(itr2.hasNext());
    }

    @Test(expected = NullPointerException.class)
    public void ListOneIsNull() {
        //C1=T, C2=F
        List<String> list1 = null;
        List<String> list2 = new ArrayList<String>();
        list2.add("pet");
        assertNull(commonElems(list1, list2));
    }

    @Test(expected = NullPointerException.class)
    public void ListTwoIsNull() {
        //C1=F, C2=T
        List<String> list1 = new ArrayList<String>();
        List<String> list2 = null;
        list1.add("pet");
        assertNull(commonElems(list1, list2));
    }

    //functionality-based approach
    @Test
    public void commonElementsIsEmpty() {
        //C3=T, C4=0
        list1 = new ArrayList<String>();
        list1.add("cat");
        list2 = new ArrayList<String>();
        list2.add("dog");
        assertNull(commonElems(list1, list2));
    }

    @Test
    public void OneCommonElement() {
        //C3=F, C4=1
        list1 = new ArrayList<String>();
        list2 = new ArrayList<String>();
        list1.add("pet");
        list1.add("dog");
        list2.add("cat");
        list2.add("pet");
        assertEquals(commonElems(list1, list2), 1);
    }

    @Test
    public void MoreCommonElements() {
        //C3=F, C4= >1
        list1 = new ArrayList<String>();
        list2 = new ArrayList<String>();
        list1.add("pet");
        list1.add("dog");
        list1.add("bird");
        list2.add("cat");
        list2.add("pet");
        list2.add("bird");
        assertNotEquals(commonElems(list1, list2), 1);
    }
}