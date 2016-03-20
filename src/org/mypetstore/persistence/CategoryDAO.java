package org.mypetstore.persistence;
import org.mypetstore.domain.Category;
import java.util.List;
/**
 * Created by Mr.Wan on 2016/3/18.
 */
public interface CategoryDAO {
    List<Category> getCategoryList();
    Category getCategory(String categoryId);
}