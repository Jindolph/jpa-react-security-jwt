package com.jindolph.shoppingmall.prelaoder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.jindolph.shoppingmall.constant.ItemStat;
import com.jindolph.shoppingmall.entity.Item;
import com.jindolph.shoppingmall.repository.ItemRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class TestDataLoader implements CommandLineRunner{
    @Autowired
    ItemRepository itemRepository;
    
    @Override
    public void run(String... args) throws Exception {
        createTestItems();
    }

    private void createTestItems() {
		String[] itemNames = { 
            "apple", "grape", 
            "orange", "avocado", 
            "blueberry", "cherry", 
            "kiwi", "lemon", 
            "mango", "peach", 
            "plum", "watermelon", 
            "melon", "strawberry", 
            "raspberry", "pear" 
        };
		String[] itemDetails = { 
            "Very Red Apple", "Shine muscat", 
            "Big Ugly Orange", "Tastes like Butter",
			"Very Berry Blueberry", "Cherry like Lips", 
            "New Zealand Kiwi", "Very Sour Lemon", 
            "Asian Sweet Mango", "Peach like Baby Ass", 
            "Big Plum", "As huge as Boobs", 
            "Melona when you come", "Soft like Strawberry milk", 
            "Raspberry Pie",  "Luxury Fruit" 
        };
		int[] itemPrices = { 
            1500, 10000, 
            550, 7000, 
            200, 400, 
            300, 1300, 
            2000, 1800, 
            480, 18000, 
            7500, 380, 
            150, 13000 
        };
		int[] itemStocks = { 
            1000, 1000, 
            500, 100, 
            3000, 1500, 
            800, 700, 
            1000, 1000, 
            3000, 500, 
            300, 2000, 
            4000, 150 
        };

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
