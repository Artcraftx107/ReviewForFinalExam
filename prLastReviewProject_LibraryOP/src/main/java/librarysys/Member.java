package librarysys;

public class Member {

    private final int id;
    private final String name;

    public Member(int id, String name){
        if(id<0||name.isBlank()){
            throw new LibraryException("Neither the id can be negative or the name can be blank");
        }
        this.id=id;
        this.name=name;
    }

    //Getters

    public int id() {
        return id;
    }

    public String name() {
        return name;
    }

    @Override
    public String toString() {
        return id+","+name;
    }
}
