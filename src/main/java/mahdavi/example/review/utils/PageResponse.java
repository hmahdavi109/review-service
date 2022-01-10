package mahdavi.example.review.utils;

import lombok.Getter;

import java.util.List;

@Getter
public class PageResponse<T> {

    private final List<T> content;

    private final boolean firstPage;

    private final boolean lastPage;

    private final long count;


    public PageResponse(List<T> content, boolean isFirst, boolean isLast, long count) {
        this.content = content;

        this.firstPage = isFirst;

        this.lastPage = isLast;

        this.count = count;
    }

}
