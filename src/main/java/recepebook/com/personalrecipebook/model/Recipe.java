package recepebook.com.personalrecipebook.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class Recipe {

    String id;
    String user_id;
    String ingredients;
}
