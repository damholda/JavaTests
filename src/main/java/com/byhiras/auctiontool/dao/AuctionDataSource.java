package com.byhiras.auctiontool.dao;

import java.math.BigDecimal;
import java.util.Set;

import com.byhiras.auctiontool.api.Auction;
import com.byhiras.auctiontool.api.Bid;
import com.byhiras.auctiontool.api.User;

public interface AuctionDataSource {

	public User getUser(int userId);
	
	public Auction getAuction(long auctionId);

	/**
	 * Retrieves all bids for given auction id.
	 * @param auctionId 
	 * @return null if no bids are found for given auction id.
	 */
	public Set<Bid> getAuctionBids(long auctionId);

	/**
	 * Retrieves all bids for given user id.
	 * @param userId
	 * @return null if no bids are found for given user id.
	 */
	public Set<Bid> getUserBids(int userId);
	
	public Bid placeBid(long auctionId, int userId, BigDecimal bidValue);

	/**
	 * Creates new auction in database with provided ownerId.
	 * @param ownerId user id of auction creator
	 * @return Auction with given user id as owner
	 * @throws DatastoreException if could not create auction or owner id is non existant
	 */
	public Auction insertAuction(int ownerId) throws DatastoreException;
	
	/**
	 * Creates new user in database. 
	 * @return User object
	 * @throws DatastoreException if could not create user
	 */
	public User insertUser() throws DatastoreException;
	
}
