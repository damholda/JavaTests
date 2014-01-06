package com.byhiras.auctiontool;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.byhiras.auctiontool.api.Auction;
import com.byhiras.auctiontool.api.Bid;
import com.byhiras.auctiontool.api.User;
import com.byhiras.auctiontool.dao.DatastoreException;

public class AuctionManagerTest {

	private AuctionManager auctionManager;
	
	long auctionId1;
	long auctionId2;
	int userId1;	
	
	@Before
	public void setup() {
		MockDataSource mockDataSource = new MockDataSource();
		auctionId1 = 1;
		auctionId2 = 2;
		userId1 = 1;
		mockDataSource.addUsers(new User(0), 
				new User(userId1));
		mockDataSource.addAuctions(new Auction(0, 0), 
				new Auction(auctionId1, userId1), new Auction(auctionId2, userId1));
		Set<Bid> bids = new HashSet<>();
		bids.add(new Bid(auctionId1, userId1, new BigDecimal(2)));
		bids.add(new Bid(auctionId1, userId1, new BigDecimal(1)));
		bids.add(new Bid(auctionId1, userId1, new BigDecimal(3)));
		bids.add(new Bid(auctionId2, userId1, new BigDecimal(3)));
		bids.add(new Bid(auctionId2, userId1, new BigDecimal(6)));
		mockDataSource.addBids(bids);
		auctionManager = new AuctionManager(mockDataSource);
	}
	
	@Test
	public void testCreateAuction() {
		try {
			assertNotNull(auctionManager.createAuction(0));
		} catch (DatastoreException e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void testCreateUser() {
		try {
			assertNotNull(auctionManager.createUser());
		} catch (DatastoreException e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void testPlaceBid() {
		BigDecimal bidValue = new BigDecimal(5);
		assertTrue(auctionManager.placeBid(0, 0, bidValue));
		assertFalse(auctionManager.placeBid(0, 0, bidValue));
	}
	
	@Test
	public void testGetWinningBid() {
		Bid winingBid = auctionManager.getHighestBid(auctionId1);
		assertEquals(new BigDecimal(3), winingBid.getBidValue());
	}
	
	@Test
	public void testGetAuctionBids() {
		Set<Bid> actualBids = auctionManager.getAuctionBids(auctionId1);
		assertTrue(actualBids.size()==3);
		int i = 1;
		for (Bid bid : actualBids) {
			assertEquals(1, bid.getAuctionId());
			assertEquals(1, bid.getBidderId());
			assertEquals(new BigDecimal(i++), bid.getBidValue());
		}
	}
	
	@Test
	public void testGetUserBids() {
		Set<Bid> actualBids = auctionManager.getUserBids(userId1);
		assertTrue(actualBids.size()==5);
		for (Bid bid : actualBids) {
			assertEquals(1, bid.getBidderId());
		}
	}

}
