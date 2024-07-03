package ru.itmo.wp.form;

import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TagForm {
    private int errorIndex;
    private List<@Pattern(regexp = "[a-zA-Z]+", message = "Expected Latin letters") String> tags;

    public void setTags(String tags) {
        if (tags.isEmpty()) {
            this.tags = new ArrayList<>();
        } else {
             String[] array_tags = tags.split("\\s+");
             this.tags = new ArrayList<>();
             this.tags.addAll(Arrays.asList(array_tags));
            for (int i = 0; i < array_tags.length; i++) {
                if (!array_tags[i].matches("[a-zA-Z]+")) {
                    errorIndex = i;
                }
            }
        }
    }

    public List<String> getTags() {
        return tags;
    }

    public int getErrorIndex() {
        return errorIndex;
    }
}
