package com.byhiras.auctiontool;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import com.byhiras.auctiontool.api.Auction;
import com.byhiras.auctiontool.api.Bid;
import com.byhiras.auctiontool.api.BidTracker;
import com.byhiras.auctiontool.api.User;
import com.byhiras.auctiontool.dao.AuctionDataSource;
import com.byhiras.auctiontool.dao.DatastoreException;

public class AuctionManager implements BidTracker {
	
	private AuctionDataSource datasource;
	
	private Set<Bid> bidsCache;
	
	AuctionManager(AuctionDataSource datasource){
		this.datasource = datasource;
	}

	public Auction createAuction(User owner) throws DatastoreException {
		return createAuction(owner.getId());
	}
	
	public Auction createAuction(int ownerId) throws DatastoreException {
		if (!validateUser(ownerId)) {
			throw new DatastoreException("Invalid user id, user does not exists.");
		}
		return datasource.insertAuction(ownerId);
	}
	
	private boolean validateUser(int userId) {
		boolean isValid = false;
		if (datasource.getUser(userId) != null) {
			isValid = true;
		}
		return isValid;
	}

	public User createUser() throws DatastoreException {
		return datasource.insertUser();
	}

	public boolean placeBid(long auctionId, int userId, BigDecimal bidValue) {
		boolean isPlaced = false;
		if (validateUser(userId)) {
			Bid bid = datasource.placeBid(auctionId, userId, bidValue);
			isPlaced = trackPlacedBid(bid);
		}
		return isPlaced;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Bid getHighestBid(long auctionId) {
		Bid bid = null;
		SortedSet<Bid> bids = getAuctionBids(auctionId);
		if (bids!=null) {
			bid = bids.last();
		}
		return bid;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SortedSet<Bid> getAuctionBids(long auctionId) {
		SortedSet<Bid> sortedBids = null;
		Set<Bid> bids = datasource.getAuctionBids(auctionId);
		if (bids!=null) {
			sortedBids = new TreeSet<>(bids);
		}		
		return sortedBids;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Set<Bid> getUserBids(int userId) {
		return datasource.getUserBids(userId);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean trackPlacedBid(Bid bid) {
		boolean exist = bid != null ? true : false;
		if (exist && bidsCache==null) {
			bidsCache = new HashSet<>();
		}
		exist = bidsCache.add(bid);
		return exist;
	}

}
