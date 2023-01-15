package requests;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.List;

@Data
public class CreateCampaignRequest {
    @NotBlank(message = "Name is a mandatory field")
    @Size(max = 45, message = "The name cannot exceed 45 characters")
    private String name;
    @NotBlank(message = "A start date is mandatory")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private String startDate;
    @Pattern(regexp = "^\\d+(.\\d{0,2})?$")
    private String bid;
    @NotEmpty(message = "Select at least one product to promote")
    private List<String> products;
}
