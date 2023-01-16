package main.requests;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.List;

@Data
public class CreateCampaignRequest {
    @NotBlank(message = "Name is a mandatory field")
    @Size(max = 45, message = "The name cannot exceed 45 characters")
    private String name;
    @NotEmpty(message = "A start date is mandatory")
    @Pattern(regexp = "^\\d{4}\\-(0[1-9]|1[012])\\-(0[1-9]|[12][0-9]|3[01])$", message= "The date must be formatted as: yyyy-MM-dd")
    private String startDate;
    @Pattern(regexp = "^\\d+(.\\d{0,2})?$", message= "The bid must be a valid number")
    private String bid;
    @NotEmpty(message = "Select at least one product to promote")
    private List<String> products;
}
