/*
 * DISCLAIMER:
 * This is a mock project created for educational purposes only.
 * It does not implement real security measures and should not be used
 * as-is in a production environment. Passwords and other sensitive information
 * are handled in an insecure manner for demonstration purposes.
 */
package auctionsys;

public class Item {
    private String itemName;
    private String description;
    private double startingPrice;
    private double currentHighestBid;
    private boolean auctionStatus;

    public Item(String itemName, String description, double startingPrice){
        if(itemName.isBlank()||description.isBlank()){
            throw new IllegalArgumentException("Neither the item's name or description can be blank");
        }
        if(startingPrice<=0){
            throw new IllegalArgumentException("The item's starting price cannot be equal or lower than 0");
        }
        this.itemName=itemName;
        this.currentHighestBid=0;
        this.description=description;
        this.startingPrice=startingPrice;
        this.auctionStatus=true;
    }

    public void placeBid(double bidAmount){
        if(bidAmount>0&&bidAmount>currentHighestBid){
            setCurrentHighestBid(bidAmount);
        }else{
            throw new IllegalArgumentException("The amount entered ($"+bidAmount+") is lower than the current highest bid ($"+currentHighestBid+")");
        }
    }

    //Getters and Setters

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getCurrentHighestBid() {
        return currentHighestBid;
    }

    public void setCurrentHighestBid(double currentHighestBid) {
        this.currentHighestBid = currentHighestBid;
    }

    public double getStartingPrice() {
        return startingPrice;
    }

    public void setStartingPrice(double startingPrice) {
        this.startingPrice = startingPrice;
    }

    public boolean isAuctionStatus() {
        return auctionStatus;
    }

    public void setAuctionStatus(boolean auctionStatus) {
        this.auctionStatus = auctionStatus;
    }

    @Override
    public String toString() {
        return itemName+","+description+","+startingPrice;
    }
}