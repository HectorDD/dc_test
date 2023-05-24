package org.example;

import org.example.model.Category;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// Main class of the application
public class Main {

    /* getCategoryLevel gets the level of a category. It is going to find the category using the category name. It was
    Implemented recursively.
    Inputs:
    - category: Is the category of the current call, the node that we are visiting. For the first call it
    should be root.
    - categoryName: The name of the category that we are looking for.
    - level: The current level of the node that we are currently visiting. For the first call should be 0.
    Output:
    The level of the category where it was found. It is going to retrieve -1 if the category was not found.
    */
    public static int getCategoryLevel(Category category, String categoryName, int level){

        if(category.getName().equals(categoryName)){
            return level;
        }

        for(int i=0; i<category.children.size(); i++){
            int result = getCategoryLevel(category.children.get(i), categoryName, level + 1);
            if(result != -1){
                return result;
            }
            }

        return -1;
    }

    /* getCategoryKeywords gets the keywords of a category, including its parents keywords. It is implemented
    recursively.
    Inputs:
    - category: Is the category of the current call, the node that we are visiting. For the first call it
    should be root.
    - categoryName: The name of the category that we are looking for.
    - prevKeywords: The keywords included in the previous method call. It works as an accumulator of keywords.
    For the first call it should be an empty List<String>
    Output:
    An List<String> that include all the keywords of a category, including its parents categories.
    */
    public static List<String> getCategoryKeywords(Category category, String categoryName, List<String> prevKeywords){
        List<String> currentKeywords = Stream.concat(prevKeywords.stream(), category.getKeywords().stream())
                .collect(Collectors.toList());
        if(category.getName().equals(categoryName)){
            return currentKeywords;
        }
        for(int i=0; i<category.children.size(); i++){
            List<String> result = getCategoryKeywords(category.children.get(i), categoryName, currentKeywords);
            if(!result.isEmpty()){
                return result;
            }
        }
        return new ArrayList<>();
    }

    public static void main(String[] args) {
        List<String> rootKeywords = new ArrayList<>();
        rootKeywords.add("key1");
        rootKeywords.add("key2");
        Category root = new Category("root", rootKeywords);
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
        int level = getCategoryLevel(root,"root", 0);
        System.out.println(level);
        List<String> sampleKeywords = getCategoryKeywords(root,"root", new ArrayList<>());
        System.out.println(sampleKeywords);

    }
}