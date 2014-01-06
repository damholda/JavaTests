package com.byhiras.auctiontool.api;

import java.math.BigDecimal;

public final class Bid implements Comparable<Bid>{
	
	private final long auctionId;
	private final int bidderAccountId;
	private final BigDecimal bidValue;
	
	public Bid(long auctionId, int bidderAccountId, BigDecimal bidValue) {
		this.auctionId = auctionId;
		this.bidderAccountId = bidderAccountId;
		this.bidValue = bidValue;
	}
	
	public long getAuctionId() {
		return auctionId;
	}
	
	public int getBidderId(){
		return bidderAccountId;
	}

	public BigDecimal getBidValue() {
		return bidValue;
	}

	@Override
	public int compareTo(Bid bid) {
		int compare = bidValue.compareTo(bid.getBidValue());
		
		return compare;
	}
	
	@Override
	public int hashCode() {
		int result = 0;
		result+=(int)(auctionId ^ (auctionId >>> 32));
		result+=bidderAccountId;
		result+=bidValue.hashCode();
		return 37 * result;
	}
	
	@Override
	public boolean equals(Object o) {
		boolean isEqual = false;
		if (o instanceof Bid) {
			Bid bid = (Bid) o;
			if(bid.getAuctionId()==auctionId &&
				bid.getBidderId()==bidderAccountId &&
				bid.getBidValue().equals(bidValue)) {
				isEqual = true;
			}
		}
		return isEqual;
	}

}
