package com.jindolph.shoppingmall;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.jindolph.shoppingmall.constant.ItemStat;
import com.jindolph.shoppingmall.entity.Item;
import com.jindolph.shoppingmall.entity.Member;
import com.jindolph.shoppingmall.entity.QItem;
import com.jindolph.shoppingmall.repository.ItemRepository;
import com.jindolph.shoppingmall.security.TokenProvider;
import com.jindolph.shoppingmall.service.MemberService;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class ShoppingmallApplicationTests {
	@Autowired
	ItemRepository itemRepository;
	@Autowired
	MemberService memberService;
	@Autowired
	TokenProvider tokenProvider;

	@PersistenceContext
	EntityManager entityManager;

	@Test
	void contextLoads() {
	}

	@Test
	@DisplayName("item save test")
	public void createItemTest() {
		Item item = new Item();
		item.setName("name");
		item.setDetail("detail");
		item.setPrice(1234);
		item.setStock(123);
		item.setItemStat(ItemStat.SELL);
		item.setRegDateTime(LocalDateTime.now());
		item.setUpdDateTime(LocalDateTime.now());
		System.out.println(itemRepository.save(item).toString());
	}

	@Test
	@DisplayName("item list save and search")
	public void saveItemListAndSearch() {
		String[] itemNames = { "apple", "grape", "orange" };
		String[] itemDetails = { "Very Red Apple", "Shine muscat", "Big Ugly Orange" };
		int[] itemPrices = { 1500, 10000, 550 };
		int[] itemStocks = { 100, 100, 50 };

		List<Item> items = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			Item item = new Item();
			item.setName(itemNames[i]);
			item.setDetail(itemDetails[i]);
			item.setPrice(itemPrices[i]);
			item.setStock(itemStocks[i]);
			item.setItemStat(ItemStat.SELL);
			item.setRegDateTime(LocalDateTime.now());
			item.setUpdDateTime(LocalDateTime.now());
			items.add(item);
		}

		itemRepository.saveAll(items);

		itemRepository.saveAll(items);

		System.out.println(itemRepository.findByName("apple"));
	}

	@Test
	@DisplayName("querydsl Test")
	public void querydslTest() {
		createTestItems();

		JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
		QItem qItem = QItem.item;
		JPAQuery<Item> query = queryFactory.selectFrom(qItem).where(qItem.itemStat.eq(ItemStat.SELL))
				.where(qItem.name.like("%" + "a" + "%")).orderBy(qItem.price.desc());

		List<Item> searchedItems = query.fetch();

		for (Item item : searchedItems) {
			System.out.println(item.toString());
		}
	}

	@Test
	@DisplayName("member signup test")
	public void memberSignUpTest() {
		Member newMember = new Member();

		newMember.setName("name");
		newMember.setEmail("name@email.com");
		newMember.setPassword("1234");

		memberService.create(newMember);
	}

	// @Test
	// @DisplayName("duplicated member signup test")
	// public void duplicatedMemberSignUpTest() {
	// Member newMember = new Member();

	// newMember.setName("name");
	// newMember.setEmail("name@email.com");
	// newMember.setPassword("1234");

	// memberService.create(newMember);
	// memberService.create(newMember);
	// }

	@Test
	@DisplayName("JWT create test")
	public void TokenCreateAndValidateTest() {
		Member member = new Member();

		member.setName("tester");
		member.setEmail("test@email.com");
		member.setPassword("1234");

		memberService.create(member);

		String token = tokenProvider.create(member);
		System.out.println("### Token: " + token);

		String userEmail = tokenProvider.validateAndGetUserEmail(token);
		System.out.println("### user Email: " + userEmail);

		boolean result = (userEmail.equals(member.getEmail()));
		System.out.println("### member Email: " + member.getEmail());

		System.out.println("### Result " + result);
	}

	private void createTestItems() {
		String[] itemNames = { "apple", "grape", "orange", "avocado", "blueberry", "cherry", "kiwi", "lemon", "mango",
				"peach", "plum", "watermelon", "melon", "strawberry", "raspberry", "pear" };
		String[] itemDetails = { "Very Red Apple", "Shine muscat", "Big Ugly Orange", "Tastes like Butter",
				"Very Berry Blueberry", "Cherry like Lips", "New Zealand Kiwi", "Very Sour Lemon", "Asian Sweet Mango",
				"Peach like Baby Ass", "Big Plum", "As huge as Boobs", "Melona when you come",
				"Soft like Strawberry milk", "Raspberry Pie", "Luxury Fruit" };
		int[] itemPrices = { 1500, 10000, 550, 7000, 200, 400, 300, 1300, 2000, 1800, 480, 18000, 7500, 380, 150,
				13000 };
		int[] itemStocks = { 1000, 1000, 500, 100, 3000, 1500, 800, 700, 1000, 1000, 3000, 500, 300, 2000, 4000, 150 };

		List<Item> items = new ArrayList<>();
		for (int i = 0; i < 16; i++) {
			Item item = new Item();
			item.setName(itemNames[i]);
			item.setDetail(itemDetails[i]);
			item.setPrice(itemPrices[i]);
			item.setStock(itemStocks[i]);
			item.setItemStat(ItemStat.SELL);
			item.setRegDateTime(LocalDateTime.now());
			item.setUpdDateTime(LocalDateTime.now());
			items.add(item);
		}

		itemRepository.saveAll(items);
	}
}
