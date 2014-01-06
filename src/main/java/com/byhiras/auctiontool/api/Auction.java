package com.byhiras.auctiontool.api;

public class Auction {
	
	private final long auctionId;
	private final int ownerId;
	private String description;
//	private Set<Bid> auctionBids;
	
	public Auction(long auctionId, int ownerId) {
		this.ownerId = ownerId;
		this.auctionId = auctionId;
	}

	public long getId() {
		return auctionId;
	}

	public int getOwnerId() {
		return ownerId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
