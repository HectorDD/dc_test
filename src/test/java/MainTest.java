import org.example.model.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.example.Main.getCategoryKeywords;
import static org.example.Main.getCategoryLevel;
import static org.junit.jupiter.api.Assertions.*;



public class MainTest {

    Category root;

    // To set categories for all the test cases
    @BeforeEach
    public void setup(){
        List<String> rootKeywords = new ArrayList<>();
        rootKeywords.add("key1");
        rootKeywords.add("key2");
        root = new Category("root", rootKeywords);
        List<String> cat1Keywords = new ArrayList<>();
        cat1Keywords.add("key3");
        Category cat1 = new Category("cat1", cat1Keywords);
        List<String> cat2Keywords = new ArrayList<>();
        cat2Keywords.add("key4");
        Category cat2 = new Category("cat2", cat2Keywords);
        List<String> cat3Keywords = new ArrayList<>();
        cat2Keywords.add("key5");
        Category cat3 = new Category("cat3", cat3Keywords);
        cat2.addChildren(cat3);
        root.addChildren(cat1);
        root.addChildren(cat2);
    }

    @Test
    public void testCategoryLevelWhenRoot(){
        int level = getCategoryLevel(root,"root", 0);
        assertEquals(level,0);
    }

    @Test
    public void testCategoryLevelWhenNotFound(){
        int level = getCategoryLevel(root,"notfoundcateg", 0);
        assertEquals(level,-1);
    }

    @Test
    public void testCategoryLevelWhenLevel1(){
        int level = getCategoryLevel(root,"cat1", 0);
        assertEquals(level,1);
    }

    @Test
    public void testCategoryLevelWhenLevel1DiffChildren(){
        int level = getCategoryLevel(root,"cat2", 0);
        assertEquals(level,1);
    }

    @Test
    public void testCategoryLevelWhenLevel2(){
        int level = getCategoryLevel(root,"cat3", 0);
        assertEquals(level,2);
    }

    @Test
    public void testCategoryKeywordsRoot(){
        List<String> keywords = getCategoryKeywords(root,"root", new ArrayList<>());
        assertTrue(keywords.contains("key1"));
        assertTrue(keywords.contains("key2"));
        assertEquals(keywords.size(),2);
    }

    @Test
    public void testCategoryKeywordsNotFoundCategory(){
        List<String> keywords = getCategoryKeywords(root,"notfoundcateg", new ArrayList<>());
        assertEquals(keywords.size(),0);
    }

    @Test
    public void testCategoryKeywordsCategoryInLevel1(){
        List<String> keywords = getCategoryKeywords(root,"cat1", new ArrayList<>());
        assertTrue(keywords.contains("key1"));
        assertTrue(keywords.contains("key2"));
        assertTrue(keywords.contains("key3"));
        assertEquals(keywords.size(),3);
    }

    @Test
    public void testCategoryKeywordsCategoryInLevel2(){
        List<String> keywords = getCategoryKeywords(root,"cat3", new ArrayList<>());
        assertTrue(keywords.contains("key1"));
        assertTrue(keywords.contains("key2"));
        assertTrue(keywords.contains("key4"));
        assertTrue(keywords.contains("key5"));
        assertEquals(keywords.size(),4);
    }


}
