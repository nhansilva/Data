package vn.com.knowledge.writer;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import vn.com.knowledge.model.Data;

import java.util.List;

public class CustomItemWriter implements ItemWriter<Data> {

    @Override
    public void write(List<? extends Data> list) throws Exception {

    }
}
