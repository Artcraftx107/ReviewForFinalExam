package library;

public class Author {
    private String authorID;
    private String name;

    public Author(String authorID, String name){
        if(authorID.isBlank()||name.isBlank()){
            throw new IllegalArgumentException("Neither the author's ID or name can be blank");
        }
        this.authorID=authorID;
        this.name=name;
    }

    //Getters

    public String getName() {
        return name;
    }

    public String getAuthorID() {
        return authorID;
    }

    @Override
    public String toString() {
        return authorID+","+name;
    }
}
