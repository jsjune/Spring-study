package study.itemservice.domain.item;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ItemRepositoryTest {

    ItemRepository iTemRepository = new ItemRepository();

    @AfterEach
    void afterEach() {
        iTemRepository.clearStore();
    }

    @Test
    void save() {
        //give
        Item item = new Item("itemA", 10000, 10);

        //when
        Item saveItem = iTemRepository.save(item);

        //then
        Item findItem = iTemRepository.findById(saveItem.getId());
        assertThat(findItem).isEqualTo(saveItem);
    }

    @Test
    void findAll() {
        //given
        Item item1 = new Item("item1", 10000, 10);
        Item item2 = new Item("item2", 20000, 20);

        iTemRepository.save(item1);
        iTemRepository.save(item2);

        //when
        List<Item> result = iTemRepository.findAll();

        //then
        assertThat(result.size()).isEqualTo(2);
        assertThat(result).contains(item1, item2);
    }

    @Test
    void updateItem() {
        //given
        Item item = new Item("item1", 10000, 10);
        Item saveItem = iTemRepository.save(item);
        Long itemId = saveItem.getId();

        //when
        Item updateParam = new Item("item2", 20000, 30);
        iTemRepository.update(itemId, updateParam);
        Item findItem = iTemRepository.findById(itemId);

        //then
        assertThat(findItem.getItemName()).isEqualTo(updateParam.getItemName());
        assertThat(findItem.getPrice()).isEqualTo(updateParam.getPrice());
        assertThat(findItem.getQuantity()).isEqualTo(updateParam.getQuantity());
    }
}