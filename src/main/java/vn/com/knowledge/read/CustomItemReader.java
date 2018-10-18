package vn.com.knowledge.read;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import vn.com.knowledge.model.Data;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CustomItemReader implements ItemReader<Data> {
    String extension = ".txt";
    String url = "E:\\Data";
    private int objectCount = 0;

    public List<String> listFilesForFolder(String extensions, String url) {
        List<String> filesInFolder = new ArrayList<>();
        try (Stream<Path> paths = Files.walk(Paths.get(url))
                .filter(path -> path.toString().endsWith(extensions))) {
            filesInFolder = paths
                    .map(path -> Files.isDirectory(path) ? path.toString() : path.toString())
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return filesInFolder;
    }

    public List<Data> readContent() throws IOException {
        List<Data> lsData = new ArrayList<>();
        List<String> listFolder = listFilesForFolder(extension, url);
        for (String folder : listFolder) {
            Path path = Paths.get(folder);
            List<String> fileList = Files.readAllLines(path);
            Data data = new Data();
            data.setName(folder.replace(extension,"").replace(url,"").replace("\\",""));
            data.setCorpus(fileList);
            lsData.add(data);
        }
        return lsData;
    }

    @Override
    public Data read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        if (objectCount < readContent().size()) {
            return readContent().get(objectCount++);
        } else {
            objectCount = 0;
        }
        return null;
    }
}
