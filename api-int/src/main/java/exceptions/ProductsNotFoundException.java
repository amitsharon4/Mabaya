package exceptions;
import java.text.MessageFormat;
import java.util.List;

public class ProductsNotFoundException extends Exception{
    public ProductsNotFoundException(List<String> products){
        super(MessageFormat.format("Products not found:\n{0}", products));
    }
}
