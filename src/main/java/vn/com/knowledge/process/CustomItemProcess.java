package vn.com.knowledge.process;

import org.springframework.batch.item.ItemProcessor;
import vn.com.knowledge.model.Data;

public class CustomItemProcess implements ItemProcessor<Data, Data> {

    @Override
    public Data process(Data s) throws Exception {
        for (String content:s.getCorpus())
        {
            content.trim();
        }
        return s;
    }
}
