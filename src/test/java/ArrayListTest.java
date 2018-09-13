import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;

/**
 * 测试 ArrayList 的 fail-fast 机制
 * @author yule
 * @date 2018/9/13 17:05
 */
public class ArrayListTest {
    private List<Integer> list = null;

    @Before
    public void buildList(){
        list = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            list.add(i);
        }
        Assert.assertEquals("[0, 1, 2, 3, 4, 5, 6, 7, 8, 9]", list.toString());
    }

    @Test
    public void testFor(){
        for(int i = 0; i < list.size(); i++){
            if(i == 2){
                //这个会删除成功，这里没有 fail-fast 的机制
                list.remove(2);
            }
        }
        Assert.assertEquals("[0, 1, 3, 4, 5, 6, 7, 8, 9]", list.toString());
    }

    @Test(expected = ConcurrentModificationException.class)
    public void testForEach(){
        for(int x : list){
            if(x == 2){
                //这个抛出异常，这里就是 fail-fast 的机制，毕竟 foreach 底层就是 Iterator
                list.remove(2);
            }
        }
    }

    @Test(expected = ConcurrentModificationException.class)
    public void testIterator(){
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()){
            int x = iterator.next();
            if(x == 2){
                //这个抛出异常，这里就是 fail-fast 的机制
                list.remove(2);
            }
        }
    }

    @Test
    public void testIteratorSuccess(){
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()){
            int x = iterator.next();
            if(x == 2){
                //这个会成功，不会抛出异常，这里也是 fail-fast 的机制
                iterator.remove();
            }
        }
        Assert.assertEquals("[0, 1, 3, 4, 5, 6, 7, 8, 9]", list.toString());
    }

}


