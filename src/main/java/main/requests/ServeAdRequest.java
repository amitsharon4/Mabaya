package main.requests;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class ServeAdRequest
{
    @NotEmpty(message = "Product category is a mandatory field")
    private String category;
}
