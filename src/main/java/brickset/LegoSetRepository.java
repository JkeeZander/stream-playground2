package brickset;

import repository.Repository;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.*;

/**
 * Represents a repository of {@code LegoSet} objects.
 */
public class LegoSetRepository extends Repository<LegoSet> {

    public LegoSetRepository() {
        super(LegoSet.class, "brickset.json");
    }

    /**
     * Checks the dataset for the given name and returns the result based on it
     * @param name string name of the element to search for
     * @return boolean value indicating whether the element with the given name exists or not
     * @author Altan Dzhumaev
     */
    public boolean doesElementExistWithGivenName(String name){
        return getAll().stream().anyMatch((element)->{
            return element.getName().equals(name);
        });
    }

    /**
     * Print names of the LegoSet in the upper case
     *
     * @author Altan Dzhumaev
     */
    public void printNameUpperCase(){
        getAll().stream().flatMap(e->Stream.of(e.getName().toUpperCase())).forEach(System.out::println);
    }

    /**
     * Calculate average number of pieces of the whole dataset
     *
     * @author Altan Dzhumaev
     * @return integer average number of pieces from the whole dataset
     */
    public int getAverageNumberOfPieces(){
        return getAll().stream().map(e->e.getPieces()).reduce(0,Integer::sum)/getAll().size();
    }

    /**
     * Traverses dataset and returns a map with number,pieces key value pair
     * @return map that has number of LegoSet as the key and pieces as the value
     * @author Altan Dzhumaev
     */
    public Map getMapFromNumberToPiecesAssociated(){
        return getAll().stream().collect(Collectors.toMap(e->e.getNumber(),e->e.getPieces()));
    }

    /**
     *Traverses data and returns map that maps name to the number of occurences of name
     * @return Map with key as a name and value as the occurence of the key
     * @author Altan Dzhumaev
     */
    public Map getMapFromNameToOccurenceAssociated(){
        return getAll().stream().collect(Collectors.groupingBy(e->e.getName(),Collectors.counting()));
    }


    public static void main(String[] args){
        var repository = new LegoSetRepository();
        System.out.print(repository.doesElementExistWithGivenName("Heart"));
        repository.printNameUpperCase();
        System.out.println(repository.getAverageNumberOfPieces());
        System.out.println(repository.getMapFromNumberToPiecesAssociated());
        System.out.println(repository.getMapFromNameToOccurenceAssociated());



    }

}
