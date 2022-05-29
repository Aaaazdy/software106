package Controller;

public interface Controller {
    /**
     * lunch page method
     */
    default void startPage(){    }

    /**
     * start next page method
     */
    default void startNextPage(){ }

    /**
     * start last page method
     */
    default void startLastPage(){ }
}
