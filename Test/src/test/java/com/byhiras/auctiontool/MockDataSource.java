package com.byhiras.auctiontool;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.byhiras.auctiontool.api.Auction;
import com.byhiras.auctiontool.api.Bid;
import com.byhiras.auctiontool.api.User;
import com.byhiras.auctiontool.dao.AuctionDataSource;

public class MockDataSource implements AuctionDataSource {

	Map<Integer, User> mockUserDatabase;
	Map<Long, Auction> mockAuctionDatabase;
	Set<Bid> mockBidsDatabase;
	
	public MockDataSource() {
		mockAuctionDatabase = new HashMap<>();
		mockUserDatabase = new HashMap<>();
		mockBidsDatabase = new HashSet<>();
	}
		
	@Override
	public User getUser(int userId) {
		return mockUserDatabase.get(userId);
	}
	
	@Override
	public User insertUser() {
		User user = new User(genId(mockUserDatabase));
		mockUserDatabase.put(user.getId(), user);
		return user;
	}
	
	public void addUsers(User... users) {
		for (User user : users) {
			mockUserDatabase.put(user.getId(), user);
		}
	}
	
	@Override
	public Auction getAuction(long auctionId) {
		return mockAuctionDatabase.get(auctionId);
	}
	
	public void addAuctions(Auction... auctions) {
		for (Auction auction : auctions) {
			mockAuctionDatabase.put(auction.getId(), auction);
		}
	}

	@Override
	public Auction insertAuction(int ownerId) {
		Auction auction = new Auction(genId(mockAuctionDatabase), ownerId);
		mockAuctionDatabase.put(auction.getId(), auction);
		return auction;
	}
		
	/**
	 * Hacky id generator just for the purpose of the test
	 * @param mockDB
	 * @return
	 */
	private int genId(Map<?, ?> mockDB) {
		return mockDB.size();
	}

	@Override
	public Bid placeBid(long auctionId, int userId, BigDecimal bidValue) {
		Bid bid = new Bid(auctionId, userId, bidValue);
		mockBidsDatabase.add(bid);
		return bid;
	}

	@Override
	public Set<Bid> getAuctionBids(long auctionId) {
		Set<Bid> auctionBids = null;
		for (Bid bid : mockBidsDatabase) {
			if (bid.getAuctionId()==auctionId) {
				if (auctionBids==null) {
					auctionBids = new HashSet<>();
				}
				auctionBids.add(bid);
			}
		}
		return auctionBids;
	}

	@Override
	public Set<Bid> getUserBids(int userId) {
		Set<Bid> userBids = null;
		for (Bid bid : mockBidsDatabase) {
			if (bid.getBidderId()==userId) {
				if (userBids==null) {
					userBids = new HashSet<>();
				}
				userBids.add(bid);
			}
		}
		return userBids;
	}

	public void addBids(Set<Bid> bids) {
		mockBidsDatabase.addAll(bids);
	}

}
