package vn.com.knowledge.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
@Document
public class Data {
    @Id
    private String name;
    private List<String> corpus;

    public Data() {
    }

    public Data(String name, List<String> corpus) {
        this.name = name;
        this.corpus = corpus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getCorpus() {
        return corpus;
    }

    public void setCorpus(List<String> corpus) {
        this.corpus = corpus;
    }
}
