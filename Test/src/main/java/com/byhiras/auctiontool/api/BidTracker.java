package com.byhiras.auctiontool.api;

import java.util.Set;
import java.util.SortedSet;

import com.byhiras.auctiontool.dao.DatastoreException;

public interface BidTracker {
	
	/**
	 * Validates and stores placed bid in cache.
	 * @param bid
	 * @return true if bid was placed correctly
	 */
	public boolean trackPlacedBid(Bid bid);

	/**
	 * Retrieves winning bid for given auction id.
	 * @param auctionId id of queried item
	 * @return Bid object or null if there is no bids on auction
	 * @throws DatastoreException throws exception if auction does not exist
	 */
	public Bid getHighestBid(long auctionId) throws DatastoreException;

	/**
	 * Retrieves all bids for given auction id, sorted from highest to lowest.
	 * @param auctionId 
	 * @return Set of bids for given auction id,
	 * null if there is no bids for that auction
	 * @throws DatastoreException throws exception if auction does not exist
	 */
	public SortedSet<Bid> getAuctionBids(long auctionId) throws DatastoreException;

	/**
	 * Retrieves all bids for given user id.
	 * @param userId id of user 
	 * @return Set of bids for given user id, 
	 * null if there is no bids by the user
	 * @throws DatastoreException throws exception if user does not exist
	 */
	public Set<Bid> getUserBids(int userId) throws DatastoreException;

}
